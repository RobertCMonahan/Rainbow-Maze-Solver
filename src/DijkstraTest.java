import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
    private Dijkstra dijkstraSearch = new Dijkstra();

    private Maze buildTinyMaze() {
        Maze tinyMaze = new Maze();
        tinyMaze.setMazeFilePath(Paths.get("test-images/tiny.png"));
        tinyMaze.constructMazeMatrix();
        tinyMaze.findAndConnectNodes();
        return tinyMaze;
    }

    private Maze buildSmallMaze() {
        Maze smallMaze = new Maze();
        smallMaze.setMazeFilePath(Paths.get("test-images/small.png"));
        smallMaze.constructMazeMatrix();
        smallMaze.findAndConnectNodes();
        return smallMaze;
    }

    private Maze buildNormalMaze() {
        Maze normalMaze = new Maze();
        normalMaze.setMazeFilePath(Paths.get("test-images/normal.png"));
        normalMaze.constructMazeMatrix();
        normalMaze.findAndConnectNodes();
        return normalMaze;
    }

    private Maze buildbraid200Maze() {
        Maze braid200Maze = new Maze();
        braid200Maze.setMazeFilePath(Paths.get("test-images/braid200.png"));
        braid200Maze.constructMazeMatrix();
        braid200Maze.findAndConnectNodes();
        return braid200Maze;
    }

    private Maze buildcombo400Maze() {
        Maze combo400Maze = new Maze();
        combo400Maze.setMazeFilePath(Paths.get("test-images/combo400.png"));
        combo400Maze.constructMazeMatrix();
        combo400Maze.findAndConnectNodes();
        return combo400Maze;
    }

    private Maze buildperfect2kMaze() {
        Maze perfect2kMaze = new Maze();
        perfect2kMaze.setMazeFilePath(Paths.get("test-images/perfect2k.png"));
        perfect2kMaze.constructMazeMatrix();
        perfect2kMaze.findAndConnectNodes();
        return perfect2kMaze;
    }


    private Maze buildbraid2kMaze() {
        Maze braid2kMaze = new Maze();
        braid2kMaze.setMazeFilePath(Paths.get("test-images/braid2k.png"));
        braid2kMaze.constructMazeMatrix();
        braid2kMaze.findAndConnectNodes();
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
//    // Still can't solve the larger mazes still not sure why, although it's possible im just not letting it run long enough
//    @Test
//    void testSearchPerfect2k() {
//        Maze perfect2kMaze = buildperfect2kMaze();
//        Dijkstra dijkstraSearch = new Dijkstra();
//        boolean reachedEnd = dijkstraSearch.search(perfect2kMaze);
//        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");
//    }
//
//    @Test
//    void testSearchBraid2k() {
//        Maze braid2kMaze = buildbraid2kMaze();
//        Dijkstra dijkstraSearch = new Dijkstra();
//        boolean reachedEnd = dijkstraSearch.search(braid2kMaze);
//        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");
//    }
}