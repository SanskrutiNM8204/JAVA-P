package Practical_12.P2;
import java.util.*;
public class UniqueVisitors {
    public static void main(String[] args) {
        String[] visitors = {"A", "B", "A", "C", "B"};
        HashSet<String> unique = new HashSet<>(Arrays.asList(visitors));
        LinkedHashSet<String> order = new LinkedHashSet<>(Arrays.asList(visitors));
        System.out.println("Unique visitors: " + unique);
        System.out.println("First visit order: " + order);
    }
}
