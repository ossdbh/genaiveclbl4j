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
@GenAIInstance(label = "This is a FlatDTOWithStandardObjectArray ")
public class FlatDTOWithStandardObjectArray {
    @GenAILabel(label = "This is String data denoting a name ")
    @GenAILabel(label = "Name is an important piece of information ")
    private String data1;

    @GenAILabel(label = "This is a boolean array ")
    private Boolean[] booleanArray;

    @GenAILabel(label = "This is a byte array ")
    private Byte[] byteArray;

    @GenAILabel(label = "This is a char array ")
    private Character[] charArray;

    @GenAILabel(label = "This is a double array ")
    private Double[] doubleArray;

    @GenAILabel(label = "This is a float array ")
    private Float[] floatArray;

    @GenAILabel(label = "This is an int array ")
    private Integer[] intArray;

    @GenAILabel(label = "This is a long array ")
    private Long[] longArray;

    @GenAILabel(label = "This is a short array ")
    private Short[] shortArray;

    @GenAILabel(label = "This is a map of String:Integer[] ")
    private Map<String, Integer[]> mapOfIntegerArray;

    @GenAILabel(label = "This is biginteger data denoting heartbeat count till date ")
    private BigInteger data2;
}
