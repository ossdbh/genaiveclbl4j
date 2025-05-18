package io.github.ossdbh.genaiveclbl4j.generator;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import io.github.ossdbh.genaiveclbl4j.constants.Constants;
import io.github.ossdbh.genaiveclbl4j.constants.Functions;
import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelDTO;
import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelMetadataHelperDTO;
import io.github.ossdbh.genaiveclbl4j.enums.GenAIAnnotationEnum;
import io.github.ossdbh.genaiveclbl4j.enums.JavaStandardLibraryClassEnum;
import io.github.ossdbh.genaiveclbl4j.exception.GenAITextLabelGeneratorException;
import io.github.ossdbh.genaiveclbl4j.helper.AnnotationHelper;
import io.github.ossdbh.genaiveclbl4j.helper.GetterMethodDiscovererHelper;
import io.github.ossdbh.genaiveclbl4j.helper.XPathHelper;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
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
                            // or its an instance of Abstract
                            Class attrClass = genAILabelDTO.getClass();
                            // If its a collection instance
                            if (genAILabelDTO.getData() instanceof Collection
                                    || genAILabelDTO.getData() instanceof AbstractMap
                                    || genAILabelDTO.getData().getClass().isArray()) {
                                // We dont want to print the entire collection here
                                // The collection and / or map objects are subsequently present inside genAILabelDTOList
                                // that would print themselves on subsequent values of "i" in this iteration

                                // But we would want to distinguish between a collection/map that has a label
                                // and a collection/map that is part of collection/map nesting
                                if (genAILabelDTO.getLabel() != null) {
                                    // If the element has label associated with it, we suppress printing any label data
                                    // cause of the logic explained in the comments right after the previous if-statement
                                    labelData = "";
                                } else {
                                    // If the element has no label associated with it then it is most probably a nested
                                    // collection that was set as NULL by the caller
                                    // We attempt to indicate that to the caller
                                    labelData = Constants.DEFAULT_VALUE_UNKNOWN;
                                }
                            } else {
                                labelData = genAILabelDTO.getData().toString();
                            }
                        } else {
                            // The label's attribute data is null so we print default value
                            labelData = Constants.DEFAULT_VALUE_UNKNOWN;
                        }

                        builder.append(label + labelData);
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
     * @param genAILabelsDiscoveredList Depth First Stack that holds the discovered field attribute values
     * @param fieldsToSkipMap stack that holds xPath like structure for skipping fields
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip passed in by the caller
     * @param getterMethodDiscoverer The getter method discovery function that would be used by the generator
     * @param labelAndDataConcatenator concatenator that would print the label and the data together
     * @return none
     */
    private static void generateTextLabel(Class clazz,
                                          Object instance,
                                          List<GenAILabelDTO> genAILabelsDiscoveredList,
                                          Stack<String> attrXPath,
                                          Map<String, Integer> fieldsToSkipMap,
                                          Function<String, String> getterMethodDiscoverer,
                                          String labelAndDataConcatenator) {
        // Check if the instance passed is annotated with GenAIInstance class
        if (AnnotationHelper.isGenAIInstance(clazz)) {
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_INSTANCE_START_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator).build()
            );

            // Push the current class instance into XPath
            attrXPath.push(instance.getClass().getSimpleName());

            // We now want to discover the label that is at this instance
            try {
                String discoveredLabel = AnnotationHelper.getGenAIInstanceLabel(clazz, Constants.GENAI_INSTANCE_ANNOTATION_LONG_NAME);
                if (discoveredLabel != null && discoveredLabel.length() > 0) {
                    // We generate a new label and set its data to blank
                    // Since this is a Class level label it would not have any data associated with the label
                    genAILabelsDiscoveredList.add(
                            GenAILabelDTO.builder()
                            .label(discoveredLabel)
                            .method(null)
                            .data("")
                            .attributeClass(clazz)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new GenAITextLabelGeneratorException(e.getMessage(), e);
            }

            if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                // Start discovering class fields
                ClassFieldResolver classFieldResolver = new ClassFieldResolver();
                List<Field> declaredFields = new ArrayList<>();

                // Build a list of declared fields that the class, passed in as param, has declared
                classFieldResolver.getClassFields(clazz, new Stack<>(), new Stack<String>(), declaredFields, fieldsToSkipMap);

                Functions.addGenAILabelMetadataMarker.apply(
                        genAILabelsDiscoveredList,
                        GenAILabelMetadataHelperDTO.builder()
                                .labelMetadata(Constants.GENAI_FIELDS_START_INDICATOR)
                                .labelAndDataConcatenator(labelAndDataConcatenator).build()
                );

                for (int i = 0; i < declaredFields.size(); i++) {
                    Field f = declaredFields.get(i);
                    // Discover annotations on the field
                    Annotation[] fieldAnnotations = f.getDeclaredAnnotations();
                    if (fieldAnnotations != null && fieldAnnotations.length > 0) {

                        // Iterate over all field annotations
                        for (int j = 0; j < fieldAnnotations.length; j++) {
                            // If the field has multiple GenAILabel annotations
                            // then JVM registers this as a GenAILables repeatable meta annotation
                            // Check if we have a field annotated as GenAILabels
                            if (
                                    fieldAnnotations[j].annotationType().getName().equals(
                                            GenAIAnnotationEnum.GENAILABELS.getAnnotation_long_name())) {

                                attrXPath.push(f.getName());

                                // Check if we need to skip the field
                                if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                                    try {
                                        // Discover all labels that you can find at this field
                                        GenAILabel[] labels = f.getAnnotationsByType(GenAILabel.class);
                                        // Iterate over all labels discovered
                                        for (int k = 0; k < labels.length; k++) {
                                            String labelDiscovered = labels[k].label();

                                            // discover the label
                                            GenAIVectorTrainAndSearchlabelGenerator.discoverNodeLabel(
                                                    clazz,
                                                    instance,
                                                    f,
                                                    labelDiscovered,
                                                    genAILabelsDiscoveredList,
                                                    attrXPath,
                                                    fieldsToSkipMap,
                                                    getterMethodDiscoverer,
                                                    labelAndDataConcatenator);
                                            if (k < labels.length - 1) {
                                                Functions.addGenAILabelMetadataMarker.apply(
                                                        genAILabelsDiscoveredList,
                                                        GenAILabelMetadataHelperDTO.builder()
                                                                .labelMetadata(Constants.GENAI_MULTIPLE_LABEL_SEPARATOR_INDICATOR)
                                                                .labelAndDataConcatenator(labelAndDataConcatenator).build()
                                                );
                                            }

                                        }
                                        if (i < declaredFields.size() - 1) {
                                            Functions.addGenAILabelMetadataMarker.apply(
                                                    genAILabelsDiscoveredList,
                                                    GenAILabelMetadataHelperDTO.builder()
                                                            .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                                            .labelAndDataConcatenator(labelAndDataConcatenator).build()
                                            );
                                        }
                                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
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

                                        GenAIVectorTrainAndSearchlabelGenerator.discoverNodeLabel(
                                                clazz,
                                                instance,
                                                f,
                                                labelDiscovered,
                                                genAILabelsDiscoveredList,
                                                attrXPath,
                                                fieldsToSkipMap,
                                                getterMethodDiscoverer,
                                                labelAndDataConcatenator);

                                        if (i < declaredFields.size() - 1) {
                                            Functions.addGenAILabelMetadataMarker.apply(
                                                    genAILabelsDiscoveredList,
                                                    GenAILabelMetadataHelperDTO.builder()
                                                            .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                                            .labelAndDataConcatenator(labelAndDataConcatenator).build()
                                            );
                                        }
                                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    }
                                }

                                attrXPath.pop();
                            } else if (fieldAnnotations[j].annotationType().getName().equals(
                                    Constants.GENAI_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME)) {
                                // else if the field is marked as nested it means is has an GenAINestedInstance annotation
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
                                                    genAILabelsDiscoveredList,
                                                    attrXPath,
                                                    fieldsToSkipMap,
                                                    getterMethodDiscoverer,
                                                    labelAndDataConcatenator);
                                        } else {
                                            // Since nested instance is null, we want to discover the node
                                            GenAIVectorTrainAndSearchlabelGenerator.discoverNodeLabel(
                                                    clazz,
                                                    instance,
                                                    f,
                                                    null,
                                                    genAILabelsDiscoveredList,
                                                    attrXPath,
                                                    fieldsToSkipMap,
                                                    getterMethodDiscoverer,
                                                    labelAndDataConcatenator);
                                        }
                                        if (i < declaredFields.size() - 1) {
                                            Functions.addGenAILabelMetadataMarker.apply(
                                                    genAILabelsDiscoveredList,
                                                    GenAILabelMetadataHelperDTO.builder()
                                                            .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                                            .labelAndDataConcatenator(labelAndDataConcatenator).build()
                                            );
                                        }
                                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                        throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                                    }
                                }
                            }
                        }
                    } else {
                        // I have found a field that has no annotations in it
                        // That is there are no annotations on this field
                        // But it could be a collection that contains annotations
                        try {
                            // we would want to make this part of xPath as well
                            // even though there are no GenAI label annotations associated with it
                            // cause the object representing this could be a complex collection
                            attrXPath.push(f.getName());

                            // Because we have no GenAI label associated with this field
                            // This means we pass in null as labelDiscovered
                            GenAIVectorTrainAndSearchlabelGenerator.discoverNodeLabel(
                                    clazz,
                                    instance,
                                    f,
                                    null,
                                    genAILabelsDiscoveredList,
                                    attrXPath,
                                    fieldsToSkipMap,
                                    getterMethodDiscoverer,
                                    labelAndDataConcatenator);

                            // pop the element
                            attrXPath.pop();

                            if (i < declaredFields.size() - 1) {
                                Functions.addGenAILabelMetadataMarker.apply(
                                        genAILabelsDiscoveredList,
                                        GenAILabelMetadataHelperDTO.builder()
                                                .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                                .labelAndDataConcatenator(labelAndDataConcatenator).build()
                                );
                            }
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            throw new GenAITextLabelGeneratorException(e.getMessage(), e);
                        }
                    }
                }
                Functions.addGenAILabelMetadataMarker.apply(
                        genAILabelsDiscoveredList,
                        GenAILabelMetadataHelperDTO.builder()
                                .labelMetadata(Constants.GENAI_FIELDS_END_INDICATOR)
                                .labelAndDataConcatenator(labelAndDataConcatenator)
                                .build()
                );
            }

            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_INSTANCE_END_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );

            // Pop the element that was pushed in
            attrXPath.pop();

        } else if (clazz.getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_LIST.getLongName()) ||
                clazz.getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_ARRAYLIST.getLongName())) {
            // Handling list element
            // Cast the instance to a list
            List l = (List) instance;

            // Add list start marker
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_LIST_START_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );

            // Iterate over the list
            if (l != null && l.size() > 0) {
                for (int i = 0; i < l.size(); i++) {
                    // We know that collections always works on objects
                    // Receive element as an object
                    Object listInstanceObject = l.get(i);
                    if (listInstanceObject != null) {
                        // Now we add a list index into xPath
                        attrXPath.push("[" + i + "]");

                        // Run this object through recursive depth first
                        GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                                listInstanceObject.getClass(),
                                listInstanceObject,
                                genAILabelsDiscoveredList,
                                attrXPath,
                                fieldsToSkipMap,
                                getterMethodDiscoverer,
                                labelAndDataConcatenator
                        );

                        // Pop the list element
                        attrXPath.pop();
                    }
                    if (i < l.size() - 1) {
                        // Add list element field separator
                        Functions.addGenAILabelMetadataMarker.apply(
                                genAILabelsDiscoveredList,
                                GenAILabelMetadataHelperDTO.builder()
                                        .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                        .labelAndDataConcatenator(labelAndDataConcatenator)
                                        .build()
                        );
                    }
                }
            }

            // Add list end marker
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_LIST_END_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );
        } else if (clazz.getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_MAP.getLongName()) ||
                clazz.getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_HASHMAP.getLongName())) {
            // Handling map element
            // Cast the instance to a map
            Map m = (Map) instance;

            // Add map start marker
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_MAP_START_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );

            if (m != null && m.keySet() != null && m.keySet().size() > 0) {
                Set<Map.Entry> e = m.entrySet();
                Iterator<Map.Entry> iterator = e.iterator();
                while (iterator.hasNext()) {
                    // Certain that entry is not null
                    Map.Entry entry = iterator.next();

                    Object key = entry.getKey();

                    if (key != null) {
                        // Run the key through depth first
                        // TODO: modify XPath here incase we want to add support for per map key object in skip fields
                        GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                                key.getClass(),
                                key,
                                genAILabelsDiscoveredList,
                                attrXPath,
                                fieldsToSkipMap,
                                getterMethodDiscoverer,
                                labelAndDataConcatenator
                        );
                    } else {
                        // We mark this as key unknown
                        genAILabelsDiscoveredList.add(GenAILabelDTO.builder()
                                .label(null)
                                .method(null)
                                .data(Constants.DEFAULT_VALUE_UNKNOWN)
                                .attributeClass(null)
                                .labelAndDataConcatenator(labelAndDataConcatenator)
                                .build());
                    }

                    // Add a Key Value separator marker
                    Functions.addGenAILabelMetadataMarker.apply(
                            genAILabelsDiscoveredList,
                            GenAILabelMetadataHelperDTO.builder()
                                    .labelMetadata(Constants.GENAI_MAP_KV_SEPARATOR_INDICATOR)
                                    .labelAndDataConcatenator(labelAndDataConcatenator)
                                    .build()
                    );

                    Object value = entry.getValue();
                    if (value != null) {
                        // Run the value through depth first
                        // TODO: modify XPath here incase we want to add support for per map value object in skip fields
                        GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                                value.getClass(),
                                value,
                                genAILabelsDiscoveredList,
                                attrXPath,
                                fieldsToSkipMap,
                                getterMethodDiscoverer,
                                labelAndDataConcatenator
                        );
                    } else {
                        // We mark this as value unknown
                        genAILabelsDiscoveredList.add(GenAILabelDTO.builder()
                                .label(null)
                                .method(null)
                                .data(Constants.DEFAULT_VALUE_UNKNOWN)
                                .attributeClass(null)
                                .labelAndDataConcatenator(labelAndDataConcatenator)
                                .build());
                    }

                    if (iterator.hasNext()) {
                        // Add map element field separator
                        Functions.addGenAILabelMetadataMarker.apply(
                                genAILabelsDiscoveredList,
                                GenAILabelMetadataHelperDTO.builder()
                                        .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                        .labelAndDataConcatenator(labelAndDataConcatenator)
                                        .build()
                        );
                    }
                }
            }

            // Add map end marker
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_MAP_END_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );
        } else if (instance.getClass().isArray()) {
            // Add list start marker
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_LIST_START_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );

            // We have 2 cases to handle here
            if (instance.getClass().getComponentType().isPrimitive()) {
                // Case - 1, array of primitives
                Object[] objectArr = Functions.toObject.apply(instance);

                // Iterate over elements in the Object array
                for (int i = 0 ; i < objectArr.length; i++) {
                    genAILabelsDiscoveredList.add(GenAILabelDTO.builder()
                            .label(null)
                            .method(null)
                            .data(objectArr[i])
                            .attributeClass(null)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build());

                    if (i < objectArr.length - 1) {
                        // Add list/array element field separator
                        Functions.addGenAILabelMetadataMarker.apply(
                                genAILabelsDiscoveredList,
                                GenAILabelMetadataHelperDTO.builder()
                                        .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                        .labelAndDataConcatenator(labelAndDataConcatenator)
                                        .build()
                        );
                    }
                }
            } else {
                // Case - 2, array of objects
                Object[] objectArr = (Object[]) instance;

                // Iterate over elements in the Object array
                for (int i = 0 ; i < objectArr.length; i++) {
                    // Fetch the object inside the array
                    Object objectArrInstance = objectArr[i];

                    // Run the object instance inside the array through depth first
                    // TODO: modify XPath here incase we want to add support for per map value object in skip fields
                    GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                            objectArrInstance.getClass(),
                            objectArrInstance,
                            genAILabelsDiscoveredList,
                            attrXPath,
                            fieldsToSkipMap,
                            getterMethodDiscoverer,
                            labelAndDataConcatenator
                    );

                    if (i < objectArr.length - 1) {
                        // Add list/array element field separator
                        Functions.addGenAILabelMetadataMarker.apply(
                                genAILabelsDiscoveredList,
                                GenAILabelMetadataHelperDTO.builder()
                                        .labelMetadata(Constants.GENAI_FIELD_SEPARATOR_INDICATOR)
                                        .labelAndDataConcatenator(labelAndDataConcatenator)
                                        .build()
                        );
                    }
                }
            }

            // Add list end marker
            Functions.addGenAILabelMetadataMarker.apply(
                    genAILabelsDiscoveredList,
                    GenAILabelMetadataHelperDTO.builder()
                            .labelMetadata(Constants.GENAI_LIST_END_INDICATOR)
                            .labelAndDataConcatenator(labelAndDataConcatenator)
                            .build()
            );
        } else {
            // For now this handles both objects and primitives
            // This could be an object inside a collection
            genAILabelsDiscoveredList.add(GenAILabelDTO.builder()
                    .label(null)
                    .method(null)
                    .data(instance)
                    .attributeClass(null)
                    .labelAndDataConcatenator(labelAndDataConcatenator)
                    .build());
        }
    }

    /*
     * this method is invoked on
     * an attribute that has been marked with annotation GenAILabel
     * an attribute that is a java collection that is not annotated with GenAILabel
     *
     */
    private static void discoverNodeLabel(Class clazz,
                                          Object instance,
                                          Field field,
                                          String labelDiscovered,
                                          List<GenAILabelDTO> genAILabelsDiscoveredList,
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

        Object methodReturned = null; Class methodClass = null;
        if (instance != null) {
            methodReturned = getter.invoke(instance, null);
            methodClass = getter.getReturnType();
        }

        // Check if the node that we are in, is a java collection
        // or is an array
        if (field.getType().getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_LIST.getLongName())
                || field.getType().getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_ARRAYLIST.getLongName())
                || field.getType().getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_MAP.getLongName())
                || field.getType().getName().equals(JavaStandardLibraryClassEnum.JAVA_UTIL_HASHMAP.getLongName())
                || field.getType().isArray()) {
            if (labelDiscovered != null && methodReturned != null) {
                genAILabelsDiscoveredList.add(
                        GenAILabelDTO.builder()
                                .label(labelDiscovered)
                                .method(getterMethodInferred)
                                .data(methodReturned)
                                .attributeClass(methodClass)
                                .build()
                );
                // discover the label
                GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                        methodReturned.getClass(),
                        methodReturned,
                        genAILabelsDiscoveredList,
                        attrXPath,
                        fieldsToSkipMap,
                        getterMethodDiscoverer,
                        labelAndDataConcatenator
                );
            } else if (labelDiscovered != null && methodReturned == null) {
                genAILabelsDiscoveredList.add(
                        GenAILabelDTO.builder()
                                .label(labelDiscovered)
                                .method(getterMethodInferred)
                                .data(Constants.DEFAULT_VALUE_UNKNOWN)
                                .attributeClass(methodClass)
                                .build()
                );
            } else if (labelDiscovered == null && methodReturned != null) {
                // discover the label
                GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(
                        methodReturned.getClass(),
                        methodReturned,
                        genAILabelsDiscoveredList,
                        attrXPath,
                        fieldsToSkipMap,
                        getterMethodDiscoverer,
                        labelAndDataConcatenator
                );
            } else {
                genAILabelsDiscoveredList.add(
                        GenAILabelDTO.builder()
                                .label(labelDiscovered)
                                .method(getterMethodInferred)
                                .data(Constants.DEFAULT_VALUE_UNKNOWN)
                                .attributeClass(methodClass)
                                .build()
                );
            }
        } else {
            if (labelDiscovered != null && !XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                // Build a FWValue instance that we would use later
                // to construct the record
                genAILabelsDiscoveredList.add(
                        GenAILabelDTO.builder()
                                .label(labelDiscovered)
                                .method(getterMethodInferred)
                                .data(methodReturned)
                                .attributeClass(methodClass)
                                .labelAndDataConcatenator(labelAndDataConcatenator)
                                .build()
                );
            }
        }
    }
}
