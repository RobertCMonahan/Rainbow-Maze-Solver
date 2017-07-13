
import java.util.ArrayList;
import java.util.List;

class Dijkstra {
    private List<MazeNode> unsettledNodes = new ArrayList<MazeNode>();
    private List<MazeNode> settledNodes = new ArrayList<MazeNode>();

    private DrawPathThoughMaze drawing = new DrawPathThoughMaze();

    protected boolean search(Maze maze) {
        final long START_TIME = System.nanoTime();
        System.out.println("Dijkstra");

        int mazeExit; //called bottom of maze in other algrothims

        // Set up drawing for image output of the solution
        drawing.setIncrement(3);
        drawing.createColorImage(maze);
        drawing.setOutputImagePath("Dijkstra_" + maze.getMazeMatrixLength());

        // Set the maze exit, the bottom of the input image in this case
        mazeExit = maze.getMazeMatrixLength() - 1;

        // add start node to unsettled list, in this case the node that is at the top of the image
        unsettledNodes.add(maze.getNodes().get(0));

        // continuously look though unsettled list until there are no nodes left or a solution is found
        while (!unsettledNodes.isEmpty()) {
            MazeNode evaluationNode = getNodeWithLowestDistance();

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

    private MazeNode getNodeWithLowestDistance() {
        //should change for loop too a fib or binomal heap for improved preformance
        MazeNode shortestNode = null;
        int shortestDistance = Integer.MAX_VALUE;
        for (MazeNode node : unsettledNodes) {
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

    private void evaluateNeighbors(MazeNode evaluationNode) {
        for (MazeNode neighborNode : evaluationNode.getArrayOfNeighborNodes()) {
            if ((settledNodes.indexOf(neighborNode) == -1)  && (neighborNode != null))  { // if neighborNode is not settled
                int distanceToNeighbor = getDistance(evaluationNode, neighborNode);
                int neighborsDistanceFromStart = evaluationNode.getDistanceFromStart() + distanceToNeighbor;
                if (neighborsDistanceFromStart < neighborNode.getDistanceFromStart()) {
                    neighborNode.setDistanceFromStart(neighborsDistanceFromStart);
                    neighborNode.setParent(evaluationNode);
                    unsettledNodes.add(neighborNode);
                    drawing.colorLine(evaluationNode.getPosition()[1], evaluationNode.getPosition()[0], neighborNode.getPosition()[1], neighborNode.getPosition()[0]);
                }
            }
        }
    }
    private int getDistance(MazeNode evaluationNode, MazeNode neighborNode){
         int yDistance = evaluationNode.getPosition()[0] - neighborNode.getPosition()[0];
         int xDistance = evaluationNode.getPosition()[1] - neighborNode.getPosition()[1];
         return Math.abs(yDistance) + Math.abs(xDistance);
    }
}

