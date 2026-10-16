package Practical_11.P1;
import java.io.*;
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    transient String password;
    Student(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
public class SaveLoad {
    public static void main(String[] args) throws Exception {
        Student s = new Student("A", "12345");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.dat"));
        out.writeObject(s);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.dat"));
        Student loaded = (Student) in.readObject();
        in.close();
        System.out.println("Name: " + loaded.name);
        System.out.println("Password: " + loaded.password);
    }
}
