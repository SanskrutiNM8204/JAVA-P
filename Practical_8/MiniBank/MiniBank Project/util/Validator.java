package util;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern MOBILE = Pattern.compile("^(?:\\+91|0)?[6-9]\\d{9}$");
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PAN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    private static final Pattern IFSC = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String s) {
        if (s == null) return false;
        return MOBILE.matcher(s.trim()).matches();
    }

    public static boolean isValidEmail(String s) {
        if (s == null) return false;
        return EMAIL.matcher(s.trim()).matches();
    }

    public static boolean isValidPan(String s) {
        if (s == null) return false;
        return PAN.matcher(s.trim().toUpperCase()).matches();
    }

    public static boolean isValidIfsc(String s) {
        if (s == null) return false;
        return IFSC.matcher(s.trim().toUpperCase()).matches();
    }
}
