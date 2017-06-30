import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Paths.get;

public class PreproccessImage {

    protected static void preproccessImage(Path inputImagePath, String outputImageName){
        Path outImagePath = convertImageToBinaryBlackAndWhite(inputImagePath,outputImageName);
        int wallWidth = determineWidthOfWalls(outImagePath);

    }

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

    // simplify image
    // find a wall determine the width and length
    protected static int determineWidthOfWalls(Path inputImagePath){
        // get image
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(inputImagePath));
        int lineInterval = matrix.length / 6;
        int[] widths = new int[4];
        int tempWidth = 0;
        boolean foundWall = false;

        for (int pixel : matrix[lineInterval *2]){
            if (pixel == 1){
                //get a width
                tempWidth += 1;
                foundWall = true;

                //widths[0] = the width you got
            } else if (foundWall = true){
                // make widths[0] == the width of the wall
                //break for loop
            }
        }
        for (int pixel : matrix[lineInterval * 3]){
            if (pixel == 1){

            }
        }
        for (int pixel : matrix[lineInterval * 4]){
            if (pixel == 1){

            }
        }
        for (int pixel : matrix[lineInterval * 5]){
            if (pixel == 1){

            }
        }

        // compare the ints in widths
        // return the widths that have at least 2 that are the same

        return width;
    }
    // use the smaller of the two as a block size to work with
    // determine width and height of the output using the block size.
    // for every block
    // determine if there are more black or white pixels in the block

    // cut off sides

}
