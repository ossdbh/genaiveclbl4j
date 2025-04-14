package io.github.ossdbh.genaiveclbl4j.generator;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import io.github.ossdbh.genaiveclbl4j.constants.Constants;
import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelDTO;
import io.github.ossdbh.genaiveclbl4j.enums.GenAIAnnotationEnum;
import io.github.ossdbh.genaiveclbl4j.enums.JavaStandardLibraryClassEnum;
import io.github.ossdbh.genaiveclbl4j.exception.GenAITextLabelGeneratorException;
import io.github.ossdbh.genaiveclbl4j.helper.AnnotationHelper;
import io.github.ossdbh.genaiveclbl4j.helper.GetterMethodDiscovererHelper;
import io.github.ossdbh.genaiveclbl4j.helper.XPathHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * this class is not being used currently
 */
public class GenAIVectorTrainAndSearchlabelGenerator {
    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that generates user-defined genai label
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance) {
        // Use default lombok based getter method discoverer
        return GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(instance,
                null,
                GetterMethodDiscovererHelper.defaultLombokBasedGetterMethod,
                Constants.LABEL_AND_DATA_CONCATENATOR);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that generates user-defined genai label
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param labelAndDataConcatenator the concatenator string to use to concat two labels
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance, String labelAndDataConcatenator) {
        // Use default lombok based getter method discoverer
        return GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(instance,
                null,
                GetterMethodDiscovererHelper.defaultLombokBasedGetterMethod,
                labelAndDataConcatenator);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that generates user-defined genai label
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param getterMethod The getter method discovery function that would be used by the generator
     * @param labelAndDataConcatenator the concatenator string to use to concat two labels
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance,
                                           Function<String, String> getterMethod,
                                           String labelAndDataConcatenator) {
        return GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(instance, null, getterMethod, labelAndDataConcatenator);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that generates user-defined genai label
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param getterMethod The getter method discovery function that would be used by the generator
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance, Function<String, String> getterMethod) {
        return GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(instance, null, getterMethod, Constants.LABEL_AND_DATA_CONCATENATOR);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip
     * @param labelAndDataConcatenator concatenator to use for joining each label and data combo
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance,
                                           List<String> fieldsToSkipList,
                                           String labelAndDataConcatenator) {
        return GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(instance, fieldsToSkipList,
                GetterMethodDiscovererHelper.defaultLombokBasedGetterMethod,
                labelAndDataConcatenator);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance, List<String> fieldsToSkipList) {
        return GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(instance, fieldsToSkipList,
                GetterMethodDiscovererHelper.defaultLombokBasedGetterMethod,
                Constants.LABEL_AND_DATA_CONCATENATOR);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip
     * @param getterMethod The getter method discovery function that would be used by the generator
     * @return returns a string that represents a genai textembeddings label
     *
     */
    public static String generateTextLabel(Object instance,
                                           List<String> fieldsToSkipList,
                                           Function<String, String> getterMethodDiscoverer,
                                           String labelAndDataConcatenator) {
        Map<String, Integer> fieldsToSkipMap = new HashMap<>();

        if (fieldsToSkipList != null && fieldsToSkipList.size() > 0) {
            fieldsToSkipMap = fieldsToSkipList.stream().collect(Collectors.toMap(Function.identity(), String::length));
        }

        Stack<String> attrXPath = new Stack<>();
        List<GenAILabelDTO> genAILabelDTOList = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        if (instance != null) {
            GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                    instance.getClass(),
                    instance,
                    genAILabelDTOList,
                    attrXPath,
                    fieldsToSkipMap,
                    getterMethodDiscoverer,
                    labelAndDataConcatenator
            );

            if (genAILabelDTOList != null && genAILabelDTOList.size() > 0) {
                for (int i = 0; i < genAILabelDTOList.size(); i++) {
                    GenAILabelDTO genAILabelDTO = genAILabelDTOList.get(i);

                    String label = ""; String labelData = "";

                    if (i < genAILabelDTOList.size() - 1) {
                        if (genAILabelDTO != null && genAILabelDTO.getLabel() != null) {
                            label = genAILabelDTO.getLabel();
                        }

                        if (genAILabelDTO != null && genAILabelDTO.getData() != null) {
                            // This is the case where the label's attribute has a non-null value
                            // We want to check if the label's attribute is a collection instance
                            // or not
                            Class attrClass = genAILabelDTO.getClass();
                            // If its a collection instance
                            if (genAILabelDTO.getData() instanceof Collection) {
                                // We dont want to print the entire collection here
                                // The objects subsequently are the ones that are part of the collection
                                labelData = "";
                            } else {
                                labelData = genAILabelDTO.getData().toString();
                            }
                        } else {
                            // The label's attribute data is null so we print default value
                            labelData = Constants.DEFAULT_VALUE_UNKNOWN + " ";
                        }

                        if (genAILabelDTO != null && genAILabelDTO.isAppendDelimiter()) {
                            builder.append(label + labelData + genAILabelDTO.getLabelAndDataConcatenator());
                        } else {
                            builder.append(label + labelData);
                        }
                    } else {
                        if (genAILabelDTO != null && genAILabelDTO.getLabel() != null) {
                            label = genAILabelDTO.getLabel();
                        }

                        if (genAILabelDTO != null && genAILabelDTO.getData() != null) {
                            labelData = genAILabelDTO.getData().toString();
                        }
                        builder.append(label + labelData);
                    }
                }
            }
        }

        return builder.toString();
    }

    /*
     * Crawls/Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds genai label annotation values that a user
     * may have added to annotate an instance for generating textembedding input
     *
     * @param clazz The class type that is being inspected
     * @param instance The actual instance that needs to be written in fixed width
     * @param fwAttrList Depth First Stack that holds the discovered field attribute values
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip
     * @param getterMethod The getter method discovery function that would be used by the generator
     * @return none
     */
    private static void generateTextLabel(Class clazz,
                                          Object instance,
                                          List<GenAILabelDTO> fwAttrList,
                                          Stack<String> attrXPath,
                                          Map<String, Integer> fieldsToSkipMap,
                                          Function<String, String> getterMethodDiscoverer,
                                          String labelAndDataConcatenator) {
        if (AnnotationHelper.isGenAIInstance(clazz)) {
            // Push the current class instance into XPath
            attrXPath.push(instance.getClass().getSimpleName());

            // We now want to discover the label that is at this instance
            try {
                String discoveredLabel = AnnotationHelper.getGenAIInstanceLabel(clazz, Constants.GENAI_INSTANCE_ANNOTATION_LONG_NAME);
                if (discoveredLabel != null && discoveredLabel.length() > 0) {
                    // We generate a new label and set its data to blank
                    // Since this is a Class level label it would not have any data associated with the label
                    fwAttrList.add(GenAILabelDTO.builder()
                            .label(discoveredLabel)
                            .method(null)
                            .data("")
                            .attributeClass(clazz)
                            .appendDelimiter(true)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build());
                }
            } catch (NoSuchMethodException e) {
                throw new GenAITextLabelGeneratorException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new GenAITextLabelGeneratorException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new GenAITextLabelGeneratorException(e.getMessage(), e);
            }

            if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                // Start discovering class fields
                ClassFieldResolver classFieldResolver = new ClassFieldResolver();
                List<Field> declaredFields = new ArrayList<>();

                // Build a list of declared fields that the class, passed in as param, has declared
                classFieldResolver.getClassFields(clazz, new Stack<>(), new Stack<String>(), declaredFields, fieldsToSkipMap);

                for (int i = 0; i < declaredFields.size(); i++) {
                    Field f = declaredFields.get(i);
                    // Discover annotations on the field
                    Annotation[] fieldAnnotations = f.getDeclaredAnnotations();
                    if (fieldAnnotations != null && fieldAnnotations.length > 0) {

                        // Iterate over all field annotations
                        for (int j = 0; j < fieldAnnotations.length; j++) {
                            // Check if we have a field annotated as GenAILabels
                            if (
                                    fieldAnnotations[j].annotationType().getName().equals(
                                            GenAIAnnotationEnum.GENAILABELS.getAnnotation_long_name())) {

                                attrXPath.push(f.getName());

                                // Check if we need to skip the field
                                if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                                    try {
                                        // If the field has multiple GenAILabel annotations
                                        // then JVM registers this as a GenAILables repeatable meta annotation
                                        // Discover all labels that you can find at this field
                                        GenAILabel[] labels = f.getAnnotationsByType(GenAILabel.class);
                                        // Iterate over all labels discovered
                                        for (int k = 0; k < labels.length; k++) {
                                            String labelDiscovered = labels[k].label();

                                            String defaultValue = labels[k].defaultValue();

                                            if (instance != null) {
                                                // discover the label
                                                GenAIVectorTrainAndSearchlabelGenerator.discoverNodeLabel(
                                                        clazz,
                                                        instance,
                                                        f,
                                                        labelDiscovered,
                                                        defaultValue,
                                                        fwAttrList,
                                                        attrXPath,
                                                        fieldsToSkipMap,
                                                        getterMethodDiscoverer,
                                                        labelAndDataConcatenator);
                                            }
                                        }
                                    } catch (NoSuchMethodException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    } catch (InvocationTargetException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    } catch (IllegalAccessException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    }
                                }

                                attrXPath.pop();
                            } else if (fieldAnnotations[j].annotationType().getName().equals(
                                    GenAIAnnotationEnum.GENAILABEL.getAnnotation_long_name())) {
                                // else if the field is marked as GenAILabel
                                attrXPath.push(f.getName());

                                if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                                    try {
                                        String labelDiscovered = (String) fieldAnnotations[j].annotationType()
                                                .getMethod(GenAIAnnotationEnum.GENAILABEL.getMethod(
                                                        Constants.GENAI_LABEL_METHOD_KEY))
                                                .invoke(fieldAnnotations[j]);

                                        String defaultValue = (String) fieldAnnotations[j].annotationType()
                                                .getMethod(GenAIAnnotationEnum.GENAILABEL.getMethod(
                                                        Constants.GENAI_LABEL_METHOD_DEFAULT_KEY))
                                                .invoke(fieldAnnotations[j]);

                                        if (instance != null) {
                                            GenAIVectorTrainAndSearchlabelGenerator.discoverNodeLabel(
                                                    clazz,
                                                    instance,
                                                    f,
                                                    labelDiscovered,
                                                    defaultValue,
                                                    fwAttrList,
                                                    attrXPath,
                                                    fieldsToSkipMap,
                                                    getterMethodDiscoverer,
                                                    labelAndDataConcatenator);
                                        }
                                    } catch (IllegalAccessException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    } catch (InvocationTargetException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    } catch (NoSuchMethodException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    }
                                }

                                attrXPath.pop();
                            } else if (fieldAnnotations[j].annotationType().getName().equals(
                                    Constants.GENAI_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME)) {
                                // else if the field is marked as nested it means is has an FwfInstance annotation
                                // on the field
                                Class nestedType = f.getType();
                                // Check if this nested type was annotated with FwInstance annotation
                                if (AnnotationHelper.isGenAIInstance(nestedType)) {
                                    // This means the nested type has an FwInstance annotation
                                    // We need to depth first travserse into the field
                                    // with the type of the nested field and the actual nested field instance

                                    // Lets infer the lombok getter method for this field
                                    String getterMethodInferred = getterMethodDiscoverer.apply(f.getName());

                                    try {
                                        // Fetch the Method object from the current instance
                                        Method getter = clazz.getMethod(getterMethodInferred);

                                        // Invoke the method, this will give us the actual instance
                                        // of the nested type
                                        Object nestedInstance = getter.invoke(instance, null);

                                        if (nestedInstance != null) {
                                            // Depth first traverse with the nested type and the actual
                                            // nested instance
                                            GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                                                    nestedType,
                                                    nestedInstance,
                                                    fwAttrList,
                                                    attrXPath,
                                                    fieldsToSkipMap,
                                                    getterMethodDiscoverer,
                                                    labelAndDataConcatenator);
                                        }
                                    } catch (NoSuchMethodException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    } catch (InvocationTargetException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    } catch (IllegalAccessException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            attrXPath.pop();
        } else if (clazz.getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_LIST.getLongName()) ||
                clazz.getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_ARRAYLIST.getLongName())) {
            List l = (List) instance;
            if (l != null && l.size() > 0) {
                for (int i = 0; i < l.size(); i++) {
                    // We know that collections always works on objects
                    Object listInstanceObject = l.get(i);
                    if (listInstanceObject != null) {
                        // Run this object through depth first
                        GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                                listInstanceObject.getClass(),
                                listInstanceObject,
                                fwAttrList,
                                attrXPath,
                                fieldsToSkipMap,
                                getterMethodDiscoverer,
                                labelAndDataConcatenator
                        );
                    }
                }
            }
        } else {
            // This could be an object inside a collection
            fwAttrList.add(GenAILabelDTO.builder()
                    .label(null)
                    .method(null)
                    .data(instance)
                    .attributeClass(null)
                    .appendDelimiter(true)
                    .labelAndDataConcatenator(labelAndDataConcatenator)
                    .build());
        }
    }

    private static void discoverNodeLabel(Class clazz,
                                          Object instance,
                                          Field field,
                                          String labelDiscovered,
                                          String defaultValue,
                                          List<GenAILabelDTO> fwAttrList,
                                          Stack<String> attrXPath,
                                          Map<String, Integer> fieldsToSkipMap,
                                          Function<String, String> getterMethodDiscoverer,
                                          String labelAndDataConcatenator)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // The label we discovered, we would want to apply
        // on an actual value that would be provided by a lombok getter
        // or by a user-defined getter method discoverer function
        // infer the getter name from the fieldname
        String getterMethodInferred = getterMethodDiscoverer.apply(field.getName());

        // Fetch the Method object from the current instance
        Method getter = clazz.getMethod(getterMethodInferred);
        //System.out.println("Calling getter " + getter.getName());

        // Invoke the method here
        Object methodReturned = getter.invoke(instance, null);

        // For now we only support default value for fields of type String
        // Check if the field's type is of String instance using a blank string
        // TODO: for now we assume that string fields only could be null
        // TODO: and we need to source default values only for string fields
        if (methodReturned == null && field.getType().isInstance("")) {
            methodReturned = defaultValue;
        }

        //Infer data type
        Class methodClass = getter.getReturnType();

        if (field.getType().getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_LIST.getLongName()) ||
                field.getType().getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_ARRAYLIST.getLongName())) {
            // Build a FWValue instance that we would use later
            // to construct the record (for array list we suppress the data)
            fwAttrList.add(
                    GenAILabelDTO.builder()
                            .label(labelDiscovered)
                            .method(getterMethodInferred)
                            .data(methodReturned)
                            .attributeClass(methodClass)
                            .appendDelimiter(false)
                            .build()
            );

            if (methodReturned != null) {
                // discover the label
                GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                        methodReturned.getClass(),
                        methodReturned,
                        fwAttrList,
                        attrXPath,
                        fieldsToSkipMap,
                        getterMethodDiscoverer,
                        labelAndDataConcatenator
                );
            }
        } else {
            // Build a FWValue instance that we would use later
            // to construct the record
            fwAttrList.add(
                    GenAILabelDTO.builder()
                            .label(labelDiscovered)
                            .method(getterMethodInferred)
                            .data(methodReturned)
                            .attributeClass(methodClass)
                            .appendDelimiter(true)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );
        }
    }
}
