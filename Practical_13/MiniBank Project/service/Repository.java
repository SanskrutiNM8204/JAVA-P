package service;
import java.lang.reflect.Field;
import java.util.*;
import model.annotation.Id;
public class Repository<T> {
    private final Map<String, T> data = new HashMap<>();
    private String idOf(T object) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return String.valueOf(field.get(object));
                }
            }
            throw new IllegalArgumentException("No @Id field found");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(T object) { data.put(idOf(object), object); }
    @SafeVarargs
    public final void saveAll(T... objects) {
        for (T object : objects) save(object);
    }
    public T findById(String id) { return data.get(id); }
    public List<T> findAll() { return new ArrayList<>(data.values()); }
    public void delete(String id) { data.remove(id); }
    public static <N extends Number> double average(N[] values) {
        double total = 0;
        for (N value : values) total += value.doubleValue();
        return total / values.length;
    }
}
