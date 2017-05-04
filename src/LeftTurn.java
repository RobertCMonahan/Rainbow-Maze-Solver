import java.util.Arrays;
import java.util.List;


/**
 * Naive Left Turn solver
 */
class LeftTurn {
    protected static boolean search(Maze maze) {
        final long START_TIME = System.nanoTime();
        System.out.println("Left Turn");

        DrawPathThoughMaze drawing = new DrawPathThoughMaze();
        drawing.createColorImage(maze);
        drawing.setOutputImagePath("Turn-Left_" + maze.getMazeMatrixLength());


        int facing = 0; // n=0, e=1, s=2, w=3
        List<Maze.MazeNode> nodes = maze.getNodes();
        Maze.MazeNode currentNode = nodes.get(0);
        int bottomOfMaze = maze.getMazeMatrixLength()-1;

        long start = System.currentTimeMillis();
        long end = start + 15*1000; // 20 seconds * 1000 ms/sec
        while (System.currentTimeMillis() < end) {

            //look for forward neighbor
            if(currentNode.getArrayOfNeighbors()[facing] != null){
                //find the node i need to move to and move
                System.out.println("At Position: "+Arrays.toString(currentNode.getPosition()));

                Maze.MazeNode lastNode = currentNode;
                currentNode = maze.searchForNodeByNodePosition(currentNode.getANeighborsYX(facing));

                int[] lastCoords = lastNode.getPosition();
                int[] currentCoords = currentNode.getPosition();
                drawing.colorLine(currentCoords[1], currentCoords[0], lastCoords[1], lastCoords[0]);

                if (currentNode.getPosition()[0] == bottomOfMaze){
                    System.out.println("mission complete");
                    final long elapsedTime = System.nanoTime() - START_TIME;
                    double elapsedTimeInSeconds = (double)elapsedTime / 1000000000.0;
                    System.out.println(elapsedTimeInSeconds + " seconds");

                    drawing.writeImageToDisk();

                    return true;
                }
                if(facing > 0){
                    facing --;
                } else {
                    facing = 3;
                }

            } else {
                //if null neighbor
                // face left (if north face east, if east face south, etc)
                if(facing <= 2){
                    facing ++;
                } else {
                    facing = 0;
                }
            }
            //repeat
        }
        System.out.println("mission failed");
        return false;
        //final long duration = System.nanoTime() - START_TIME;
        //System.out.println(duration);
    }
}
