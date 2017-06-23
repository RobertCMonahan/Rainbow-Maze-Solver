import java.nio.file.Path;
import java.util.*;

import static java.util.Arrays.copyOfRange;

class Maze {
    private int[][] mazeMatrix;
    private Path mazeFilePath;

    public int[][] getMazeMatrix() {
        return this.mazeMatrix;
    }

    public int getMazeMatrixLength() {
        return this.mazeMatrix.length;
    }

    public void constructMazeMatrix() {
        this.mazeMatrix = GetRGBFast.convertToIntMatrix(Utils.createBufferedImage(mazeFilePath));
    }

    public void setMazeFilePath(Path filePath) {
        this.mazeFilePath = filePath;
    }

    public void printAllNodeData() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("-----------------");
            System.out.println("node: " + i);
            nodes.get(i).printNodeValues();
        }
    }

    public List<MazeNode> getNodes() {
        return this.nodes;
    }

    public MazeNode searchForNodeByNodePosition(int[] position) {
        for (MazeNode node : this.getNodes()) {
            if (Arrays.equals(node.getPosition(), position)) {
                return node;
            }
        }
        return null;
    }

    public List<MazeNode> nodes = new ArrayList<MazeNode>();



    public void findAndConnectNodes() {
        final int WHITE = 1;
        final int BLACK = 0;

        int horizontalDistanceFromLastNode = 0;
        int[] verticalDistanceFromLastNode = new int[mazeMatrix.length];

        findStart();
        // add a tic for the start position's coordinate so it'll be found by neighbor
        int startNodeXCoordinate = nodes.get(0).getPosition()[1];
        verticalDistanceFromLastNode[startNodeXCoordinate] += 1;


        for (int y = 1; y < (this.mazeMatrix.length - 1); y++) { // skip first and last row

            horizontalDistanceFromLastNode = 0; //clear for each row


            for (int x = 1; x < ((this.mazeMatrix[y].length) - 1); x++) {
                int westPixel = this.mazeMatrix[y][x - 1];
                int currentPixel = this.mazeMatrix[y][x];
                int eastPixel = this.mazeMatrix[y][x + 1];


                if (currentPixel == WHITE) {

                    if (westPixel == WHITE) {
                        if (eastPixel == WHITE) {

                            // white white white
                            // check up and down if either are white create node
                            if ((this.mazeMatrix[y + 1][x] == WHITE) || (this.mazeMatrix[y - 1][x] == WHITE)) {
                                nodes.add(new MazeNode(new int[]{y, x}));
                                connectNeighbors(y, x, horizontalDistanceFromLastNode, verticalDistanceFromLastNode[x]);

                                horizontalDistanceFromLastNode = 0;
                                verticalDistanceFromLastNode[x] = 0;
                            }

                        } else { // eastPixel == Black
                            // white white black
                            nodes.add(new MazeNode(new int[]{y, x}));
                            connectNeighbors(y, x, horizontalDistanceFromLastNode, verticalDistanceFromLastNode[x]);

                            horizontalDistanceFromLastNode = 1;
                            verticalDistanceFromLastNode[x] = 0;
                        }
                        horizontalDistanceFromLastNode += 1; // west is WHITE

                    } else { // westPixel == BLACK
                        if (eastPixel == WHITE) {
                            // black white white
                            nodes.add(new MazeNode(new int[]{y, x}));

                            connectNeighbors(y, x, horizontalDistanceFromLastNode, verticalDistanceFromLastNode[x]);

                            horizontalDistanceFromLastNode = 1;
                            verticalDistanceFromLastNode[x] = 0;

                        } else { // eastPixel == BLACK
                            // black white black
                            if ((this.mazeMatrix[y + 1][x] == BLACK) || (this.mazeMatrix[y - 1][x] == BLACK)) {
                                nodes.add(new MazeNode(new int[]{y, x}));

                                connectNeighbors(y, x, horizontalDistanceFromLastNode, verticalDistanceFromLastNode[x]);

                                horizontalDistanceFromLastNode = 1;
                                verticalDistanceFromLastNode[x] = 0;
                            }
                        }
                    }
                    verticalDistanceFromLastNode[x]++;

                } else {
                    horizontalDistanceFromLastNode = 0; // on wall
                    verticalDistanceFromLastNode[x] = 0;
                }
            }
        }
        findEnd(verticalDistanceFromLastNode);
    }

    private void findStart() {
        int xPositionCounter = 0;
        for (int pixel : this.mazeMatrix[0]) {
            if (pixel == 1) { // white pixel
                MazeNode startNode = new MazeNode(new int[]{0, xPositionCounter});
                startNode.setDistanceFromStart(0);
                nodes.add(startNode);
            }
            xPositionCounter++;
        }
    }

    private void findEnd(int[] verticalDistanceFromLastNode) {
        int xPositionCounter = 0;
        for (int pixel : this.mazeMatrix[mazeMatrix.length - 1]) {
            if (pixel == 1) { // white pixel
                MazeNode endNode = new MazeNode(new int[]{mazeMatrix.length - 1, xPositionCounter});
                nodes.add(endNode);

                connectNeighbors(mazeMatrix.length - 1, xPositionCounter, 0, verticalDistanceFromLastNode[xPositionCounter]);
            }
            xPositionCounter++;
        }
    }

    private void connectNeighbors(int y, int x, int horizontalDistanceFromLastNode, int verticalDistanceFromLastNodeAtCurrentNodesX) {
        if (horizontalDistanceFromLastNode > 0) {
            connectNeighborsInXPlane(y, x, horizontalDistanceFromLastNode);
        }
        if (verticalDistanceFromLastNodeAtCurrentNodesX > 0) {
            connectNeighborsInYPlane(y, x, verticalDistanceFromLastNodeAtCurrentNodesX);
        }
    }

    private void connectNeighborsInXPlane(int y, int x, int horizontalDistanceFromLastNode) {
        int neighborsX = x - horizontalDistanceFromLastNode;
        // add connection neighbor --> current node
        MazeNode currentNode = nodes.get(nodes.size() - 1);
        currentNode.addNeighborNode(3, this.searchForNodeByNodePosition(new int[]{y, neighborsX}));
        // add connection neighbor <-- current node
        MazeNode lastNode = nodes.get(nodes.size() - 2);
        lastNode.addNeighborNode(1, this.searchForNodeByNodePosition(new int[]{y, x}));

    }

    private void connectNeighborsInYPlane(int y, int x, int verticalDistanceFromLastNodeAtCurrentNodesX) {
        int neighborsY = y - verticalDistanceFromLastNodeAtCurrentNodesX;
        // add connection neighbor --> current node
        MazeNode currentNode = nodes.get(nodes.size() - 1);
        currentNode.addNeighborNode(0, this.searchForNodeByNodePosition(new int[]{neighborsY, x}));

        // add connection neighbor <-- current node
        Iterator<MazeNode> nodesIterator = this.nodes.iterator();
        while (nodesIterator.hasNext()) {
            MazeNode aNode = nodesIterator.next();
            if (Arrays.equals(aNode.getPosition(), new int[]{neighborsY, x})) {
                aNode.addNeighborNode(2, this.searchForNodeByNodePosition(new int[]{y, x}));
            }
        }
    }
}
