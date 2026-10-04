package Practical_8.P2;

public class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(int shortfall) {
        super("short by " + shortfall + " item(s)");
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}