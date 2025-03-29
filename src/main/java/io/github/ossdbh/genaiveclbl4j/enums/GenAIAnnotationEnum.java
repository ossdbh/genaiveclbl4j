package io.github.ossdbh.genaiveclbl4j.enums;

import io.github.ossdbh.genaiveclbl4j.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public enum GenAIAnnotationEnum {

    GENAIINSTANCE(Constants.GENAI_INSTANCE_ANNOTATION_SHORT_NAME,
            Constants.GENAI_INSTANCE_ANNOTATION_LONG_NAME,
            Constants.attributeKV),
    GENAILABEL(Constants.GENAI_LABEL_ANNOTATION_SHORT_NAME,
            Constants.GENAI_LABEL_ANNOTATION_LONG_NAME,
            Constants.attributeKV),

    GENAILABELS(Constants.GENAI_LABELS_ANNOTATION_SHORT_NAME,
            Constants.GENAI_LABELS_ANNOTATION_LONG_NAME,
            Constants.attributeKV),

    GENAINESTEDATTRIBUTE(Constants.GENAI_NESTED_ATTRIBUTE_ANNOTATION_SHORT_NAME,
            Constants.GENAI_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME,
            null);

    private String annotation_short_name;
    private String annotation_long_name;
    private Map<String, String> annotationMethodNamesMap = new HashMap();

    GenAIAnnotationEnum(String annotation_short_name,
                        String annotation_long_name,
                        Map<String, String> annotationMethodNamesMap) {
        this.annotation_short_name = annotation_short_name;
        this.annotation_long_name = annotation_long_name;
        this.annotationMethodNamesMap = annotationMethodNamesMap;
    }

    public String getAnnotation_short_name() {
        return annotation_short_name;
    }

    public String getAnnotation_long_name() {
        return annotation_long_name;
    }

    public Map<String, String> getAnnotationMethodNamesMap() {
        return annotationMethodNamesMap;
    }

    public String getMethod(String key) {
        return this.annotationMethodNamesMap.get(key);
    }
}
