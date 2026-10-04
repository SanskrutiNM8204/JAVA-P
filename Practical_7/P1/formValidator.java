package Practical_7.P1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class Validator {
    static List<String> validate(Object object) {
        List<String> errors = new ArrayList<>();

        if (object == null) {
            errors.add("Object must not be null");
            return errors;
        }

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (field.isAnnotationPresent(NotBlank.class)
                        && (!(value instanceof String) || ((String) value).trim().isEmpty())) {
                    errors.add(field.getName() + " must not be blank");
                }

                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                if (maxLength != null && value instanceof String
                        && ((String) value).length() > maxLength.value()) {
                    errors.add(field.getName() + " must be at most "
                            + maxLength.value() + " characters");
                }
            } catch (IllegalAccessException exception) {
                errors.add("Unable to validate " + field.getName());
            }
        }

        return errors;
    }
}

public class formValidator {
    public List<String> validate(Object object) {
        return Validator.validate(object);
    }
}
