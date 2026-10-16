package Practical_13.P2;
public class BoundedStats {
    static <N extends Number> double average(N[] values) {
        double total = 0;
        for (N value : values) total += value.doubleValue();
        return total / values.length;
    }
    public static void main(String[] args) {
        Integer[] numbers = {10, 20, 30};
        Double[] marks = {80.0, 90.0, 70.0};
        System.out.println("Integer average: " + average(numbers));
        System.out.println("Double average: " + average(marks));
    }
}
