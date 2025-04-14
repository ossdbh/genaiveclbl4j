package io.github.ossdbh.genaiveclbl4j.constants;

import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public class Functions {

    public static void discoverLabel(Class clazz,
                                      Object instance,
                                      Field field,
                                      String labelDiscovered,
                                      List<GenAILabelDTO> fwAttrList,
                                      Function<String, String> getterMethodDiscoverer)
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

        //Infer data type
        Class methodClass = getter.getReturnType();

        // Build a FWValue instance that we would use later
        // to construct the record
        fwAttrList.add(GenAILabelDTO.builder()
                .label(labelDiscovered)
                .method(getterMethodInferred)
                .data(methodReturned)
                .attributeClass(methodClass)
                .build());
    }
}
