import java.util.Scanner;

public class TollBooth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalToll = 0, bikeCount = 0, carCount = 0, truckCount = 0;
        
        while (true) {
            System.out.print("Vehicle number (GJXX-XXXX / done): ");
            String number = scanner.next();
            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Vehicle type (car / bike / truck): ");
            String type = scanner.next();
            if (type.equalsIgnoreCase("done")) {
                break;
            }

            if (type.equalsIgnoreCase("bike")) {
                bikeCount++;
                totalToll += 20;
            } else if (type.equalsIgnoreCase("car")) {
                carCount++;
                totalToll += 50;
            } else if (type.equalsIgnoreCase("truck")) {
                truckCount++;
                totalToll += 150;
            } else {
                System.out.println("Unknown vehicle type: " + type);
            }
        }

        System.out.println("Total toll: " + totalToll);

        if (bikeCount + carCount + truckCount == 0) {
            System.out.println("No vehicles entered.");
        } else {
            String mostFrequent = "bike";
            int maxCount = bikeCount;

            if (carCount > maxCount) {
                mostFrequent = "car";
                maxCount = carCount;
            }
            if (truckCount > maxCount) {
                mostFrequent = "truck";
            }

            System.out.println("Most frequent: " + mostFrequent);
        }

        scanner.close();
    }
}
