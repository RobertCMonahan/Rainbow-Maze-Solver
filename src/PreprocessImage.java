import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Paths.get;

public class PreprocessImage {
    /**
     * Process an image of a maze so that it can be solved by the algorithm.
     * Some of the processing that occurs:
     * -removes boarders
     * -converts to black & white binary png
     * -simplifies maze to reduce the number of nodes.
     *
     *
     * @param inputImagePath the Path to the image you would like to process
     * @param outputImageName the name of the output image
     */
    protected static void preprocessImage(Path inputImagePath, String outputImageName){
        Path outImagePath = convertImageToBinaryBlackAndWhite(inputImagePath,outputImageName);
        int[] wallPathBorderWidths = determineWidthOfWallsPathsAndBorder(outImagePath);
        simplifiyImage(outImagePath, wallPathBorderWidths);
    }

    /**
     * Converts a color image to a black & white binary png
     *
     * @param inputImagePath the Path to the image you would like to process
     * @param outputImageName the name of the output image
     * @return Path of the output image
     */
    protected static Path convertImageToBinaryBlackAndWhite(Path inputImagePath, String outputImageName){
        // base code for convertImageToBinaryBlackAndWhite found at:
        // https://stackoverflow.com/questions/20321606/convert-2d-binary-matrix-to-black-white-image-in-java
        // special thanks to bcorso

        // get input image
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(inputImagePath));

        // convert image into binary black and white data
        int[] binaryImageData = new int[matrix.length * matrix[0].length];
        int atIndex = 0;
        for (int[] line : matrix){
            for (int pixel: line){
                if (Math.abs(pixel) <= 8388607){
                    binaryImageData[atIndex] = 1;
                } else if (Math.abs(pixel) >=8388608){
                    binaryImageData[atIndex] = 0;
                }
                atIndex += 1;
            }
        }

        // prepare output variables
        String outputImagePathName = outputImageName;
        int w = matrix.length, h = matrix[0].length;
        outputImagePathName += String.valueOf(w) + "x" + String.valueOf(h);

        // create the binary mapping
        byte BLACK = (byte)0, WHITE = (byte)1;
        byte[] map = {BLACK, WHITE};
        IndexColorModel icm = new IndexColorModel(1, 1, map, map, map);

        // create image from color model and data
        WritableRaster raster = icm.createCompatibleWritableRaster(w, h);
        raster.setPixels(0, 0, w, h, binaryImageData);
        BufferedImage bi = new BufferedImage(icm, raster, false, null);

        // output to a file
        try {
            ImageIO.write(bi, "png", new File("test-images/" + outputImagePathName + ".png"));
            System.out.println("Created black and white image at: test-images/" + outputImagePathName + ".png");
        } catch(IOException ioe){
            System.out.println (ioe.toString());
            System.out.println("Could not find file " + Arrays.toString(binaryImageData));
        }
        return get("test-images/" + outputImagePathName + ".png");
    }


    /**
     * Gets the width of the walls in the maze so that the image can be simplified
     *
     * @param inputImagePath path to the black & white binary image that you want the wall width of
     * @return int[] width of walls, width of path, width of border
     */
    protected static int[] determineWidthOfWallsPathsAndBorder(Path inputImagePath){
        // get image
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(inputImagePath));
        int numberOfRowsToCheck = 30; // this is the number of samples to take to check the paths and walls
        int lineInterval = matrix.length / (numberOfRowsToCheck+2);
        int[] widths = new int[numberOfRowsToCheck];


        for (int i=0 ; i < numberOfRowsToCheck ; i++){
            widths[i] = getWidthForRowOfPathOrWall(matrix[lineInterval * (i+2) ], 0, 1);
        }
        int borderWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        for (int i=0 ; i < numberOfRowsToCheck ; i++){
            widths[i] = getWidthForRowOfPathOrWall(matrix[lineInterval * (i+2) ], borderWidth, 0);
        }
        int wallWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        for (int i=0 ; i < numberOfRowsToCheck ; i++){
            widths[i] = getWidthForRowOfPathOrWall(matrix[lineInterval * (i+2) ], borderWidth, 1);
        }
        int pathWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        System.out.println("wallWidth: " + wallWidth);
        System.out.println("pathWidth: " + pathWidth);
        System.out.println("borderWidth: " + borderWidth);
        return new int[]{wallWidth, pathWidth, borderWidth};
    }

    /**
     *
     * @param row           the row (int[]) that you want to figure out the width of a wall or path
     * @param pixelsToSkip  the number of pixels at the start you want to skip, this should be the size of the border
     * @param pathOrWall    path = 1, wall = 0
     * @return              int that is the width in pixels of the wall or path
     */
    private static int getWidthForRowOfPathOrWall(int[] row, int pixelsToSkip, int pathOrWall){
        boolean foundWall = false;
        int width = 0;
        int startingLocation = 1 + pixelsToSkip;

        for(int i=startingLocation; i< row.length; i++){
            int pixel = row[i];
            if (pixel == pathOrWall){
                width += 1;
                foundWall = true;
            } else if ((pixel != pathOrWall) && (foundWall)){
                return width;
            }
        }
        return -1; // no wall was found
    }

    // use the smaller of the two as a block size to work with
    // determine width and height of the output using the block size.
    // for every block
    // determine if there are more black or white pixels in the block

    /*
    - remove borders
        - skip the size of the border
    - divide up image by blocks of the size of the greatest common denominator of the walls and path
        - convert each block into either a 1 or 0
            - make a new matrix for the new output image
            - count up 1 & 0 in each block
            - which ever there are more of make the corresponding block in the new matrix that color
            - go to next block
            - when there are no blocks left
                - create an image from the new matrix

     this may suffer from a gerrymandering problem where there is a wall but the path is the majority on both blocks and
     when it is converted the wall is destroyed.
     */

    protected static void simplifiyImage(Path image, int[] wallPathBorderWidths){
        // get the greatest common denomnator from the wallpath use that as the new pixel
        // skip borders
        int GCF = Utils.getGreatestCommonFactor(wallPathBorderWidths[0], wallPathBorderWidths[1]);
    }

}
