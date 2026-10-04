import java.util.Scanner;               // import Scanner to read user input

public class VendingMachine {           // main class for the vending machine program
    enum Coin {                         // enum defining accepted coin types
        ONE, TWO, FIVE, TEN             
    }

    private static Coin Coin(String s) { 
        if (s == null) 
            return null;                        // return null immediately for null input
        for (Coin c : Coin.values()) {          // iterate over all Coin enum values
            if (c.name().equalsIgnoreCase(s)) 
                return c;                       // case-insensitive match: return matching coin
        }
        return null;                            // no matching coin found
    }

    public static void main(String[] args) {        // program entry point
        int price = 15;                             // price of the item in the vending machine
        int total = 0;                                // accumulator for total inserted amount
        Scanner scanner = new Scanner(System.in);   // scanner to read tokens from standard input

        while (total < price) {                     // loop until enough money has been inserted
            String input = scanner.next().trim().toUpperCase(); // read next token, trim whitespace, convert to uppercase
            System.out.println("Inserted coin: " + input);   // take input
            Coin coin = Coin(input);                    // coin input is parsed into the Coin enum; null if invalid
            if (coin == null) {                         // if parsing failed
                System.out.println("Invalid coin: " + input); // inform user about invalid coin
                continue;                               // skip to next iteration to accept another input
            }

            int value = switch (coin) {                 // convert coin enum into its numeric value
                case ONE -> 1;                          // ONE represents value 1
                case TWO -> 2;                          // TWO represents value 2
                case FIVE -> 5;                          // FIVE represents value 5
                case TEN -> 10;                         // TEN represents value 10
            };                                          // end of switch expression producing the coin's integer value
            System.out.println("Value of coin: " + value); // print numeric value of the accepted coin

            total += value;                                     // add the coin's value to the running total
            if (total < price) {                                // if still short of the price
                System.out.println("Total so far: " + total);    // display running total to the user
            }
        }

        System.out.println("Paid. Change: " + (total - price));     // payment complete; show change due
        scanner.close();                                            // close the scanner to release system resources
    }
}
