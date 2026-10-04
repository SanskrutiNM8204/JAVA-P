import java.util.Scanner;

public class CDriver {
    public static void main (String []args) {
    Card[] cards = new Card[5];
    String[][] data = {
        {"Ace", "Hearts"},
        {"King", "Spades"},
        {"Ace", "Hearts"},
        {"Two", "Clubs"},
        {"Ten", "Diamonds"}
    };
    int added = 0;
    boolean duplicateReported = false;
    for (int i = 0; i < data.length; i++) {
        Card c = new Card(data[i][0], data[i][1]);
        boolean seen = false;
        for (int j = 0; j < added; j++) {
            if (c.equals(cards[j])) {
                seen = true;
                if (!duplicateReported) {
                    System.out.println("Duplicate found: " + c);
                    duplicateReported = true;
                }
                break;
            }
        }
        if (!seen) {
            cards[added++] = c;
            System.out.println(c);
        }
    }
}
}