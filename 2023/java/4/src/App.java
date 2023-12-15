import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    static List<Card> cards = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String FILE_PATH = "input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            List<String> lines = reader.lines().toList();
            makeCard(lines);
            System.out.println("Part 1 Solution: " + part1Solution());
            System.out.println("Part 2 Solution: " + part2Solution());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeCard(List<String> lines) {
        for (String cardData : lines) {
            String[] parts = cardData.split(":");
            int cardNumber = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
            cards.add(new Card(cardNumber, parts[1].trim()));
        }
    }

    public static int part1Solution() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.calculateScore();
        }
        return sum;
    }

    public static int part2Solution() {
      Map<Integer, Integer> cardCounts = new HashMap<>();
      for (int i = 0; i < cards.size(); i++) {
          cardCounts.put(i, 1);
      }

      for (int i = 0; i < cards.size(); i++) {
          Card currentCard = cards.get(i);
          int matches = currentCard.calculateMatches();
          for (int j = 1; j <= matches; j++) {
              int copyIndex = i + j;
              if (copyIndex < cards.size()) {
                  cardCounts.put(copyIndex, cardCounts.getOrDefault(copyIndex, 0) + cardCounts.get(i));
              }
          }
      }

      int totalCards = 0;
      for (int count : cardCounts.values()) {
          totalCards += count;
      }

      return totalCards;
  }
}
