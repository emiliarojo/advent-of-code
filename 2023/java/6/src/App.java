import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

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
      List<Race> races = new ArrayList<>();
      List<Integer> times = new ArrayList<>();
      List<Integer> records = new ArrayList<>();

      while (scanner.hasNextLine()) {
          String line = scanner.nextLine().trim();
          if (line.startsWith("Time:")) {
              String[] timeValues = line.substring(5).trim().split("\\s+");
              // System.out.println(timeValues[0]);
              for (String time : timeValues) {
                  times.add(Integer.parseInt(time));
              }
          } else if (line.startsWith("Distance:")) {
              String[] timeValues = line.substring(9).trim().split("\\s+");
              // System.out.println(timeValues[0]);
              for (String record : timeValues) {
                  records.add(Integer.parseInt(record));
              }
          }
      }
      scanner.close();

      for (int i = 0; i < times.size(); i++) {
          races.add(new Race(times.get(i), records.get(i)));
      }

      WaysToWin waystowin = new WaysToWin(races);
      // List<Integer> waysToWinEachRace = waystowin.calculateWaysToWin();
      // System.out.println(waysToWinEachRace);
      long total = waystowin.calculateTotal();

      return total;
    }

    public static long part2Solution(String filePath) throws FileNotFoundException {
      File file = new File(filePath);
      Scanner scanner = new Scanner(file);
      List<Race> races = new ArrayList<>();
      long time = 0;
      long record = 0;

      while (scanner.hasNextLine()) {
          String line = scanner.nextLine().trim();
          if (line.startsWith("Time:")) {
              time = Long.parseLong(line.substring(5).replaceAll("\\s+", ""));
              // System.out.println(time);

          } else if (line.startsWith("Distance:")) {
              record = Long.parseLong(line.substring(9).replaceAll("\\s+", ""));
              // System.out.println(record);
          }
      }
      scanner.close();

      races.add(new Race(time, record));

      WaysToWin waystowin = new WaysToWin(races);
      long total = waystowin.calculateWaysToWinForSingleRace();

      return total;
    }
}
