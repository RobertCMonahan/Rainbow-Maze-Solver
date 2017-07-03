import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

class PreproccessImageTest {

    @Test
    void outputIsBinary(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/maze_small.jpg");
        PreprocessImage.preprocessImage(inputImagePath, "test");

        Path outputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/test700x700.png");

        boolean isBinary = true;
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(outputImagePath));
        for (int[] line : matrix){
            for (int pixel: line){
                if ((pixel >= 2)||(pixel <= -1)){
                // if pixel is not 0 or 1
                    isBinary = false;
                }
            }
        }
        //Utils.printIntMatrix(matrix);
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }
}