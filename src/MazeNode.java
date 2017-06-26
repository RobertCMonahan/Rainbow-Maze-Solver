import java.util.Arrays;

class MazeNode {
    private int[] position = new int[2]; // y, x
    private MazeNode[] neighborNodes = new MazeNode[4]; // MazeNode[north, east, south, west]
    private int distanceFromStart = Integer.MAX_VALUE; // the distance is "infinity" until it is looked at and overridden
    private MazeNode parent = null;

    protected MazeNode(int[] positionVal) {
        position = positionVal;
    }

    protected void printNodeValues() {
        System.out.println("Position: " + Arrays.toString(this.position));
        System.out.println("Distance from start: " + distanceFromStart);
        System.out.println("Neighbor Nodes: ");
        for (MazeNode neighbor : neighborNodes) {
            System.out.println(neighbor);
        }
    }

    protected int[] getPosition() {
        return this.position;
    }

    protected MazeNode[] getArrayOfNeighborNodes() {
        return this.neighborNodes;
    }

    protected void addNeighborNode(int cardinalDirectionIndex, MazeNode neighborNode) {
        this.neighborNodes[cardinalDirectionIndex] = neighborNode;
    }

    protected int getDistanceFromStart() {
        return this.distanceFromStart;
    }

    protected void setDistanceFromStart(int input) {
        this.distanceFromStart = input;
    }

    protected void setParent(MazeNode parentNode) {
        this.parent = parentNode;
    }

    protected MazeNode getParent() {
        return this.parent;
    }


}