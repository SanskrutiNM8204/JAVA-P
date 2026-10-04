public class Driver {
    public static void main(String[] args) { 
        String[] passwords = {
            "abc",
            "Abcd1234!",
            "Password",
            "pass1234",
            "P@ssword",
            "A1!"
        };

        for (String pass : passwords) { 
            System.out.println("--------------------------------"); 
            System.out.println("Password: " + pass); 
            System.out.println("  Length >= 8: " + PasswordChecker.hasLength(pass));
            System.out.println("  Has uppercase: " + PasswordChecker.hasUppercase(pass));
            System.out.println("  Has digit: " + PasswordChecker.hasDigit(pass));
            System.out.println("  Has special char: " + PasswordChecker.hasSpecialCharacter(pass)); 
            System.out.println("  Strength: " + PasswordChecker.strength(pass));
            System.out.println("--------------------------------");
        }
    }
}
