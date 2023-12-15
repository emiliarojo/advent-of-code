import java.util.List;

public class EnginePart {
    private int value;

    public EnginePart(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isValidPart(List<String> lines, int rowIndex, int startIndex, int endIndex) {
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < rowOffsets.length; k++) {
            for (int i = startIndex; i <= endIndex; i++) {
                int newRow = rowIndex + rowOffsets[k];
                int newCol = i + colOffsets[k];

                if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(newRow).length()) {
                    char adjacentChar = lines.get(newRow).charAt(newCol);
                    if (!Character.isDigit(adjacentChar) && adjacentChar != '.') {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
