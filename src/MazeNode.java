import java.util.Arrays;

class MazeNode {
    private int[] position = new int[2]; // y, x
    private MazeNode[] neighborNodes = new MazeNode[4]; // MazeNode[north, east, south, west]
    private int distanceFromStart = Integer.MAX_VALUE;
    private boolean visited = false;
    private MazeNode parent = null;

    public MazeNode(int[] positionVal) {
        position = positionVal;
    }

    public void printNodeValues() {
        System.out.println("Position: " + Arrays.toString(this.position));
        System.out.println("Distance from start: " + distanceFromStart);
        System.out.println("Visited: " + visited);
        System.out.println("Neighbor Nodes: ");
        for (MazeNode neighbor : neighborNodes) {
            System.out.println(neighbor);
        }
    }

    public int[] getPosition() {
        return this.position;
    }

    public MazeNode[] getArrayOfNeighborNodes() {
        return this.neighborNodes;
    }

    public void addNeighborNode(int cardinalDirectionIndex, MazeNode neighborNode) {
        this.neighborNodes[cardinalDirectionIndex] = neighborNode;
    }

    public int getDistanceFromStart() {
        return this.distanceFromStart;
    }

    public void setDistanceFromStart(int input) {
        this.distanceFromStart = input;
    }

    public void setParent(MazeNode parentNode) {
        this.parent = parentNode;
    }

    public MazeNode getParent() {
        return this.parent;
    }


}