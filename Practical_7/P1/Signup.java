package Practical_7.P1;

class SignupForm {
    @NotBlank
    @MaxLength(20)
    private final String name;

    @NotBlank
    @MaxLength(50)
    private final String email;

    SignupForm(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
