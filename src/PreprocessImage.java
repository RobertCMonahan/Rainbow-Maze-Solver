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
        int[] wallWidth = determineWidthOfWallsPathsAndBorder(outImagePath);

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
        int lineInterval = matrix.length / 8;
        int[] widths = new int[6];

        widths[0] = getWidthForRowOfPathOrWall(matrix[lineInterval * 2], 0, 1);
        widths[1] = getWidthForRowOfPathOrWall(matrix[lineInterval * 3], 0, 1);
        widths[2] = getWidthForRowOfPathOrWall(matrix[lineInterval * 4], 0, 1);
        widths[3] = getWidthForRowOfPathOrWall(matrix[lineInterval * 5], 0, 1);
        widths[4] = getWidthForRowOfPathOrWall(matrix[lineInterval * 6], 0, 1);
        widths[5] = getWidthForRowOfPathOrWall(matrix[lineInterval * 7], 0, 1);

        System.out.println("Widths" + Arrays.toString(widths));
        int boarderWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        widths[0] = getWidthForRowOfPathOrWall(matrix[lineInterval * 2], boarderWidth, 0);
        widths[1] = getWidthForRowOfPathOrWall(matrix[lineInterval * 3], boarderWidth, 0);
        widths[2] = getWidthForRowOfPathOrWall(matrix[lineInterval * 4], boarderWidth, 0);
        widths[3] = getWidthForRowOfPathOrWall(matrix[lineInterval * 5], boarderWidth, 0);
        widths[4] = getWidthForRowOfPathOrWall(matrix[lineInterval * 6], boarderWidth, 0);
        widths[5] = getWidthForRowOfPathOrWall(matrix[lineInterval * 7], boarderWidth, 0);

        System.out.println("Widths" + Arrays.toString(widths));
        int wallWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        widths[0] = getWidthForRowOfPathOrWall(matrix[lineInterval * 2], boarderWidth, 1);
        widths[1] = getWidthForRowOfPathOrWall(matrix[lineInterval * 3], boarderWidth, 1);
        widths[2] = getWidthForRowOfPathOrWall(matrix[lineInterval * 4], boarderWidth, 1);
        widths[3] = getWidthForRowOfPathOrWall(matrix[lineInterval * 5], boarderWidth, 1);
        widths[4] = getWidthForRowOfPathOrWall(matrix[lineInterval * 6], boarderWidth, 1);
        widths[5] = getWidthForRowOfPathOrWall(matrix[lineInterval * 7], boarderWidth, 1);

        System.out.println("Widths" + Arrays.toString(widths));
        int pathWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        System.out.println("wallWidth: " + wallWidth);
        System.out.println("pathWidth: " + pathWidth);
        System.out.println("boarderWidth: " + boarderWidth);
        return new int[]{wallWidth, pathWidth, boarderWidth};

        // the path width can give the woung number to easily, i need to figure out a better way to determine it.
        // it should be the smallest width that also occurs the most, maybe give a wight of 1 or 2 to the smallest number
    }

    /**
     *
     * @param row
     * @param pixelsToSkip
     * @param pathOrWall    path = 1, wall = 0
     * @return
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

    // cut off sides

}
