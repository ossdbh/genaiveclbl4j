package io.github.ossdbh.genaiveclbl4j.constants;

import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelDTO;
import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelMetadataHelperDTO;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Functions {

    BiFunction<List<GenAILabelDTO>, GenAILabelMetadataHelperDTO, List<GenAILabelDTO>> addGenAILabelMetadataMarker = (list, genAILabelMetadataHelperDTO) -> {
        list.add(
                GenAILabelDTO.builder()
                        .label(null)
                        .method(null)
                        .data(genAILabelMetadataHelperDTO.getLabelMetadata())
                        .attributeClass(null)
                        .labelAndDataConcatenator(genAILabelMetadataHelperDTO.getLabelAndDataConcatenator())
                        .build());
        return list;
    };

    Function<Object, Object[]> toObject = (instance) -> {
        Object[] objectArr = null;
        // Cast instance to an object array
        if (instance instanceof boolean[]) {
            boolean[] primitiveArray = (boolean[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof byte[]) {
            byte[] primitiveArray = (byte[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof char[]) {
            char[] primitiveArray = (char[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof double[]) {
            double[] primitiveArray = (double[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof float[]) {
            float[] primitiveArray = (float[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof int[]) {
            int[] primitiveArray = (int[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof long[]) {
            long[] primitiveArray = (long[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        } else if (instance instanceof short[]) {
            short[] primitiveArray = (short[]) instance;
            objectArr = ArrayUtils.toObject(primitiveArray);
        }
        return objectArr;
    };

}
