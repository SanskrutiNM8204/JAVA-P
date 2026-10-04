package Practical_7.P2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MiniTestRunner {
    public static void main(String[] args) {
        SampleTests tests = new SampleTests();
        int ran = 0;

        for (Method method : tests.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Run.class) || method.getParameterCount() != 0) {
                continue;
            }

            try {
                method.setAccessible(true);
                method.invoke(tests);
                ran++;
            } catch (IllegalAccessException | InvocationTargetException exception) {
                System.out.println("Could not run " + method.getName());
            }
        }

        System.out.println("Methods ran: " + ran);
    }
}
