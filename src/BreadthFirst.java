import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class BreadthFirst {
    protected static boolean search(Maze maze) {
        System.out.println("Breadth-First");
        final long START_TIME = System.nanoTime();

        DrawPathThoughMaze drawing = new DrawPathThoughMaze();
        drawing.createColorImage(maze);
        drawing.setOutputImagePath("Breadth-First_" + maze.getMazeMatrixLength());

        List<Maze.MazeNode> nodes = maze.getNodes();
        int bottomOfMaze = maze.getMazeMatrixLength() - 1;
        List<Maze.MazeNode> queue = new ArrayList<Maze.MazeNode>();
        queue.add(nodes.get(0));

        while (!queue.isEmpty()) {
            Maze.MazeNode currentNode = queue.get(0);
            if (currentNode.getPosition()[0] == bottomOfMaze) { // at end node
                final long ELAPSED_TIME = System.nanoTime() - START_TIME;
                double elapsedTimeInSeconds = (double) ELAPSED_TIME / 1000000000.0;
                System.out.println(elapsedTimeInSeconds + " seconds");


                List<int[]> shortestPathArray = Utils.getAndDrawSolutionPath(currentNode, drawing);

                drawing.writeImageToDisk();
                return true;
            }
            for (Maze.MazeNode neighborNode : currentNode.getArrayOfNeighborNodes()) {
                if ((neighborNode != null) && (!neighborNode.getVisited())) {
                    queue.add(neighborNode);
                    neighborNode.setDistanceFromStart(currentNode.getDistanceFromStart() + distanceBetweenTwoNodes(currentNode, neighborNode));
                    neighborNode.setParent(currentNode);
                    int[] lastCoords = neighborNode.getPosition();
                    int[] currentCoords = currentNode.getPosition();
                    drawing.colorLine(currentCoords[1], currentCoords[0], lastCoords[1], lastCoords[0]);
                }
            }
            currentNode.setVisited(true);
            queue.remove(0);
        }
        return false;
    }

    private static int distanceBetweenTwoNodes(Maze.MazeNode node1, Maze.MazeNode node2) {
        int[] coords1 = node1.getPosition();
        int[] coords2 = node2.getPosition();
        return Math.abs((coords1[0] - coords2[0]) + (coords1[1] - coords2[1]));
    }
}
