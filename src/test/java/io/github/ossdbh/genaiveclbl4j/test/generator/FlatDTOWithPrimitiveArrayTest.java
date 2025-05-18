package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithPrimitiveArray;
import io.github.ossdbh.genaiveclbl4j.test.dto.FlatDTOWithPrimitiveArray;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatDTOWithPrimitiveArrayTest {

    @Test
    public void test1() {
        String expected = "{This is a flat DTO (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,This is a boolean array UNKNOWN_VALUE,This is a byte array UNKNOWN_VALUE,This is a char array UNKNOWN_VALUE,This is a double array UNKNOWN_VALUE,This is a float array UNKNOWN_VALUE,This is an int array UNKNOWN_VALUE,This is a long array UNKNOWN_VALUE,This is a short array UNKNOWN_VALUE,This is a map of String:DTOWithPrimitiveArray[] UNKNOWN_VALUE,This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )}";
        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray.builder().build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(generatedLabel.equals(expected));
    }

    @Test
    public void test2() {
        String expected = "{This is a flat DTO (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,This is a boolean array UNKNOWN_VALUE,This is a byte array UNKNOWN_VALUE,This is a char array UNKNOWN_VALUE,This is a double array UNKNOWN_VALUE,This is a float array UNKNOWN_VALUE,This is a long array UNKNOWN_VALUE,This is a short array UNKNOWN_VALUE,This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )}";
        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray.builder().build();

        List<String> skipList = new ArrayList<>();
        skipList.add("FlatDTOWithPrimitiveArray.intArray");
        skipList.add("FlatDTOWithPrimitiveArray.mapOfDTOWithPrimitiveArray");

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray, skipList);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test3() {
        Map<String, DTOWithPrimitiveArray[]> map = new HashMap<>();

        String expected = "{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE,This is a map of String:DTOWithPrimitiveArray[] {},This is biginteger data denoting heartbeat count till date 123123123)}";

        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray
                .builder()
                .data1("data1")
                .booleanArray(new boolean[]{true, false})
                .byteArray(new byte[]{0x1f, 0x7f, 0x03})
                .intArray(new int[]{1,2,3})
                .charArray(new char[]{'a', 'b', 'c'})
                .doubleArray(new double[]{125.123, 125.123, 125.123})
                .floatArray(new float[]{1.0f, 125.123f, 125.123f})
                .longArray(new long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfDTOWithPrimitiveArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test4() {
        Map<String, DTOWithPrimitiveArray[]> map = new HashMap<>();
        map.put("1", new DTOWithPrimitiveArray[]{});

        String expected = "{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE,This is a map of String:DTOWithPrimitiveArray[] {1<=>[]},This is biginteger data denoting heartbeat count till date 123123123)}";

        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray
                .builder()
                .data1("data1")
                .booleanArray(new boolean[]{true, false})
                .byteArray(new byte[]{0x1f, 0x7f, 0x03})
                .intArray(new int[]{1,2,3})
                .charArray(new char[]{'a', 'b', 'c'})
                .doubleArray(new double[]{125.123, 125.123, 125.123})
                .floatArray(new float[]{1.0f, 125.123f, 125.123f})
                .longArray(new long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfDTOWithPrimitiveArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test5() {
        DTOWithPrimitiveArray dtoWithPrimitiveArray = DTOWithPrimitiveArray.builder().build();

        Map<String, DTOWithPrimitiveArray[]> map = new HashMap<>();
        map.put("1", new DTOWithPrimitiveArray[]{dtoWithPrimitiveArray});

        String expected = "{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE,This is a map of String:DTOWithPrimitiveArray[] {1<=>[{This is a DTOWithPrimitiveArray (This is an int array inside DTOWithPrimitiveArray UNKNOWN_VALUE)}]},This is biginteger data denoting heartbeat count till date 123123123)}";

        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray
                .builder()
                .data1("data1")
                .booleanArray(new boolean[]{true, false})
                .byteArray(new byte[]{0x1f, 0x7f, 0x03})
                .intArray(new int[]{1,2,3})
                .charArray(new char[]{'a', 'b', 'c'})
                .doubleArray(new double[]{125.123, 125.123, 125.123})
                .floatArray(new float[]{1.0f, 125.123f, 125.123f})
                .longArray(new long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfDTOWithPrimitiveArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test6() {
        DTOWithPrimitiveArray dtoWithPrimitiveArray = DTOWithPrimitiveArray.builder().intArray(new int[]{}).build();

        Map<String, DTOWithPrimitiveArray[]> map = new HashMap<>();
        map.put("1", new DTOWithPrimitiveArray[]{dtoWithPrimitiveArray});

        String expected = "{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE,This is a map of String:DTOWithPrimitiveArray[] {1<=>[{This is a DTOWithPrimitiveArray (This is an int array inside DTOWithPrimitiveArray [])}]},This is biginteger data denoting heartbeat count till date 123123123)}";

        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray
                .builder()
                .data1("data1")
                .booleanArray(new boolean[]{true, false})
                .byteArray(new byte[]{0x1f, 0x7f, 0x03})
                .intArray(new int[]{1,2,3})
                .charArray(new char[]{'a', 'b', 'c'})
                .doubleArray(new double[]{125.123, 125.123, 125.123})
                .floatArray(new float[]{1.0f, 125.123f, 125.123f})
                .longArray(new long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfDTOWithPrimitiveArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test7() {
        DTOWithPrimitiveArray dtoWithPrimitiveArray = DTOWithPrimitiveArray.builder().intArray(new int[]{1,2,3,4}).build();

        Map<String, DTOWithPrimitiveArray[]> map = new HashMap<>();
        map.put("1", new DTOWithPrimitiveArray[]{dtoWithPrimitiveArray});

        String expected = "{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is a boolean array [true,false],This is a byte array [31,127,3],This is a char array [a,b,c],This is a double array [125.123,125.123,125.123],This is a float array [1.0,125.123,125.123],This is an int array [1,2,3],This is a long array [1223343324,12313234234,23423423234],This is a short array UNKNOWN_VALUE,This is a map of String:DTOWithPrimitiveArray[] {1<=>[{This is a DTOWithPrimitiveArray (This is an int array inside DTOWithPrimitiveArray [1,2,3,4])}]},This is biginteger data denoting heartbeat count till date 123123123)}";

        FlatDTOWithPrimitiveArray flatDTOWithPrimitiveArray = FlatDTOWithPrimitiveArray
                .builder()
                .data1("data1")
                .booleanArray(new boolean[]{true, false})
                .byteArray(new byte[]{0x1f, 0x7f, 0x03})
                .intArray(new int[]{1,2,3})
                .charArray(new char[]{'a', 'b', 'c'})
                .doubleArray(new double[]{125.123, 125.123, 125.123})
                .floatArray(new float[]{1.0f, 125.123f, 125.123f})
                .longArray(new long[]{1223343324l, 12313234234l, 23423423234l})
                .mapOfDTOWithPrimitiveArray(map)
                .data2(BigInteger.valueOf(123123123l))
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTOWithPrimitiveArray);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

}
