import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class GridDijkstra {
    private List<int[]> unsettledNodes = new ArrayList<>();
    private List<int[]> settledNodes = new ArrayList<>();
    private int[][] matrix;

    private DrawPathThoughMaze drawing = new DrawPathThoughMaze();

    /*
    each node will be an int[]{x, y, distanceFromStart, parentX, parentY}
    the distance from start will be Integer.MAX_VALUE unless the node has been evaluated
    parentX & parentY will be null until evaluated
     */

    boolean search(Path mazeFilePath, int[] mazeExit, int[] mazeStart) {
        Timer dijkstraTimer = new Timer();
        dijkstraTimer.start();
        System.out.println("Dijkstra");

        matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(mazeFilePath));

        // Set up drawing for image output of the solution
//        drawing.setIncrement(3);
//        drawing.createColorImage(maze);
//        drawing.setOutputImagePath("Dijkstra_" );

        // add start node to unsettled list, in this case the node that is at the top of the image
        unsettledNodes.add(mazeStart);

        // continuously look though unsettled list until there are no nodes left or a solution is found
        while (!unsettledNodes.isEmpty()) {
            int[] evaluationNode = getNodeWithLowestDistance();

            if ((evaluationNode[0] == mazeExit[0]) && (evaluationNode[1] == mazeExit[1])) { // at end node
                System.out.println("Found End!");
                // follow parents back to start node
              //  Utils.getAndDrawSolutionPath(evaluationNode, drawing);

                dijkstraTimer.stopAndReturnTime();
                drawing.writeImageToDisk();

                return true;
            }
            unsettledNodes.remove(evaluationNode);
            settledNodes.add(evaluationNode);
            evaluateNeighbors(evaluationNode);
        }
        return false;
    }

    private int[] getNodeWithLowestDistance() {
        //should change for loop too a fib or binomal heap for improved preformance
        int[] shortestNode = null;
        int shortestDistance = Integer.MAX_VALUE;
        for (int[] node : unsettledNodes) {
            if (node[2] <= shortestDistance) {
                shortestDistance = node[2];
                shortestNode = node;
            }
        }
        if (shortestNode == null) {
            System.out.print("DEBUG: error: shortestNode == null, Dijkstra.java, getNodeWithLowestDistance should never have been evaluated.");
        }
        return shortestNode;
    }

    private void evaluateNeighbors(int[] evaluationNode) {
        for (int[] neighborNode : getArrayOfNeighborNodes(evaluationNode)) {
            if ((settledNodes.indexOf(neighborNode) == -1)  && (neighborNode != null))  { // if neighborNode is not settled
                int distanceToNeighbor = 1;
                int neighborsDistanceFromStart = evaluationNode[2] + distanceToNeighbor;
                if (neighborsDistanceFromStart < neighborNode[2]) {
                    neighborNode[2] = (neighborsDistanceFromStart); //set neighborNodes distance from start
                    neighborNode[3] = evaluationNode[0]; //set neighborNodes parentX as evaluationNodes x
                    neighborNode[4] = evaluationNode[1]; //set neighborNodes parentY as evaluationNodes y
                    unsettledNodes.add(neighborNode);
                    drawing.colorLine(evaluationNode[0], evaluationNode[1], neighborNode[0], neighborNode[1]);
                }
            }
        }
    }

    private int[][] getArrayOfNeighborNodes(int[] node){
        final int PATH = 1;
        int[][] neighbors = {null, null, null, null};
        int[] north = {node[0], node[1]-1};
        int[] east = {node[0]-1, node[1]};
        int[] south = {node[0], node[1]+1};
        int[] west = {node[0]+1, node[1]};

        if (matrix[north[1]][north[0]] == PATH){
            neighbors[0] = north;
        }
        if (matrix[east[1]][east[0]] == PATH){
            neighbors[0] = east;
        }
        if (matrix[south[1]][south[0]] == PATH){
            neighbors[0] = south;
        }
        if (matrix[west[1]][west[0]] == PATH){
            neighbors[0] = west;
        }

        return neighbors;
    }

}
