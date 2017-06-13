import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Goes up the chain of parents from the given node until there are no parents left.
     * Creates and returns an ArrayList of the nodes location [y,x].
     * Also draws a green line of the path followed into the drawing given.
     *
     * @param  exitNode   A Maze.MazeNode object, that you want a path too
     * @param  drawing    the drawing you want to draw the green line which follows that path.
     * @return            ArrayList of node locations in the format int[y,x] from exitNode to the last node with a
     *                    parent (usually the startNode).
     */
    protected static List<int[]> getAndDrawSolutionPath(Maze.MazeNode exitNode, DrawPathThoughMaze drawing) {
        drawing.setColorToGreen();
        List<int[]> shortestPath = new ArrayList<int[]>();
        shortestPath.add(exitNode.getPosition());

        Maze.MazeNode currentNode = exitNode;
        while (currentNode.getDistanceFromStart() > 0) {
            Maze.MazeNode parent = currentNode.getParent();
            shortestPath.add(parent.getPosition());

            int[] lastCoords = parent.getPosition();
            int[] currentCoords = currentNode.getPosition();
            drawing.colorLine(currentCoords[1], currentCoords[0], lastCoords[1], lastCoords[0]);


            currentNode = parent;
        }

        for (int i = 0; i < shortestPath.size(); i++) {
            String value = Arrays.toString(shortestPath.get(i));
            System.out.print(value + ", ");
        }
        System.out.println();
        return shortestPath;

    }

}
