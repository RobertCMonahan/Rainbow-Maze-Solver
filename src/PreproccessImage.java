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

public class PreproccessImage {
    // base code found at:
    // https://stackoverflow.com/questions/20321606/convert-2d-binary-matrix-to-black-white-image-in-java
    // special thanks to bcorso
    protected static boolean convertImageToBinaryBlackAndWhite(Path inputImagePath){
        // get input image
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(inputImagePath));

        Utils.printIntMatrix(matrix);

        List<Integer> imageListData = new ArrayList<>(matrix.length * matrix[0].length);
        for (int[] line : matrix){
            for (int pixel: line){
                imageListData.add(pixel);
            }
        }
        int[] imageData = Utils.convertIntegersListToIntArray(imageListData);
        // cut off sides
        // every ten pixels go down until you hit a wall

        String outputImagePathName = "test";
        int w = matrix.length, h = matrix[0].length;
        outputImagePathName += String.valueOf(w) + "x" + String.valueOf(h);

        // create the binary mapping
        byte BLACK = (byte)0, WHITE = (byte)1;
        byte[] map = {BLACK, WHITE};
        IndexColorModel icm = new IndexColorModel(1, 1, map, map, map);

        // create image from color model and data
        WritableRaster raster = icm.createCompatibleWritableRaster(w, h);
        raster.setPixels(0, 0, w, h, imageData);
        BufferedImage bi = new BufferedImage(icm, raster, false, null);

        // output to a file
        try {
            ImageIO.write(bi, "png", new File("out/out-images/" + outputImagePathName + ".png"));
        } catch(IOException ioe){
            System.out.println (ioe.toString());
            System.out.println("Could not find file " + Arrays.toString(imageData));
        }
        return true;
    }


}
