import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AStar {
    private List<int[]> unsettledNodes = new ArrayList<>();
    private List<int[]> settledNodes = new ArrayList<>();
    private int[][] matrix;
    private int[] exitCoordinates = new int[]{-1, -1};

    private DrawPathThoughMaze drawing = new DrawPathThoughMaze();

    /*
    each node will be an int[]{x, y, distance from start, cost, parentX, parentY}
    the distance from start will be Integer.MAX_VALUE unless the node has been evaluated
    parentX & parentY will be null until evaluated
     */

    boolean search(Path mazeFilePath) {
        Timer dijkstraTimer = new Timer();
        dijkstraTimer.start();
        System.out.println("A*");

        matrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(mazeFilePath));

        // Set up drawing for image output of the solution
       drawing.setIncrement(3);
       drawing.createColorImage(matrix);
       drawing.setOutputImagePath("A*_" + matrix.length);

        exitCoordinates = autoFindEnd();
        int[] mazeStartLocation = autoFindStart();
        // add start node to unsettled list, in this case the node that is at the top of the image
        int[] mazeStart = {mazeStartLocation[0], mazeStartLocation[1], 0, calculatePathCost(0, mazeStartLocation, exitCoordinates),-1, -1};

        System.out.println("mazeStart" + Arrays.toString(mazeStart));
        unsettledNodes.add(mazeStart);

        // continuously look though unsettled list until there are no nodes left or a solution is found
        while (!unsettledNodes.isEmpty()) {
            int[] evaluationNode = getNodeWithSmallestCost(unsettledNodes);
            if ((evaluationNode[0] == exitCoordinates[0]) && (evaluationNode[1] == exitCoordinates[1])) { // at end node
                System.out.println("Found End!");
                // follow parents back to start node
              //  Utils.getAndDrawSolutionPath(evaluationNode, drawing);

                dijkstraTimer.stopAndReturnTime();
                drawSolutionPath(evaluationNode, settledNodes, drawing);
                drawing.writeImageToDisk();


                return true;
            }
            unsettledNodes.remove(evaluationNode);
            settledNodes.add(evaluationNode);
            System.out.println("settle node: " + Arrays.toString(evaluationNode));
            evaluateNeighbors(evaluationNode);
        }
        return false;
    }

    /**
     * searches arraylist for the node in from a given arraylist with the smallest value for the distance to start (int[2])
     * @param arraylist the list of int[] that you want to search
     * @return int[] node with the smallest int[2] value
     */
    private int[] getNodeWithSmallestCost(List<int[]> arraylist) {
        //should change for loop too a fib or binomal heap for improved preformance
        int[] nodeWithSmallestCost = null;
        int smallestCost = Integer.MAX_VALUE;
        for (int[] node : arraylist) {
            if (node[3] <= smallestCost) {
                smallestCost = node[3];
                nodeWithSmallestCost = node;
            }
        }
        if (nodeWithSmallestCost == null) {
            System.out.print("DEBUG: error: shortestNode == null, Dijkstra.java, getNodeWithLowestDistance should never have been evaluated.");
        }
        return nodeWithSmallestCost;
    }

    /**
     * get neighbors and evaluate if they can be added to the unsettled list
     * @param evaluationNode node that you want to put though A*
     */
    private void evaluateNeighbors(int[] evaluationNode) {
        for (int[] neighborNode : getArrayOfNeighborNodes(evaluationNode)) {

            int indexOfNodeInSettledNodes = arraylistIndexOfElementWithIntsXY(settledNodes, neighborNode[0], neighborNode[1]);
            if ((indexOfNodeInSettledNodes == -1) && (!arrayContainsOnlyZeros(neighborNode))) { // if neighborNode is not settled && is not empty

                    int indexOfNodeInUnsettledNodes = arraylistIndexOfElementWithIntsXY(unsettledNodes, neighborNode[0], neighborNode[1]);
                    if ((indexOfNodeInUnsettledNodes != -1) && (neighborNode[2] < unsettledNodes.get(indexOfNodeInUnsettledNodes)[2])){
                        unsettledNodes.remove(indexOfNodeInUnsettledNodes);
                    }
                    unsettledNodes.add(neighborNode);

                    System.out.println("add node to unsettledNodes: " + Arrays.toString(neighborNode));
                    drawing.colorLine(evaluationNode[0], evaluationNode[1], neighborNode[0], neighborNode[1]);
                }
        }
    }

    /**
     * searches an arraylist of int[] and checks each element in the list if int[0] matches the x value && if int[1]
     * matches the y value. If there is a match then the index of that int[] is returned. If the value is not found then
     * -1 is returned.
     * @param arraylist the list of int[] that you want to search
     * @param x the value your looking for in the position int[0]
     * @param y the value your looking for in the position int[1]
     * @return the index of the int[] if a match was found or -1 if no match was found.
     */
    private int arraylistIndexOfElementWithIntsXY(List<int[]> arraylist, int x, int y){
        int index = 0;
        for (int[] node : arraylist){
            if((node[0] == x) && (node[1] == y)){
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * checks int[] for any ints other than 0
     * @param array the int[] you would like to check
     * @return true is the array only contains 0's, false if any of the ints are non-zero
     */
    private boolean arrayContainsOnlyZeros(int[] array){
        for (int integer : array){
            if(integer != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * creates an array of nodes (int[]) for the 4 cardinal directions from the node that is given.
     * each direction consists of int{x, y, distance from start, cost heuristic, parent x, parent y}.
     * If the node is a PATH (meaning that x,y in the matrix == 1) then the node is copied into the array of neighbors.
     * If the node is a WALL (meaning that x,y in the matrix == 0) the neighbor is left as and array of 5 0's.
     *
     * @param node int[] in the format int{x, y, distance from start, cost heuristic,  parent x, parent y} that you want to find the
     *             neighbors of.
     * @return int[][] nodes of the 4 cardinal directions from the given node
     */
    private int[][] getArrayOfNeighborNodes(int[] node){
        final int PATH = 1;
        int[][] neighbors = new int[4][6];
        int[] east = {node[0], node[1]-1, node[2]+1, calculatePathCost(node[2]+1, new int[]{node[0], node[1]-1}, exitCoordinates), node[0], node[1]};
        int[] north = {node[0]-1, node[1], node[2]+1, calculatePathCost(node[2]+1, new int[]{node[0]-1, node[1]}, exitCoordinates),node[0], node[1]};
        int[] west = {node[0], node[1]+1, node[2]+1, calculatePathCost(node[2]+1, new int[]{node[0], node[1]+1}, exitCoordinates),node[0], node[1]};
        int[] south = {node[0]+1, node[1], node[2]+1, calculatePathCost(node[2]+1, new int[]{node[0]+1, node[1]}, exitCoordinates),node[0], node[1]};

        if ((north[1] >= 0) && (north[1] <= matrix.length-1) && (north[0] >= 0) && (north[0] <= matrix[0].length-1) && (matrix[north[1]][north[0]] == PATH)){
            System.arraycopy( north, 0, neighbors[0], 0, 6 );
        }
        if ((east[1] > 0) && (east[1] <= matrix.length-1) && (east[0] >= 0) && (east[0] <= matrix[0].length-1) && (matrix[east[1]][east[0]] == PATH)){
            System.arraycopy( east, 0, neighbors[1], 0, 6 );
        }
        if ((south[1] > 0) && (south[1] <= matrix.length-1) && (south[0] >= 0) && (south[0] <= matrix[0].length-1) && (matrix[south[1]][south[0]] == PATH)){
            System.arraycopy( south, 0, neighbors[2], 0, 6 );
        }
        if ((west[1] > 0) && (west[1] <= matrix.length-1) && (west[0] >= 0) && (west[0] <= matrix[0].length-1) && (matrix[west[1]][west[0]] == PATH)){
            System.arraycopy( west, 0, neighbors[3], 0, 6 );
        }
        System.out.println("neighbors to " + Arrays.toString(node) + " = " + Arrays.deepToString(neighbors));
        return neighbors;
    }

    private void drawSolutionPath(int[] exitNode, List<int[]> settledNodes, DrawPathThoughMaze drawing) {
        drawing.setColorToGreen();
        int[] currentNode = exitNode;
        while (currentNode[2] > 0) {
            int[] parentNode = getParentNode(currentNode, settledNodes);
            assert parentNode != null;
            drawing.colorLine(currentNode[0], currentNode[1], parentNode[0], parentNode[1]);
            currentNode = parentNode;
        }
    }

    private int[] getParentNode(int[] node, List<int[]> settledNodes){
        System.out.println(arraylistIndexOfElementWithIntsXY(settledNodes, node[4], node[5]));
        int index = arraylistIndexOfElementWithIntsXY(settledNodes, node[4], node[5]);
        if (index >= 0){
            return settledNodes.get(index);
        }
        return null;
    }

    private int[] autoFindStart() {
        int xPositionCounter = 0;
        for (int pixel : matrix[0]) {
            if (pixel == 1) { // white pixel
                return new int[]{xPositionCounter, 0};
            }
            xPositionCounter++;
        }
        return new int[-1];
    }
    private int[] autoFindEnd() {
        int xPositionCounter = 0;
        for (int pixel : matrix[matrix.length-1]) {
            if (pixel == 1) { // white pixel
                return new int[]{xPositionCounter, matrix.length-1};
            }
            xPositionCounter++;
        }
        return new int[-1];
    }
    private int calculatePathCost(int nodesDistanceFromStart, int[] nodeCoordinates, int[] exitCoordinates){
        return nodesDistanceFromStart + Math.abs(nodeCoordinates[0] - exitCoordinates[0]) + Math.abs(nodeCoordinates[1] - exitCoordinates[1]);
    }
}
