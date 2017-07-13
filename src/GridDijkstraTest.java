import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridDijkstraTest {
    @Test
    void testSearchTiny() {
        Path tinyMazePath = Paths.get("test-images/tiny.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new  GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(tinyMazePath, start, finish);
        assertEquals(true, reachedEnd, "tiny");
    }

    @Test
    void testSearchSmall() {
        Path smallMazePath = Paths.get("test-images/small.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(smallMazePath, start, finish);
        assertEquals(true, reachedEnd, "small");
    }

    @Test
    void testSearchNormal() {
        Path mediumMazePath = Paths.get("test-images/normal.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(mediumMazePath, start, finish);
        assertEquals(true, reachedEnd, "normal");
    }

    @Test
    void testSearchBraid200() {
        Path b200maze = Paths.get("test-images/braid200.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(b200maze, start, finish);
        assertEquals(true, reachedEnd, "b200");
    }

    @Test
    void testSearchCombo400() {
        Path c400maze = Paths.get("test-images/combo400.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(c400maze, start, finish);
        assertEquals(true, reachedEnd, "c400");
    }

    @Test
    void testSearchNonStardard() {
        Path nonStandard = Paths.get("test-images/test700x700.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(nonStandard, start, finish);
        assertEquals(true, reachedEnd, "non standard");
    }

    @Test
    void testSearchPerfect2k() {
        Path perfect2kMaze = Paths.get("test-images/perfect2k.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(perfect2kMaze, start, finish);
        assertEquals(true, reachedEnd, "perfect2k");
    }

    @Test
    void testSearchBraid2k() {
        Path braid2kMaze = Paths.get("test-images/braid2k.png");
        int[] start = {0,3};
        int[] finish = {9, 7};
        GridDijkstra dijkstraSearch = new GridDijkstra();
        boolean reachedEnd = dijkstraSearch.search(braid2kMaze, start, finish);
        assertEquals(true, reachedEnd, "braid2k");
    }
}