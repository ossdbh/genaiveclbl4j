package io.github.ossdbh.genaiveclbl4j.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(GenAILabels.class)
public @interface GenAILabel {
    String label() default "";

    // If the attribute's value on which this annotation is present
    // is null then use this as the default value as far
    // as the attribute's data is concerned
    // TODO: for now the API only supports string default values
    // TODO: in the future we may extend this by case-switching default
    // TODO: values for all data-types
    String defaultValue() default "UNKNOWN";
}
