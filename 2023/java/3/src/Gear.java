import java.util.List;
import java.util.ArrayList;

public class Gear {
    private EnginePart part1;
    private EnginePart part2;

    public Gear(EnginePart part1, EnginePart part2) {
        this.part1 = part1;
        this.part2 = part2;
    }

    public long getGearRatio() {
        return (long) part1.getValue() * part2.getValue();
    }

    public static boolean isValidGear(List<String> lines, int rowIndex, int colIndex) {
        if (lines.get(rowIndex).charAt(colIndex) != '*') {
            return false;
        }

        List<EnginePart> adjacentParts = findAdjacentParts(lines, rowIndex, colIndex, App.parts);
        return adjacentParts.size() == 2;
    }

    public static List<EnginePart> findAdjacentParts(List<String> lines, int rowIndex, int colIndex, List<EnginePart> parts) {
        List<EnginePart> adjacentParts = new ArrayList<>();
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < rowOffsets.length; k++) {
            int newRow = rowIndex + rowOffsets[k];
            int newCol = colIndex + colOffsets[k];

            if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(newRow).length()) {
                String numberStr = extractNumber(lines, newRow, newCol);
                if (!numberStr.isEmpty()) {
                    int number = Integer.parseInt(numberStr);
                    EnginePart part = findPartByValue(number, parts);
                    if (part != null && !adjacentParts.contains(part)) {
                        adjacentParts.add(part);
                    }
                }
            }
        }

        return adjacentParts;
    }

    private static String extractNumber(List<String> lines, int rowIndex, int colIndex) {
        StringBuilder number = new StringBuilder();
        while (colIndex < lines.get(rowIndex).length() && Character.isDigit(lines.get(rowIndex).charAt(colIndex))) {
            number.append(lines.get(rowIndex).charAt(colIndex));
            colIndex++;
        }
        return number.toString();
    }

    private static EnginePart findPartByValue(int value, List<EnginePart> parts) {
        for (EnginePart part : parts) {
            if (part.getValue() == value) {
                return part;
            }
        }
        return null;
    }
}
