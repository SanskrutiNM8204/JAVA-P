package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import model.annotation.MaxLength;
import model.annotation.Positive;

public final class AnnotationValidator {
    private AnnotationValidator() {
    }

    public static String[] validate(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object to validate cannot be null.");
        }

        List<String> errors = new ArrayList<>();
        for (Class<?> type = obj.getClass(); type != null && type != Object.class; type = type.getSuperclass()) {
            for (Field field : type.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);

                    if (field.isAnnotationPresent(Positive.class)) {
                        Positive positive = field.getAnnotation(Positive.class);
                        if (!(value instanceof Number number) || number.doubleValue() <= 0) {
                            errors.add(field.getName() + " " + positive.message());
                        }
                    }

                    if (field.isAnnotationPresent(MaxLength.class)) {
                        MaxLength maxLength = field.getAnnotation(MaxLength.class);
                        if (value instanceof CharSequence text && text.length() > maxLength.value()) {
                            errors.add(field.getName() + " length must be at most " + maxLength.value());
                        }
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Could not validate field " + field.getName() + ".");
                }
            }
        }
        return errors.toArray(String[]::new);
    }
}