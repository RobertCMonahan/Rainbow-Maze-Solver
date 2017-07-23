import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

class PreproccessImageTest {

    @Test
    void imageConvertsToBinary01(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/1.gif");
        String outputImageName = "1_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary02(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/2.png");
        String outputImageName = "2_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary03(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/3.jpeg");
        String outputImageName = "3_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary04(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/4.gif");
        String outputImageName = "4_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary05(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/5.jpeg");
        String outputImageName = "5_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary06(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/6.png");
        String outputImageName = "6_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary07(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/7.jpg");
        String outputImageName = "7_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary08(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/8.png");
        String outputImageName = "8_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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
        assertEquals(true, isBinary, "output image contains pixels that are not 1 or 0");
    }

    @Test
    void imageConvertsToBinary09(){
        Path inputImagePath = get("/home/emerald/Projects/Rainbow-Maze-Solver/test-images/9.gif");
        String outputImageName = "9_bin_";

        Path outputImagePath = PreprocessImage.convertImageToBinaryBlackAndWhite(inputImagePath, outputImageName);

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