import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    private Maze tinyMaze = new Maze();
    @Test
    void testMatrixIsCreated() {
        tinyMaze.setMazeFilePath(Paths.get("test-images/tiny.png"));
        tinyMaze.constructMazeMatrix();
        int[][] matrix = tinyMaze.getMazeMatrix();
        boolean matrixIsEmpty = true;

        if(matrix!=null){
            for (int[] item : matrix) {
                if (item != null) {
                    matrixIsEmpty = false;
                }
            }
        }
        assertEquals(false, matrixIsEmpty, "test if a matrix is created");
    }

    @Test
    void testNodeCreation() {
        tinyMaze.setMazeFilePath(Paths.get("test-images/tiny.png"));
        tinyMaze.constructMazeMatrix();
        tinyMaze.findAndConnectNodes();
        tinyMaze.printAllNodeData();
    }
}