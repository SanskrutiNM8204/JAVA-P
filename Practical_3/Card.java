import java.util.Objects;

public class Card {
    private String rank;
    private String suit;
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    public String toString() {
        return rank + " of " + suit;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card c = (Card) o;
        return Objects.equals(rank, c.rank) && Objects.equals(suit, c.suit);
    }
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
