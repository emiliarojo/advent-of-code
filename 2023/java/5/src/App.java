import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Part 1 Solution: " + part1Solution("input.txt"));
            System.out.println("Part 2 Solution: " + part2Solution("input.txt"));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static long part1Solution(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        Almanac almanac = new Almanac();
        List<Long> seeds = new ArrayList<>();
        String currentMap = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("seeds:")) {
                String[] seedParts = line.substring(6).trim().split(" ");
                for (String seedPart : seedParts) {
                    seeds.add(Long.parseLong(seedPart));
                }
            } else if (line.contains("map:")) {
                currentMap = line.substring(0, line.indexOf("map:")).trim();
            } else if (!line.isEmpty()) {
                String[] parts = line.split(" ");
                long destStart = Long.parseLong(parts[0]);
                long srcStart = Long.parseLong(parts[1]);
                long length = Long.parseLong(parts[2]);
                almanac.addMapping(currentMap, destStart, srcStart, length);
            }
        }
        scanner.close();

        return seeds.stream()
                    .map(almanac::findLocation)
                    .min(Long::compare)
                    .orElseThrow(() -> new RuntimeException("Location not found"));
    }

    public static long part2Solution(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        Almanac almanac = new Almanac();
        List<Range> seedRanges = new ArrayList<>();
        String currentMap = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("seeds:")) {
                String[] seedParts = line.substring(6).trim().split(" ");
                for (int i = 0; i < seedParts.length; i += 2) {
                    long start = Long.parseLong(seedParts[i]);
                    long length = Long.parseLong(seedParts[i + 1]);
                    seedRanges.add(new Range(start, start + length));
                }
            } else if (line.contains("map:")) {
                currentMap = line.substring(0, line.indexOf("map:")).trim();
            } else if (!line.isEmpty()) {
                String[] parts = line.split(" ");
                long destStart = Long.parseLong(parts[0]);
                long srcStart = Long.parseLong(parts[1]);
                long length = Long.parseLong(parts[2]);
                almanac.addMapping(currentMap, destStart, srcStart, length);
            }
        }
        scanner.close();

        return seedRanges.stream()
                         .flatMap(seedRange -> almanac.getMappingStartPoints(seedRange).stream())
                         .map(almanac::findLocation)
                         .min(Long::compare)
                         .orElseThrow(() -> new RuntimeException("Location not found"));
    }
}
