package io.github.shenbinglife.argrunner;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TooType {

    String value();
}
