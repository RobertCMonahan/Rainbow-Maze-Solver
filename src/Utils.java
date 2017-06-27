import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Utils {
    /**
     * prints out a matrix to the console in the same format as the matrix is in. Each line of the matrix is printed on
     * a new line in the console
     *
     * @param matrix int[][] that you want to print to the console
     */
    public static void printIntMatrix(int[][] matrix){
        for (int[] row : matrix) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    /**
     * Creates a BufferedImage from a files Path.
     *
     * @param mazeFilePath Path of the file you would like to create of buffered image of
     * @return BufferedImage
     */
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
     * @param  exitNode   A MazeNode object, that you want a path too
     * @param  drawing    the drawing you want to draw the green line which follows that path.
     * @return            ArrayList of node locations in the format int[y,x] from exitNode to the last node with a
     *                    parent (usually the startNode).
     */
    protected static List<int[]> getAndDrawSolutionPath(MazeNode exitNode, DrawPathThoughMaze drawing) {
        drawing.setColorToGreen();
        List<int[]> shortestPath = new ArrayList<int[]>();
        shortestPath.add(exitNode.getPosition());

        MazeNode currentNode = exitNode;
        while (currentNode.getDistanceFromStart() > 0) {
            MazeNode parent = currentNode.getParent();
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

    /**
     * Courtisty of the fine people at StackOverFlow
     * https://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array
     * special thanks to Jon Skeet, Marek Sebera, and Matthew Willis.
     *
     * Converts a List<Integer> to a primitive type int[]
     * @param integers          must be a ArrayList of type Integer
     * @return                  an int array
     */
    public static int[] convertIntegersListToIntArray(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}
