package Practical_14.P1;
import java.util.*;
class ItemService {
    private final ArrayList<String> items = new ArrayList<>();
    public void add(String item) { items.add(item); }
    public int count() { return items.size(); }
    public void action(String action) {
        if (!action.equals("add")) throw new IllegalArgumentException("Invalid action");
    }
}
public class LayeredApp {
    public static void main(String[] args) {
        ItemService service = new ItemService();
        service.add("Account");
        service.add("Transaction");
        System.out.println("Items: " + service.count());
        try {
            service.action("wrong");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid action caught");
        }
    }
}
