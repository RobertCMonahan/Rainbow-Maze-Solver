import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

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
        boolean reachedEnd = Dijkstra.search(tinyMaze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchSmall() {
        Maze smaze = buildSmallMaze();
        boolean reachedEnd = Dijkstra.search(smaze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchNormal() {
        Maze nmaze = buildNormalMaze();
        boolean reachedEnd = Dijkstra.search(nmaze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchBraid200() {
        Maze b200maze = buildbraid200Maze();
        boolean reachedEnd = Dijkstra.search(b200maze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchCombo400() {
        Maze c400maze = buildcombo400Maze();
        boolean reachedEnd = Dijkstra.search(c400maze);
        assertEquals(true, reachedEnd, "If the DepthFirst Search reached the end");
    }

    @Test
    void testSearchBraid2k() {
        Maze braid2kMaze = buildbraid2kMaze();
        boolean reachedEnd = Dijkstra.search(braid2kMaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");
    }
}