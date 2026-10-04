package Practical_8.P2;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(int quantity) {
        super("quantity must be greater than zero; received " + quantity);
    }
}