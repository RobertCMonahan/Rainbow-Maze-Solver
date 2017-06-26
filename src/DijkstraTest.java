import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
    private Dijkstra dijkstraSearch = new Dijkstra();

    private Maze buildTinyMaze() {
        final long START_TIME_BUILD = System.nanoTime();
        Maze tinyMaze = new Maze();
        tinyMaze.setMazeFilePath(Paths.get("test-images/tiny.png"));
        tinyMaze.constructMazeMatrix();

        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");

        tinyMaze.findAndConnectNodes();

        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");
        return tinyMaze;
    }

    private Maze buildSmallMaze() {
        final long START_TIME_BUILD = System.nanoTime();
        Maze smallMaze = new Maze();
        smallMaze.setMazeFilePath(Paths.get("test-images/small.png"));
        smallMaze.constructMazeMatrix();
        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");

        smallMaze.findAndConnectNodes();
        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");
        return smallMaze;
    }

    private Maze buildNormalMaze() {
        final long START_TIME_BUILD = System.nanoTime();
        Maze normalMaze = new Maze();
        normalMaze.setMazeFilePath(Paths.get("test-images/normal.png"));
        normalMaze.constructMazeMatrix();
        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");
        normalMaze.findAndConnectNodes();
        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");
        return normalMaze;
    }

    private Maze buildbraid200Maze() {
        final long START_TIME_BUILD = System.nanoTime();
        Maze braid200Maze = new Maze();
        braid200Maze.setMazeFilePath(Paths.get("test-images/braid200.png"));
        braid200Maze.constructMazeMatrix();
        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");
        braid200Maze.findAndConnectNodes();
        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");
        return braid200Maze;
    }

    private Maze buildcombo400Maze() {
        final long START_TIME_BUILD = System.nanoTime();
        Maze combo400Maze = new Maze();
        combo400Maze.setMazeFilePath(Paths.get("test-images/combo400.png"));
        combo400Maze.constructMazeMatrix();
        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");
        combo400Maze.findAndConnectNodes();
        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");
        return combo400Maze;
    }

    private Maze buildperfect2kMaze() {
        final long START_TIME_BUILD = System.nanoTime();
        Maze perfect2kMaze = new Maze();
        perfect2kMaze.setMazeFilePath(Paths.get("test-images/perfect2k.png"));
        perfect2kMaze.constructMazeMatrix();
        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");
        perfect2kMaze.findAndConnectNodes();
        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");
        return perfect2kMaze;
    }


    private Maze buildbraid2kMaze() {
        final long START_TIME_BUILD = System.nanoTime();

        Maze braid2kMaze = new Maze();
        braid2kMaze.setMazeFilePath(Paths.get("test-images/braid2k.png"));
        braid2kMaze.constructMazeMatrix();
        final long ELAPSED_TIME_CONST = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds1 = (double) ELAPSED_TIME_CONST / 1000000000.0;
        System.out.println(elapsedTimeInSeconds1 + " seconds to build construct matrix");
        braid2kMaze.findAndConnectNodes();

        final long ELAPSED_TIME_BUILD = System.nanoTime() - START_TIME_BUILD;
        double elapsedTimeInSeconds2 = (double) ELAPSED_TIME_BUILD / 1000000000.0;
        System.out.println(elapsedTimeInSeconds2 + " seconds to build maze");

        return braid2kMaze;

    }

    @Test
    void testSearchTiny() {
        Maze tinyMaze = buildTinyMaze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(tinyMaze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchSmall() {
        Maze smaze = buildSmallMaze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(smaze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchNormal() {
        Maze nmaze = buildNormalMaze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(nmaze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchBraid200() {
        Maze b200maze = buildbraid200Maze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(b200maze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchCombo400() {
        Maze c400maze = buildcombo400Maze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(c400maze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }
    // Still can't solve the larger mazes still not sure why, although it's possible im just not letting it run long enough
    @Test
    void testSearchPerfect2k() {
        Maze perfect2kMaze = buildperfect2kMaze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(perfect2kMaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");
    }

    @Test
    void testSearchBraid2k() {
        Maze braid2kMaze = buildbraid2kMaze();
        Dijkstra dijkstraSearch = new Dijkstra();
        boolean reachedEnd = dijkstraSearch.search(braid2kMaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");
    }
}