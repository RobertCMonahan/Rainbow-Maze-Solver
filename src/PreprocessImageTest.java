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

    @Test
    void outputIsBinary2(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/simple_600x600.png");
        PreprocessImage.preprocessImage(inputImagePath, "test");

        Path outputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/simple_600x600.png");

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

    @Test
    void canDetermineWallBorderPath(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/maze_small.jpg");
        Path outImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, "test");
        int[] wallPathBorderWidths = PreprocessImage.determineWidthOfWallsPathsAndBorder(outImagePath);

        assertArrayEquals(new int[]{7,34,38}, wallPathBorderWidths, "Widths should be: Wall==7, Path==34, Border==38" );
    }


}