package io.github.ossdbh.genaiveclbl4j.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.TYPE)

@Documented

public @interface GenAIInstance {
    String label() default "";
    String value() default "";

}
