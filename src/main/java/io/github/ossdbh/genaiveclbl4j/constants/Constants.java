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

    public static final String JAVA_LANG_OBJECT_CLASSNAME = "java.lang.Object";
    public static final String JAVA_LANG_OBJECT_SIMPLECLASSNAME = "Object";
    public static final String JAVA_UTIL_LIST_CLASSNAME = "java.util.List";
    public static final String JAVA_UTIL_LIST_SIMPLECLASSNAME = "List";
    public static final String JAVA_UTIL_ARRAYLIST_CLASSNAME = "java.util.ArrayList";
    public static final String JAVA_UTIL_ARRAYLIST_SIMPLECLASSNAME = "ArrayList";
    public static final String JAVA_UTIL_MAP_CLASSNAME = "java.util.Map";
    public static final String JAVA_UTIL_MAP_SIMPLECLASSNAME = "Map";
    public static final String JAVA_UTIL_HASHMAP_CLASSNAME = "java.util.HashMap";
    public static final String JAVA_UTIL_HASHMAP_SIMPLECLASSNAME = "HashMap";

    public static final String LABEL_AND_DATA_CONCATENATOR = "|";

    public static final Map<String, String> attributeKV = new HashMap<String, String>() {{
        put(Constants.GENAI_LABEL_METHOD_KEY, Constants.GENAI_LABEL_METHOD_VALUE);
        put(Constants.GENAI_LABEL_METHOD_DEFAULT_KEY, Constants.GENAI_LABEL_METHOD_DEFAULT_VALUE);
    }};

    public static final String DEFAULT_VALUE_UNKNOWN = "UNKNOWN_VALUE";
}

