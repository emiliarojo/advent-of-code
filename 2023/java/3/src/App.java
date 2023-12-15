import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class App {
    static List<EnginePart> parts = new ArrayList<>();
    static List<Gear> gears = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String FILE_PATH = "input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            List<String> lines = reader.lines().toList();
            System.out.println("Part 1 Solution: " + part1Solution(lines));
            System.out.println("Part 2 Solution: " + part2Solution(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int part1Solution(List<String> lines) {
        int sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j))) {
                    int end = j;
                    while (end < line.length() && Character.isDigit(line.charAt(end))) {
                        end++;
                    }
                    String numberString = line.substring(j, end);
                    int number = Integer.parseInt(numberString);
                    if (EnginePart.isValidPart(lines, i, j, end)) {
                        sum += number;
                        parts.add(new EnginePart(number));
                    }
                    j = end - 1;
                }
            }
        }
        return sum;
    }

    public static long part2Solution(List<String> lines) {
        long total = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '*') {
                    List<Integer> adjacentNumbers = getAdjacentNumbers(lines, i, j);
                    if (adjacentNumbers.size() == 2) {
                        total += (long) adjacentNumbers.get(0) * adjacentNumbers.get(1);
                    }
                }
            }
        }
        return total;
    }

    private static List<Integer> getAdjacentNumbers(List<String> lines, int rowIndex, int colIndex) {
        List<Integer> adjacentNumbers = new ArrayList<>();
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < rowOffsets.length; k++) {
            int newRow = rowIndex + rowOffsets[k];
            int newCol = colIndex + colOffsets[k];

            if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(newRow).length()) {
                if (Character.isDigit(lines.get(newRow).charAt(newCol))) {
                    String numberStr = extractFullNumber(lines, newRow, newCol);
                    if (!numberStr.isEmpty() && !adjacentNumbers.contains(Integer.parseInt(numberStr))) {
                        adjacentNumbers.add(Integer.parseInt(numberStr));
                    }
                }
            }
        }

        return adjacentNumbers;
    }

    private static String extractFullNumber(List<String> lines, int rowIndex, int colIndex) {
        if (!Character.isDigit(lines.get(rowIndex).charAt(colIndex))) {
            return "";
        }

        StringBuilder number = new StringBuilder();
        int currentCol = colIndex;
        while (currentCol >= 0 && Character.isDigit(lines.get(rowIndex).charAt(currentCol))) {
            number.insert(0, lines.get(rowIndex).charAt(currentCol));
            currentCol--;
        }
        currentCol = colIndex + 1;
        while (currentCol < lines.get(rowIndex).length() && Character.isDigit(lines.get(rowIndex).charAt(currentCol))) {
            number.append(lines.get(rowIndex).charAt(currentCol));
            currentCol++;
        }
        return number.toString();
    }
}
