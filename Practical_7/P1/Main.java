package Practical_7.P1;

import java.util.List;

class Main {
    public static void main(String[] args) {
        SignupForm form = new SignupForm("Sanskruti Malaviya", "abcd1234@gmail.com");
        List<String> errors = Validator.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid.");
        } else {
            errors.forEach(System.out::println);
        }
    }
}
