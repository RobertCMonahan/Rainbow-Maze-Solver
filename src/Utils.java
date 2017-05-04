import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static void printIntMatrix(int[][] matrix){
        for (int[] row : matrix) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static BufferedImage createBufferedImage(Path mazeFilePath){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Files.newInputStream(mazeFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
