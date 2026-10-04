public class ShapesDemo {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
            new Circle(5),
            new Rectangle(3, 4),
            new Triangle(5, 2),
            new Circle(1.2),
            new Rectangle(6 , 2)
        };

        double total = 0.0;
        double max = 0.0;
        Shape largest = null;

        System.out.println("Areas:");
        for (Shape s : shapes) {
            double a = s.area();
            total += a;
            if (a > max) {
                max = a;
                largest = s;
            }
            System.out.printf("%-30s area = %.2f%n", s.toString(), a);
        }

        System.out.printf("Running total = %.2f%n", total);
        System.out.printf("Largest: %s with area %.2f%n", largest, max);
    }
}
