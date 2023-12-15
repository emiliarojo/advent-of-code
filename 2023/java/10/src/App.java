import java.nio.file.*;
import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            Grid grid = new Grid(lines);
            int maxDistance = grid.findMaxDistance();
            System.out.println("Part 1 Solution: " + maxDistance);

            Loop loop = grid.getLoop();
            List<Pipe> loopPipes = loop.getLoopPipes();

            int tilesInsideLoop = grid.countTilesInsideLoop(loopPipes);
            System.out.println("Part 2 Solution: " + tilesInsideLoop);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
