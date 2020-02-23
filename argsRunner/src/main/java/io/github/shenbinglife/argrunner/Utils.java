package io.github.shenbinglife.argrunner;

import java.util.Objects;

public class Utils {

    public static <T> boolean arrayContains(T[] array , T object) {
        if (array == null) {
            throw new NullPointerException("the checked array is null");
        }
        for (T t : array) {
            if (t == object || Objects.equals(t, object)) {
                return true;
            }
        }
        return false;
    }
}
