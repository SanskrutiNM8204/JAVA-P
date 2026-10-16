package Practical_13.P1;
class Box<T> {
    private T value;
    Box(T value) { this.value = value; }
    public T get() { return value; }
    public void set(T value) { this.value = value; }
}
class Pair<A, B> {
    A first;
    B second;
    Pair(A first, B second) { this.first = first; this.second = second; }
}
public class GenericContainers {
    static <T> T first(T[] values) { return values[0]; }
    public static void main(String[] args) {
        Box<String> box = new Box<>("Java");
        Pair<Integer, String> pair = new Pair<>(1, "One");
        System.out.println(box.get());
        System.out.println(pair.first + " " + pair.second);
        System.out.println(first(new Integer[]{10, 20, 30}));
    }
}
