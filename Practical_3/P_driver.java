public class P_driver {
    public static void main (String []args) {
    Point[] point = {
        new Point(1, 2),
        new Point(2, 3), 
        new Point(3, 1), 
        new Point(1, 2), 
        new Point(5, 7) 
    };
    int Distinct = 0;
    for (int i = 0; i < point.length; i++) {
        System.out.println(point[i]);
        boolean isDuplicate = false;
        for (int j = 0; j < i; j++) {
            if (point[i].equals(point[j])) {
                System.out.println("Duplicate found: " + point [i]);
                isDuplicate = true;
                break;
                
            }
        }
        if (!isDuplicate) {
            Distinct++;
        }
    }
    System.out.println("Distinct points: " + Distinct); 
}
}
