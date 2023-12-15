import java.util.*;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private int handValue;
    private final long bid;

    public Hand(String cardString, long bid, boolean useJoker) {
        this.cards = new ArrayList<>();
        for (char c : cardString.toCharArray()) {
            this.cards.add(new Card(c, useJoker));
        }
        if (useJoker) {
            determineHandValueWithJoker();
        } else {
            determineHandValue();
        }
        this.bid = bid;
    }

    private void determineHandValue() {
        Map<Character, Integer> cardCounts = new HashMap<>();
        for (Card card : this.cards) {
            char cardValue = card.getValue();
            cardCounts.put(cardValue, cardCounts.getOrDefault(cardValue, 0) + 1);
        }

        if (cardCounts.containsValue(5)) {
            this.handValue = 7; // Five of a kind
        } else if (cardCounts.containsValue(4)) {
            this.handValue = 6; // Four of a kind
        } else if (cardCounts.containsValue(3) && cardCounts.containsValue(2)) {
            this.handValue = 5; // Full house
        } else if (cardCounts.containsValue(3)) {
            this.handValue = 4; // Three of a kind
        } else if (cardCounts.values().stream().filter(count -> count == 2).count() == 2) {
            this.handValue = 3; // Two pair
        } else if (cardCounts.containsValue(2)) {
            this.handValue = 2; // One pair
        } else {
            this.handValue = 1; // High card
        }
    }

    private void determineHandValueWithJoker() {

        Map<Character, Integer> cardCounts = new HashMap<>();

        for (Card card : this.cards) {
            char cardValue = card.getValue();
            if (cardCounts.containsKey(cardValue) ){
                cardCounts.put(cardValue, cardCounts.get(cardValue) + 1);
            } else {
                cardCounts.put(cardValue, 1);
            }
        }

        String pattern = createPattern(cardCounts);

        if (pattern.equals("5")) {
            this.handValue = 7; // Five of a kind
        } else if (pattern.equals("41")) {
            this.handValue = 6; // Four of a kind
        } else if (pattern.equals("32")) {
            this.handValue = 5; // Full house
        } else if (pattern.equals("311")) {
            this.handValue = 4; // Three of a kind
        } else if (pattern.equals("221")) {
            this.handValue = 3; // Two pair
        } else if (pattern.equals("2111")) {
            this.handValue = 2; // One pair
        } else {
            this.handValue = 1;
        }
    }

    private String createPattern(Map<Character, Integer> cardCounts) {
        int jokerCount = cardCounts.getOrDefault('J', 0);
        cardCounts.remove('J');

        List<Integer> counts = new ArrayList<>(cardCounts.values());
        Collections.sort(counts, Collections.reverseOrder());

        for (int i = 0; i < jokerCount; i++) {
            if (counts.isEmpty()) {
                counts.add(1);
            } else {
                counts.set(0, counts.get(0) + 1);
                Collections.sort(counts, Collections.reverseOrder());
            }
        }
        StringBuilder patternBuilder = new StringBuilder();
        for (int count : counts) {
            patternBuilder.append(count);
        }
        return patternBuilder.toString();
    }


    public long getBid() {
        return this.bid;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getHandValue() {
        return this.handValue;
    }

    @Override
    public int compareTo(Hand other) {
        if (this.handValue != other.handValue) {
            return Integer.compare(other.handValue, this.handValue);
        } else {
            for (int i = 0; i < this.cards.size(); i++) {
                Card thisCard = this.cards.get(i);
                Card otherCard = other.cards.get(i);
                if (thisCard.isJoker() && !otherCard.isJoker()) {
                    return 1;
                } else if (!thisCard.isJoker() && otherCard.isJoker()) {
                    return -1;
                }
                int comparison = Integer.compare(otherCard.getNumericalValue(), thisCard.getNumericalValue());
                if (comparison != 0) {
                    return comparison;
                }
            }
            return 0;
        }
    }
}
