import java.nio.file.Path;


class DepthFirst {
    protected static void search(Maze maze) {
        System.out.println("Depth-First");

        final long START_TIME = System.nanoTime();

        DrawPathThoughMaze drawing = new DrawPathThoughMaze();
        drawing.createColorImage(maze);
        drawing.setOutputImagePath("Depth-First_" + maze.getMazeMatrixLength());

    }
}
