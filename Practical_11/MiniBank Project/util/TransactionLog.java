import java.io.IOException;
import java.nio.file.*;

public class TransactionLog {
    public static void add(String text, String file) throws IOException {
        Files.writeString(Path.of(file), text + System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
