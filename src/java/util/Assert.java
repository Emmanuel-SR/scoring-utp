package util;

import java.util.Collection;

public final class Assert {

    private Assert() {
    }

    public static void isTrue(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    public static void notNull(Object o, String message, Object... args) {
        if (o == null) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    public static void notEmpty(Collection col, String message, Object... args) {
        if (col == null || col.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    public static void notEmpty(Object[] o, String message, Object... args) {
        if (o == null || o.length == 0) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    public static void notBlank(String str, String message, Object... args) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }
}
