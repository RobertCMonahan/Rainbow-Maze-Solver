import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AStarTest {


    @Test
    void testSearchSimple() {
        Path simple = Paths.get("test-images/simple_600x600.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(simple);
        assertEquals(true, reachedEnd, "simple_600x600.png");
    }

    @Test
    void testSearchDiagonal() {
        Path diagonal = Paths.get("test-images/test615x614.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(diagonal);
        assertEquals(true, reachedEnd, "test615x614.png");
    }

    @Test
    void testSearchTiny() {
        Path tinyMazePath = Paths.get("test-images/tiny.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(tinyMazePath);
        assertEquals(true, reachedEnd, "tiny");
    }

    @Test
    void testSearchSmall() {
        Path smallMazePath = Paths.get("test-images/small.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(smallMazePath);
        assertEquals(true, reachedEnd, "small");
    }

    @Test
    void testSearchNormal() {
        Path mediumMazePath = Paths.get("test-images/normal.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(mediumMazePath);
        assertEquals(true, reachedEnd, "normal");
    }

    @Test
    void testSearchBraid200() {
        Path b200maze = Paths.get("test-images/braid200.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(b200maze);
        assertEquals(true, reachedEnd, "b200");
    }

    @Test
    void testSearchCombo400() {
        Path c400maze = Paths.get("test-images/combo400.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(c400maze);
        assertEquals(true, reachedEnd, "c400");
    }

    @Test
    void testSearchNonStardard() {
        Path nonStandard = Paths.get("test-images/test700x700.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(nonStandard);
        assertEquals(true, reachedEnd, "non standard");
    }

    @Test
    void testSearchPerfect2k() {
        Path perfect2kMaze = Paths.get("test-images/perfect2k.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(perfect2kMaze);
        assertEquals(true, reachedEnd, "perfect2k");
    }

    @Test
    void testSearchBraid2k() {
        Path braid2kMaze = Paths.get("test-images/braid2k.png");
        AStar dijkstraSearch = new AStar();
        boolean reachedEnd = dijkstraSearch.search(braid2kMaze);
        assertEquals(true, reachedEnd, "braid2k");
    }
}