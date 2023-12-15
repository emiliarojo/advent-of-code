import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        List<List<Integer>> histories = parseInput("input.txt");
        System.out.println("Part 1 Solution: " + part1Solution(histories));
        System.out.println("Part 2 Solution: " + part2Solution(histories));
    }

    public static List<List<Integer>> parseInput(String filePath) {
        List<List<Integer>> histories = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                List<Integer> history = new ArrayList<>();
                for (String value : scanner.nextLine().split("\\s+")) {
                    history.add(Integer.parseInt(value));
                }
                histories.add(history);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return histories;
    }

    public static int part1Solution(List<List<Integer>> histories) {
        int sum = 0;
        for (List<Integer> historyData : histories) {
            History history = new History(historyData);
            sum += history.getNextValue();
        }
        return sum;
    }

    public static int part2Solution(List<List<Integer>> histories) {
        int sum = 0;
        for (List<Integer> historyData : histories) {
            History history = new History(historyData);
            sum += history.getPreviousValue();
        }
        return sum;
    }
}
