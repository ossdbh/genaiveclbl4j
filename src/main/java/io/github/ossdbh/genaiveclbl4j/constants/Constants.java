package io.github.ossdbh.genaiveclbl4j.constants;

import java.util.HashMap;
import java.util.Map;

public interface Constants {
    public static final String GENAI_INSTANCE_ANNOTATION_SHORT_NAME = "GenAIInstance";
    public static final String GENAI_INSTANCE_ANNOTATION_LONG_NAME = "io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance";
    public static final String GENAI_LABEL_ANNOTATION_SHORT_NAME = "GenAILabel";
    public static final String GENAI_LABEL_ANNOTATION_LONG_NAME = "io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel";
    public static final String GENAI_LABELS_ANNOTATION_SHORT_NAME = "GenAILabels";
    public static final String GENAI_LABELS_ANNOTATION_LONG_NAME = "io.github.ossdbh.genaiveclbl4j.annotation.GenAILabels";
    public static final String GENAI_LABEL_METHOD_KEY = "LABEL";
    public static final String GENAI_LABEL_METHOD_VALUE = "label";
    public static final String GENAI_LABEL_METHOD_DEFAULT_KEY = "DEFAULTVALUE";
    public static final String GENAI_LABEL_METHOD_DEFAULT_VALUE = "defaultValue";
    public static final String GENAI_NESTED_ATTRIBUTE_ANNOTATION_SHORT_NAME = "GenAINestedInstance";
    public static final String GENAI_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME = "io.github.ossdbh.genaiveclbl4j.annotation.GenAINestedInstance";

    public static final String JAVA_LANG_OBJECT_CLASSNAME = "java.lang.object";
    public static final String JAVA_LANG_OBJECT_SIMPLECLASSNAME = "object";

    public static final String LABEL_AND_DATA_CONCATENATOR = "|";

    public static final Map<String, String> attributeKV = new HashMap<String, String>() {{
        put(Constants.GENAI_LABEL_METHOD_KEY, Constants.GENAI_LABEL_METHOD_VALUE);
        put(Constants.GENAI_LABEL_METHOD_DEFAULT_KEY, Constants.GENAI_LABEL_METHOD_DEFAULT_VALUE);
    }};
}

