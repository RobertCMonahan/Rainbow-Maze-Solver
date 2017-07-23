import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 *
 */
public class DrawPathThoughMaze {
    private int[] colorRGB = new int[]{0, 0, 255};
    private boolean colorIsChanging = true;
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;
    private int increment = 1;
    private int greenIncrement = -increment;
    private String imagePathName;

    public void createColorImage(int[][] matrix) {
        final int WHITE = 1;
        bufferedImage = new BufferedImage(matrix[0].length, matrix.length, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                int pixel = matrix[y][x];
                if (pixel == WHITE) {
                    bufferedImage.setRGB(x, y, 16777215); //white
                } else {
                    bufferedImage.setRGB(x, y, 0); //black
                }
            }
        }
        graphics2D = bufferedImage.createGraphics();
    }

    public void colorLine(int x1, int y1, int x2, int y2) {
        incrementColor();
        graphics2D.setColor(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        graphics2D.drawLine(x1, y1, x2, y2);
    }

    public void writeImageToDisk() {
        try {
            ImageIO.write(bufferedImage, "png", new File("out/out-images/" + imagePathName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void setIncrementByMazeSize(int totalNumberOfNodesInSolution) {
//        // (red - blue) / totalNumberOfNodesInSolution
//        this.increment = (16529464 - 3701244) / totalNumberOfNodesInSolution;
//    }

    protected void setIncrement(int incrementValue){
        this.increment = incrementValue;
        this.greenIncrement = -increment;
    }

    public void setOutputImagePath(String imagePath) {
        // (red - blue) / totalNumberOfNodesInSolution
        this.imagePathName = imagePath;
    }

    public void setColorToGreen() {
        // (red - blue) / totalNumberOfNodesInSolution
        this.increment = 0;
        colorRGB = new int[]{0, 255, 0};
        colorIsChanging = false;
    }

    private void incrementColor() {
        // change color (red --> blue or blue --> red)
        colorRGB[2] -= increment;
        colorRGB[0] += increment;

        if (colorIsChanging) {
            if (colorRGB[2] < Math.abs(increment)) {
                increment = -increment;
                if (colorRGB[1] < Math.abs(increment)) {
                    greenIncrement = -greenIncrement;
                }
                if (colorRGB[1] > (220 - Math.abs(increment))) {
                    greenIncrement = -greenIncrement;
                }
                colorRGB[1] += greenIncrement;
            }
            if (colorRGB[2] > (255 - Math.abs(increment))) {
                increment = -increment;
                if (colorRGB[1] < Math.abs(increment)) {
                    greenIncrement = -greenIncrement;
                }
                if (colorRGB[1] > (220 - Math.abs(increment))) {
                    greenIncrement = -greenIncrement;
                }
                colorRGB[1] += greenIncrement;
            }
        }
    }
}
