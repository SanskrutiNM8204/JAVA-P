package Practical_8.P2;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final Map<String, Integer> inventory;

    public Warehouse(Map<String, Integer> initialInventory) {
        inventory = new HashMap<>(initialInventory);
    }

    public void issue(String item, int quantity)
            throws OutOfStockException, InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        int available = inventory.getOrDefault(item, 0);
        if (quantity > available) {
            throw new OutOfStockException(quantity - available);
        }

        inventory.put(item, available - quantity);
    }

    public Map<String, Integer> getInventory() {
        return Map.copyOf(inventory);
    }
}