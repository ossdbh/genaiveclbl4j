package io.github.ossdbh.genaiveclbl4j.annotation;

import io.github.ossdbh.genaiveclbl4j.constants.Constants;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(GenAILabels.class)
public @interface GenAILabel {
    String label() default "";

    // If the attribute's value on which this annotation is present
    // is null then use this as the default value as far
    // as the attribute's data is concerned
    String defaultValue() default Constants.DEFAULT_VALUE_UNKNOWN;
}
