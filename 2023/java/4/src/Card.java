import java.util.ArrayList;
import java.util.List;

class Card {
    private int cardNumber;
    private List<Integer> numbersBeforeBar;
    private List<Integer> numbersAfterBar;

    public Card(int cardNumber, String cardData) {
        this.cardNumber = cardNumber;
        String[] parts = cardData.split("\\|");
        this.numbersBeforeBar = convertStringToList(parts[0]);
        this.numbersAfterBar = convertStringToList(parts[1]);
    }

    public Card(Card other) {
        this.cardNumber = other.cardNumber;
        this.numbersBeforeBar = new ArrayList<>(other.numbersBeforeBar);
        this.numbersAfterBar = new ArrayList<>(other.numbersAfterBar);
    }

    private List<Integer> convertStringToList(String data) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : data.trim().split("\\s+")) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public int calculateScore() {
        int matches = 0;
        for (int number : this.numbersAfterBar) {
            if (this.numbersBeforeBar.contains(number)) {
                matches++;
            }
        }
        return matches > 0 ? (int) Math.pow(2, matches - 1) : 0;
    }

    public int calculateMatches() {
        int matches = 0;
        for (int number : this.numbersAfterBar) {
            if (this.numbersBeforeBar.contains(number)) {
                matches++;
            }
        }
        return matches;
    }
}
