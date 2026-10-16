package Practical_11.P2;
import java.nio.file.*;
import java.io.*;
public class LogAnalyzer {
    public static void main(String[] args) throws Exception {
        Path file = Path.of("sample.log");
        Files.writeString(file, "login successful\nerror found\nlogin again\n");
        int lines = 0;
        int matches = 0;
        BufferedReader reader = Files.newBufferedReader(file);
        String line;
        while ((line = reader.readLine()) != null) {
            lines++;
            if (line.contains("error")) matches++;
        }
        reader.close();
        System.out.println("Lines: " + lines);
        System.out.println("Keyword matches: " + matches);
        System.out.println("File size: " + Files.size(file));
    }
}
