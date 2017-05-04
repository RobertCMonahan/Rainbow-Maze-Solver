import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class LeftTurnTest {
    private Maze buildTinyMaze(){
        Maze tinyMaze = new Maze();
        tinyMaze.setMazeFilePath(Paths.get("test-images/tiny.png"));
        tinyMaze.constructMazeMatrix();
        tinyMaze.findAndConnectNodes();

        return tinyMaze;
    }

    private Maze buildSmallMaze(){
        Maze smallMaze = new Maze();
        smallMaze.setMazeFilePath(Paths.get("test-images/small.png"));
        smallMaze.constructMazeMatrix();
        smallMaze.findAndConnectNodes();
        return smallMaze;
    }
    private Maze buildNormalMaze(){
        Maze normalMaze = new Maze();
        normalMaze.setMazeFilePath(Paths.get("test-images/normal.png"));
        normalMaze.constructMazeMatrix();
        normalMaze.findAndConnectNodes();
        return normalMaze;
    }
    private Maze buildbraid200Maze(){
        Maze braid200Maze = new Maze();
        braid200Maze.setMazeFilePath(Paths.get("test-images/braid200.png"));
        braid200Maze.constructMazeMatrix();
        braid200Maze.findAndConnectNodes();
        return braid200Maze;
    }

    @Test
    void testTurnSearch() {
        Maze tinyMaze = buildTinyMaze();
        boolean reachedEnd = LeftTurn.search(tinyMaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");

        Maze smaze = buildSmallMaze();
        reachedEnd = LeftTurn.search(smaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");

        Maze nmaze = buildNormalMaze();
        reachedEnd = LeftTurn.search(nmaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");

        Maze p2kmaze = buildbraid200Maze();
        reachedEnd = LeftTurn.search(p2kmaze);
        assertEquals(true, reachedEnd, "If the LeftTurn Search reached the end");
    }


}