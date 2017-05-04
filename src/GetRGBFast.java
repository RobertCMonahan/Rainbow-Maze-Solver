import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

// https://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image
// special thanks to CatGuardian, Motasim, & cluecke
// faster than using getRGB
// Top-Left --> Bottom-Right
public class GetRGBFast {
    protected static int[][] convertToIntMatrix(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();

        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
        final int bitsPerPixel = image.getColorModel().getPixelSize();

        if (bitsPerPixel == 1) {
            return makeBlackAndWhiteMatrix(pixels, width, height);
        } else {
            if (hasAlphaChannel) {
                return makeAlphaRGBMatrix(pixels, width, height);
            } else {
                return makeRGBMatrix(pixels, width, height);
            }
        }
    }


    // for black and white images (where 0=black and 1=white)
    private static int[][] makeBlackAndWhiteMatrix(byte[] pixels, int width, int height){

        int[][] matrixResult = new int[height][width];

        boolean done = false;
        boolean alreadyWentToNextByte = false;
        int byteIndex = 0;
        int row = 0;
        int col = 0;
        int numBits = 0;
        byte currentByte = pixels[byteIndex];
        while (!done) {
            alreadyWentToNextByte = false;

            matrixResult[row][col] = (currentByte & 0x80) >> 7;
            currentByte = (byte) (((int) currentByte) << 1);
            numBits++;

            if ((row == height - 1) && (col == width - 1)) {
                done = true;
            } else {
                col++;

                if (numBits == 8) {
                    currentByte = pixels[++byteIndex];
                    numBits = 0;
                    alreadyWentToNextByte = true;
                }

                if (col == width) {
                    row++;
                    col = 0;

                    if (!alreadyWentToNextByte) {
                        currentByte = pixels[++byteIndex];
                        numBits = 0;
                    }
                }
            }
        }
        return matrixResult;
    }


    private static int[][] makeAlphaRGBMatrix(byte[] pixels, int width, int height){

        int[][] matrixResult = new int[height][width];

        final int pixelLength = 4;
        for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
            argb += ((int) pixels[pixel + 1] & 0xff); // blue
            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
            matrixResult[row][col] = argb;
            col++;
            if (col == width) {
                col = 0;
                row++;
            }
        }
        return matrixResult;
    }


    private static int[][] makeRGBMatrix(byte[] pixels, int width, int height){

        int[][] matrixResult = new int[height][width];

        final int pixelLength = 3;
        for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += -16777216; // 255 alpha
            argb += ((int) pixels[pixel] & 0xff); // blue
            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            matrixResult[row][col] = argb;
            col++;
            if (col == width) {
                col = 0;
                row++;
            }
        }
        return matrixResult;
    }

}