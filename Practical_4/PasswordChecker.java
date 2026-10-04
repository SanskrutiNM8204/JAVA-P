public class PasswordChecker {

    public static boolean hasLength(String password) { 
        return password.length() >= 8;
    }

    public static boolean hasUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    public static boolean hasDigit(String password) { 
        return password.matches(".*[0-9].*");
    }

    public static boolean hasSpecialCharacter(String password) { 
        return password.matches(".*[^A-Za-z0-9].*");
    }

    public static String strength(String pass) {
        int count = 0; 
        if (hasLength(pass)) count++; 
        if (hasUppercase(pass)) count++; 
        if (hasDigit(pass)) count++;
        if (hasSpecialCharacter(pass)) count++; 
        if (count <= 1) { 
            return "Weak"; 
        } 
        else if (count <= 3) { 
            return "Medium"; 
        }
        else { 
            return "Strong"; 
        }
    }
}
