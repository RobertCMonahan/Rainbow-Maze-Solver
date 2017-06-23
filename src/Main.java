import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        final long startTime = System.nanoTime();

        if (args[0].equals("-help") || args[0].equals("-h")) { //flagged words
            PrintHelpMessage.help();

        } else if (args.length == 1) { //not enough args
            PrintHelpMessage.help();

        } else if (args.length == 2) { // file search
            String pathfinderAlgorithm = args[0].toLowerCase();
            String mazeFilename = args[1];
            Path mazeFilePath = Paths.get(mazeFilename);
            Maze mazeToSolve = new Maze();
            mazeToSolve.setMazeFilePath(Paths.get(mazeFilename));
            mazeToSolve.constructMazeMatrix();
            Dijkstra dijkstraSearch = new Dijkstra();
            dijkstraSearch.search(mazeToSolve);

        } else { // too many args
            PrintHelpMessage.help();

        }
        // get total run time of program
        final long duration = System.nanoTime() - startTime;
        System.out.println(duration);
    }
}
