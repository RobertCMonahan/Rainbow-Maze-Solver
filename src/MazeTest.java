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
        //tinyMaze.printMazeMatrix();
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

        List<Maze.MazeNode> allNodes = tinyMaze.nodes;
        int[][] node0Neighbors = allNodes.get(0).getArrayOfNeighbors();
        int[][] node1Neighbors = allNodes.get(1).getArrayOfNeighbors();
        int[][] node2Neighbors = allNodes.get(2).getArrayOfNeighbors();
        int[][] node3Neighbors = allNodes.get(3).getArrayOfNeighbors();
        int[][] node4Neighbors = allNodes.get(4).getArrayOfNeighbors();
        int[][] node5Neighbors = allNodes.get(5).getArrayOfNeighbors();
        int[][] node11Neighbors = allNodes.get(11).getArrayOfNeighbors();
        int[][] node12Neighbors = allNodes.get(12).getArrayOfNeighbors();
        int[][] node19Neighbors = allNodes.get(19).getArrayOfNeighbors();
        int[][] node20Neighbors = allNodes.get(20).getArrayOfNeighbors();
        int[][] node21Neighbors = allNodes.get(21).getArrayOfNeighbors();
        int[][] node22Neighbors = allNodes.get(22).getArrayOfNeighbors();

        assertEquals(23, allNodes.size(),  "test how many nodes: expected size of 23");

        assertEquals(true, Arrays.equals(new int[]{1, 3, 1}, node0Neighbors[2]),  "node0 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{1, 3, 2}, node1Neighbors[1]),  "node1 neighbor to the east");
        assertEquals(true, Arrays.equals(new int[]{3, 1, 2}, node1Neighbors[2]),  "node1 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{1, 1, 2}, node2Neighbors[3]),  "node2 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{0, 3, 1}, node2Neighbors[0]),  "node2 neighbor to the north");
        assertEquals(true, Arrays.equals(new int[]{1, 6, 3}, node2Neighbors[1]),  "node2 neighbor to the east");

        assertEquals(true, Arrays.equals(new int[]{1, 3, 3}, node3Neighbors[3]),  "node3 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{1, 8, 2}, node3Neighbors[1]),  "node3 neighbor to the east");
        assertEquals(true, Arrays.equals(new int[]{3, 6, 2}, node3Neighbors[2]),  "node3 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{1, 6, 2}, node4Neighbors[3]),  "node4 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{5, 8, 4}, node4Neighbors[2]),  "node4 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{1, 1, 2}, node5Neighbors[0]),  "node5 neighbor to the north");
        assertEquals(true, Arrays.equals(new int[]{3, 3, 2}, node5Neighbors[1]),  "node5 neighbor to the east");
        assertEquals(true, Arrays.equals(new int[]{5, 1, 2}, node5Neighbors[2]),  "node5 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{5, 3, 2}, node11Neighbors[3]),  "node11 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{3, 5, 2}, node11Neighbors[0]),  "node11 neighbor to the north");
        assertEquals(true, Arrays.equals(new int[]{8, 5, 3}, node11Neighbors[2]),  "node11 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{5, 8, 1}, node12Neighbors[1]),  "node12 neighbor to the east");

        assertEquals(true, Arrays.equals(new int[]{8, 3, 2}, node19Neighbors[3]),  "node19 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{5, 5, 3}, node19Neighbors[0]),  "node19 neighbor to the north");
        assertEquals(true, Arrays.equals(new int[]{8, 7, 2}, node19Neighbors[1]),  "node19 neighbor to the east");

        assertEquals(true, Arrays.equals(new int[]{8, 5, 2}, node20Neighbors[3]),  "node20 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{8, 8, 1}, node20Neighbors[1]),  "node20 neighbor to the east");
        assertEquals(true, Arrays.equals(new int[]{9, 7, 1}, node20Neighbors[2]),  "node20 neighbor to the south");

        assertEquals(true, Arrays.equals(new int[]{8, 7, 1}, node21Neighbors[3]),  "node21 neighbor to the west");
        assertEquals(true, Arrays.equals(new int[]{7, 8, 1}, node21Neighbors[0]),  "node21 neighbor to the north");

        assertEquals(true, Arrays.equals(new int[]{8, 7, 1}, node22Neighbors[0]),  "node22 neighbor to the north");
    }
}