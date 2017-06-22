import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Dijkstra {
    private List<Maze.MazeNode> unsettledNodes = new ArrayList<Maze.MazeNode>();
    private List<Maze.MazeNode> settledNodes = new ArrayList<Maze.MazeNode>();

    protected boolean search(Maze maze) {
        final long START_TIME = System.nanoTime();
        System.out.println("Dijkstra");

        int mazeExit; //called bottom of maze in other algrothims

        // Set up drawing for image output of the solution
        DrawPathThoughMaze drawing = new DrawPathThoughMaze();
        drawing.createColorImage(maze);
        drawing.setOutputImagePath("Dijkstra_" + maze.getMazeMatrixLength());

        // Set the maze exit, the bottom of the input image in this case
        mazeExit = maze.getMazeMatrixLength() - 1;

        // add start node to unsettled list, in this case the node that is at the top of the image
        unsettledNodes.add(maze.getNodes().get(0));

        // continuously look though unsettled list until there are no nodes left or a solution is found
        while (!unsettledNodes.isEmpty()) {
            Maze.MazeNode evaluationNode = getNodeWithLowestDistance();

            if (evaluationNode.getPosition()[0] == mazeExit) { // at end node
                System.out.println("Found End!");
                // follow parents back to start node
                Utils.getAndDrawSolutionPath(evaluationNode, drawing);

                final long ELAPSED_TIME = System.nanoTime() - START_TIME;
                double elapsedTimeInSeconds = (double) ELAPSED_TIME / 1000000000.0;
                System.out.println(elapsedTimeInSeconds + " seconds");
                drawing.writeImageToDisk();

                return true;
            }

            unsettledNodes.remove(evaluationNode);
            settledNodes.add(evaluationNode);
            evaluateNeighbors(evaluationNode);
        }
        return false;
    }

    private Maze.MazeNode getNodeWithLowestDistance() {
        //should change for loop too a fib or binomal heap for improved preformance
        Maze.MazeNode shortestNode = null;
        int shortestDistance = Integer.MAX_VALUE;
        for (Maze.MazeNode node : unsettledNodes) {
            if (node.getDistanceFromStart() <= shortestDistance) {
                shortestDistance = node.getDistanceFromStart();
                shortestNode = node;
            }
        }
        if (shortestNode == null) {
            System.out.print("DEBUG: error: shortestNode == null, Dijkstra.java, getNodeWithLowestDistance should never have been evaluated.");
        }
        return shortestNode;
    }

    private void evaluateNeighbors(Maze.MazeNode evaluationNode) {
        for (Maze.MazeNode neighborNode : evaluationNode.getArrayOfNeighborNodes()) {
            if ((settledNodes.indexOf(neighborNode) == -1)  && (neighborNode != null))  { // if neighborNode is not settled
                int distanceToNeighbor = getDistance(evaluationNode, neighborNode);
                int neighborsDistanceFromStart = evaluationNode.getDistanceFromStart() + distanceToNeighbor;
                if (neighborsDistanceFromStart < neighborNode.getDistanceFromStart()) {
                    neighborNode.setDistanceFromStart(neighborsDistanceFromStart);
                    neighborNode.setParent(evaluationNode);
                    unsettledNodes.add(neighborNode);
                    neighborNode.setParent(evaluationNode);
                }
            }
        }
    }
    private int getDistance(Maze.MazeNode evaluationNode, Maze.MazeNode neighborNode){
         int yDistance = evaluationNode.getPosition()[0] - neighborNode.getPosition()[0];
         int xDistance = evaluationNode.getPosition()[1] - neighborNode.getPosition()[1];
         return Math.abs(yDistance) + Math.abs(xDistance);
    }
}

