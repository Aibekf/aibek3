package interfaces;

public interface Validatable<T> {
    void validate(T t);

    default boolean isValid(T t) {
        try {
            validate(t);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    static void notBlank(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    static void positive(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
