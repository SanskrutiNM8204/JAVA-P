import java.util.Scanner;

public class DriverChat { 
    public static void main(String[] args) { 
        String[] logLines = {
            "10:05 alice Hello there",
            "10:07 bob hi everyone",
            "10:09 Hello, I'm late"
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword: "); 
        String keyword = scanner.nextLine().trim(); 

        StringBuilder report = new StringBuilder(); 
        int matches = ChatFilter.buildReport(keyword, logLines, report); 

        System.out.println("Matches: " + matches); 
        System.out.print(report.toString()); 

        scanner.close();
    }
}
