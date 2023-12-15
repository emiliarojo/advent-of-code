public class Card implements Comparable<Card> {
    private final char value;
    private int numericalValue;
    private final boolean isJoker;

    public Card(char value, boolean useJokerRule) {
        this.value = value;
        this.isJoker = (value == 'J');
        setNumericalValue(value, useJokerRule);
    }

    private void setNumericalValue(char value, boolean useJokerRule) {
        if (value == 'J' && useJokerRule) {
            this.numericalValue = 1;
        } else {
            switch (value) {
                case 'A': this.numericalValue = 14; break;
                case 'K': this.numericalValue = 13; break;
                case 'Q': this.numericalValue = 12; break;
                case 'J': this.numericalValue = 11; break;
                case 'T': this.numericalValue = 10; break;
                default: this.numericalValue = Character.getNumericValue(value); break;
            }
        }
    }

    public char getValue() {
        return this.value;
    }

    public int getNumericalValue() {
        return this.numericalValue;
    }

    public boolean isJoker() {
        return isJoker;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.numericalValue, other.numericalValue);
    }
}
