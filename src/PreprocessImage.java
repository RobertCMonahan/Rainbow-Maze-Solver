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
        int wallWidth = determineWidthOfWalls(outImagePath);

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
     * @return int that is the width of the maze walls.
     */
    protected static int determineWidthOfWalls(Path inputImagePath){
        // get image
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(inputImagePath));
        int lineInterval = matrix.length / 6;
        int[] widths = new int[4];
        widths[0] = getFirstWallWidthForRow(matrix[lineInterval * 2]);
        widths[1] = getFirstWallWidthForRow(matrix[lineInterval * 3]);
        widths[2] = getFirstWallWidthForRow(matrix[lineInterval * 4]);
        widths[3] = getFirstWallWidthForRow(matrix[lineInterval * 5]);

//        System.out.println("lineInterval: " + lineInterval);
//        System.out.println(matrix[lineInterval * 2]);
//        System.out.println("widths array: " + Arrays.toString(widths));
//        System.out.println(Utils.countOccurrence(widths));


        int wallWidth = Utils.getKeyWithLargestValueFromMap(Utils.countOccurrence(widths));

        // compare the ints in widths
        // return the widths that have at least 2 that are the same
        System.out.println("wallWidth: " + wallWidth);
        return wallWidth;
    }

    /**
     *
     * @param row
     *
     * @return
     */
    private static int getFirstWallWidthForRow(int[] row){
        boolean foundWall = false;
        int width = 0;

        for (int pixel : row){
            if (pixel == 0){
                width += 1;
                foundWall = true;
            } else if ((pixel == 1) && (foundWall)){
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
