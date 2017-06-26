import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class PreproccessImage {
    // base code found at:
    // https://stackoverflow.com/questions/20321606/convert-2d-binary-matrix-to-black-white-image-in-java
    // special thanks to bcorso
    protected static boolean convertImageToBinaryBlackAndWhite(){
        String outputImagePathName = "test";
        int w = 30, h = 30;
        outputImagePathName += String.valueOf(w) + "x" + String.valueOf(h);

        // create the binary mapping
        byte BLACK = (byte)0, WHITE = (byte)1;
        byte[] map = {BLACK, WHITE};
        IndexColorModel icm = new IndexColorModel(1, map.length, map, map, map);

        // create checkered data (replace this with a reading of the input image
        int[] imageData = new int[w*h];
        for(int i=0; i<w; i++)
            for(int j=0; j<h; j++)
                imageData[i*h + j] = i%4<2 && j%4<2 || i%4>=2 && j%4>=2 ? BLACK:WHITE;

        // create image from color model and data
        WritableRaster raster = icm.createCompatibleWritableRaster(w, h);
        raster.setPixels(0, 0, w, h, imageData);
        BufferedImage bi = new BufferedImage(icm, raster, false, null);

        // output to a file
        try {
            ImageIO.write(bi, "png", new File("out/out-images/" + outputImagePathName + ".png"));
        } catch(IOException ioe){
            System.out.println (ioe.toString());
            System.out.println("Could not find file " + imageData);
        }
    }


}
