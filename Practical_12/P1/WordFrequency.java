package Practical_12.P1;
import java.util.*;
public class WordFrequency {
    public static void main(String[] args) {
        String text = "java is easy and java is useful";
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : text.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
