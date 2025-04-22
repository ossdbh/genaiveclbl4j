package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

import java.math.BigInteger;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a flat DTO ")
public class FlatDTOWithPrimitiveArray {
    @GenAILabel(label = "This is String data denoting a name ")
    @GenAILabel(label = "Name is an important piece of information ")
    private String data1;

    @GenAILabel(label = "This is a boolean array ")
    private boolean[] booleanArray;

    @GenAILabel(label = "This is a byte array ")
    private byte[] byteArray;

    @GenAILabel(label = "This is a char array ")
    private char[] charArray;

    @GenAILabel(label = "This is a double array ")
    private double[] doubleArray;

    @GenAILabel(label = "This is a float array ")
    private float[] floatArray;

    @GenAILabel(label = "This is an int array ")
    private int[] intArray;

    @GenAILabel(label = "This is a long array ")
    private long[] longArray;

    @GenAILabel(label = "This is a short array ")
    private short[] shortArray;

    @GenAILabel(label = "This is a map of String:DTOWithPrimitiveArray[] ")
    private Map<String, DTOWithPrimitiveArray[]> mapOfDTOWithPrimitiveArray;

    @GenAILabel(label = "This is biginteger data denoting heartbeat count till date ")
    private BigInteger data2;
}