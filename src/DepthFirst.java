import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


class DepthFirst {
    private static int bottomOfMaze;
    private static DrawPathThoughMaze drawing;
    private static boolean solutionFound = false;

    protected static boolean search(Maze maze) {
        System.out.println("Depth-First");

        final long START_TIME = System.nanoTime();

        drawing = new DrawPathThoughMaze();
        drawing.createColorImage(maze);
        drawing.setOutputImagePath("Depth-First_" + maze.getMazeMatrixLength());

        bottomOfMaze = maze.getMazeMatrixLength() - 1;

        System.out.println("bottomOfMaze" + bottomOfMaze);

        List<Maze.MazeNode> nodes = maze.getNodes();
        List<Maze.MazeNode> nodeStack = new ArrayList<Maze.MazeNode>();
        nodeStack.add(nodes.get(0));

        Maze.MazeNode startNode = nodeStack.get(nodeStack.size() - 1);
        DFSVisit(startNode);

        final long ELAPSED_TIME = System.nanoTime() - START_TIME;
        double elapsedTimeInSeconds = (double) ELAPSED_TIME / 1000000000.0;
        System.out.println(elapsedTimeInSeconds + " seconds");
        drawing.writeImageToDisk();

        return solutionFound;
    }

    // DFSVisit( nodeStack(topitem) )
    // for each neighbor of topitem in stack
    // item's parent = topitem //(current node)
    // mark item as visited
    // run DFSVisit on node
    protected static void DFSVisit(Maze.MazeNode currentNode) {
        if (!solutionFound) {
            if (currentNode.getPosition()[0] == bottomOfMaze) { // at end node
                System.out.println("Found End!");
                solutionFound = true;
                //follow in of parents back home
                // need function for this
                List<int[]> solutionPath = Utils.getAndDrawSolutionPath(currentNode, drawing);
            }

            Maze.MazeNode[] currentNodeNeighborsArray = currentNode.getArrayOfNeighborNodes();
            ArrayList<Maze.MazeNode> currentNodeNeighborsList = new ArrayList<Maze.MazeNode>(Arrays.asList(currentNodeNeighborsArray));
            Collections.shuffle(currentNodeNeighborsList);

            for (Maze.MazeNode neighbor : currentNodeNeighborsList) {
                if ((neighbor != null) && (!neighbor.getVisited())) {
                    neighbor.setVisited(true);
                    neighbor.setParent(currentNode);
                    int[] lastCoords = neighbor.getPosition();
                    int[] currentCoords = currentNode.getPosition();
                    drawing.colorLine(currentCoords[1], currentCoords[0], lastCoords[1], lastCoords[0]);
                    if (!solutionFound) {
                        DFSVisit(neighbor);
                    }
                }
            }
        }
    }
}
