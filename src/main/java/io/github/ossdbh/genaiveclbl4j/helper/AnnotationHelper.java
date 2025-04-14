package io.github.ossdbh.genaiveclbl4j.helper;

import io.github.ossdbh.genaiveclbl4j.constants.Constants;
import io.github.ossdbh.genaiveclbl4j.enums.GenAIAnnotationEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AnnotationHelper {
    /*
     * Given a class check if its annotated as an FwfInstance
     */
    public static boolean isGenAIInstance(Class clazz) {
        return AnnotationHelper.isGenAIInstance(clazz, Constants.GENAI_INSTANCE_ANNOTATION_LONG_NAME);
    }

    /*
     * Given a class check if its annotated with a custom annotation
     */
    public static boolean isGenAIInstance(Class clazz, String annotationToCheck) {
        // List class-level annotations
        Annotation[] cl_annotations = clazz.getAnnotations();

        // Lets create a Map of annotation name to annotation type
        Map<String, Annotation> classAnnotationMap = new HashMap<>();
        for (int i = 0; i < cl_annotations.length; i++) {
            classAnnotationMap.put(cl_annotations[i].annotationType().getName(), cl_annotations[i]);
        }

        if (classAnnotationMap.containsKey(annotationToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getGenAIInstanceLabel(Class clazz, String annotationToCheck) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String discoveredLabel = null;
        Annotation[] cl_annotations = clazz.getAnnotations();

        // Lets create a Map of annotation name to annotation type
        Map<String, Annotation> classAnnotationMap = new HashMap<>();
        for (int i = 0; i < cl_annotations.length; i++) {
            classAnnotationMap.put(cl_annotations[i].annotationType().getName(), cl_annotations[i]);
        }

        if (classAnnotationMap.containsKey(annotationToCheck)) {
            Annotation annotation = classAnnotationMap.get(annotationToCheck);
            discoveredLabel = (String) annotation.annotationType().getMethod(GenAIAnnotationEnum.GENAILABEL.getMethod(
                    Constants.GENAI_LABEL_METHOD_KEY)).invoke(annotation);
        }

        return discoveredLabel;
    }

    public static String inferGetter(String fieldName) {
        String prefix = "get";
        StringBuilder builder = new StringBuilder();
        builder.append(prefix); builder.append(fieldName.substring(0,1).toUpperCase()); builder.append(fieldName.substring(1));
        return builder.toString();
    }
}
