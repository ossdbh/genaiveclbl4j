package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.FlatDTOWithPrimitiveArray;
import io.github.ossdbh.genaiveclbl4j.test.dto.FlatDTOWithStandardObjectArray;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatDTOWithStandardObjectArrayTest {

    @Test
    public void test1() {
        String expected = "{This is a FlatDTOWithStandardObjectArray (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,This is a boolean array UNKNOWN_VALUE ,This is a byte array UNKNOWN_VALUE ,This is a char array UNKNOWN_VALUE ,This is a double array UNKNOWN_VALUE ,This is a float array UNKNOWN_VALUE ,This is an int array UNKNOWN_VALUE ,This is a long array UNKNOWN_VALUE ,This is a short array UNKNOWN_VALUE ,This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )}";

        FlatDTOWithStandardObjectArray flatDTOWithPrimitiveArray = FlatDTOWithStandardObjectArray.builder().build();

        List<String> skipFieldsList = new ArrayList<>();
        skipFieldsList.add("FlatDTOWithStandardObjectArray.mapOfIntegerArray");

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray, skipFieldsList);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(generatedLabel.equals(expected));
    }

    @Test
    public void test2() {
        String expected = "{This is a FlatDTOWithStandardObjectArray (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,This is a boolean array UNKNOWN_VALUE ,This is a byte array UNKNOWN_VALUE ,This is a char array UNKNOWN_VALUE ,This is a double array UNKNOWN_VALUE ,This is a float array UNKNOWN_VALUE ,This is an int array UNKNOWN_VALUE ,This is a long array UNKNOWN_VALUE ,This is a short array UNKNOWN_VALUE ,This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )}";

        FlatDTOWithStandardObjectArray flatDTOWithPrimitiveArray = FlatDTOWithStandardObjectArray.builder().build();

        List<String> skipList = new ArrayList<>();
        skipList.add("FlatDTOWithPrimitiveArray.intArray");
        skipList.add("FlatDTOWithStandardObjectArray.mapOfIntegerArray");

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray, skipList);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test3() {
        String expected = "{This is a FlatDTOWithStandardObjectArray (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE ,This is a map of String:Integer[] UNKNOWN_VALUE ,This is biginteger data denoting heartbeat count till date 123123123)}";

        FlatDTOWithStandardObjectArray flatDTOWithPrimitiveArray = FlatDTOWithStandardObjectArray
                .builder()
                .data1("data1")
                .booleanArray(new Boolean[]{true, false})
                .byteArray(new Byte[]{0x1f, 0x7f, 0x03})
                .intArray(new Integer[]{1,2,3})
                .charArray(new Character[]{'a', 'b', 'c'})
                .doubleArray(new Double[]{125.123, 125.123, 125.123})
                .floatArray(new Float[]{1.0f, 125.123f, 125.123f})
                .longArray(new Long[]{1223343324l, 12313234234l, 23423423234l})
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test4() {
        String expected = "{This is a FlatDTOWithStandardObjectArray (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE ,This is a map of String:Integer[] {},This is biginteger data denoting heartbeat count till date 123123123)}";

        Map<String, Integer[]> map = new HashMap<>();

        FlatDTOWithStandardObjectArray flatDTOWithPrimitiveArray = FlatDTOWithStandardObjectArray
                .builder()
                .data1("data1")
                .booleanArray(new Boolean[]{true, false})
                .byteArray(new Byte[]{0x1f, 0x7f, 0x03})
                .intArray(new Integer[]{1,2,3})
                .charArray(new Character[]{'a', 'b', 'c'})
                .doubleArray(new Double[]{125.123, 125.123, 125.123})
                .floatArray(new Float[]{1.0f, 125.123f, 125.123f})
                .longArray(new Long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfIntegerArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test5() {
        String expected = "{This is a FlatDTOWithStandardObjectArray (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE ,This is a map of String:Integer[] {1<=>[]},This is biginteger data denoting heartbeat count till date 123123123)}";

        Map<String, Integer[]> map = new HashMap<>();
        map.put("1", new Integer[]{});

        FlatDTOWithStandardObjectArray flatDTOWithPrimitiveArray = FlatDTOWithStandardObjectArray
                .builder()
                .data1("data1")
                .booleanArray(new Boolean[]{true, false})
                .byteArray(new Byte[]{0x1f, 0x7f, 0x03})
                .intArray(new Integer[]{1,2,3})
                .charArray(new Character[]{'a', 'b', 'c'})
                .doubleArray(new Double[]{125.123, 125.123, 125.123})
                .floatArray(new Float[]{1.0f, 125.123f, 125.123f})
                .longArray(new Long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfIntegerArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test6() {
        String expected = "{This is a FlatDTOWithStandardObjectArray (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE ,This is a map of String:Integer[] {1<=>[1]},This is biginteger data denoting heartbeat count till date 123123123)}";

        Map<String, Integer[]> map = new HashMap<>();
        map.put("1", new Integer[]{1});

        FlatDTOWithStandardObjectArray flatDTOWithPrimitiveArray = FlatDTOWithStandardObjectArray
                .builder()
                .data1("data1")
                .booleanArray(new Boolean[]{true, false})
                .byteArray(new Byte[]{0x1f, 0x7f, 0x03})
                .intArray(new Integer[]{1,2,3})
                .charArray(new Character[]{'a', 'b', 'c'})
                .doubleArray(new Double[]{125.123, 125.123, 125.123})
                .floatArray(new Float[]{1.0f, 125.123f, 125.123f})
                .longArray(new Long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfIntegerArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

}
