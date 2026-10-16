import java.io.*;
import model.Account;
public class StatePersister {
    public static void save(Account[] accounts, String file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(accounts);
        out.close();
    }
    public static Account[] load(String file) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Account[] accounts = (Account[]) in.readObject();
        in.close();
        return accounts;
    }
}
