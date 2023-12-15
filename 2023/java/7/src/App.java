import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Part 1 Solution: " + calculateTotalWinnings("input.txt", false));
            System.out.println("Part 2 Solution: " + calculateTotalWinnings("input.txt", true));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static long calculateTotalWinnings(String filePath, boolean useJoker) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        List<Hand> hands = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            Hand hand = new Hand(line[0], Integer.parseInt(line[1].trim()), useJoker);
            hands.add(hand);
        }
        scanner.close();

        Collections.sort(hands, Collections.reverseOrder());

        long total = 0;
        int rank = 1;
        for (Hand hand : hands) {
            hand.getCards().forEach(card -> System.out.print(card.getValue()));
            System.out.println(" " + hand.getBid() + " " + hand.getHandValue());
            total += (hand.getBid() * rank);
            rank++;
        }
        return total;
    }
}
