package io.github.shenbinglife.argrunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

public class Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static ArgsRunner load(String name) {
        List<String> list = new ArrayList<String>();
        ServiceLoader<ArgsRunner> loader = ServiceLoader.load(ArgsRunner.class);
        for (ArgsRunner runner : loader) {
            ToolType toolType = runner.getClass().getDeclaredAnnotation(ToolType.class);
            if (toolType == null) {
                LOGGER.warn("can not find tool type for Runner: " + runner.getClass().getCanonicalName());
                continue;
            }
            String type = toolType.value();
            if (type.equals(name)) {
                return runner;
            } else {
                list.add(type);
            }
        }
        throw new IllegalArgumentException("unsupport tool type: " + name
                + ", available tool types: " + String.join(", ", list));

    }

    public static <T> boolean arrayContains(T[] array, T object) {
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
