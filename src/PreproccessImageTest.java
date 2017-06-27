import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

class PreproccessImageTest {



    @Test
    void testPreproccessingHasNoErrors() {
        Path imagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/black_and_white_maze_tiny.gif");
        boolean noErrors = PreproccessImage.convertImageToBinaryBlackAndWhite(imagePath);
        assertEquals(true, noErrors, "there were no errors will preproccessing the image");
    }
//
//    @Test
//    void testIfOutImageIsBinary(){
//        Path imagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/out/out-images/test569x569.png");
//        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(imagePath));
//        Utils.printIntMatrix(matrix);
//        assertEquals(1, matrix[0][0], "the top left pixel of the output image is white 1 ");
//    }

    @Test
    void testPreproccessingHasNoErrors2() {
        Path imagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/maze_small.jpg");
        boolean noErrors = PreproccessImage.convertImageToBinaryBlackAndWhite(imagePath);
        assertEquals(true, noErrors, "there were no errors will preproccessing the image");
    }

    @Test
    void testIfOutImageIsBinary2(){
        Path imagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/out/out-images/test700x700.png");
        int[][] matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(imagePath));
        Utils.printIntMatrix(matrix);
        assertEquals(1, matrix[0][0], "the top left pixel of the output image is white 1 ");
    }
}