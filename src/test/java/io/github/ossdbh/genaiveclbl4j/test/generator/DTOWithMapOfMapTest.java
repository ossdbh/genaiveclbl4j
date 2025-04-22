package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithMap;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithMapOfMap;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithMapOfMapOfList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTOWithMapOfMapTest {

    @Test
    public void test1() {
        DTOWithMap dtoWithMap = DTOWithMap.builder().data1("data1").stringMap(null).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of String:String UNKNOWN_VALUE ,This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMap);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test2() {
        Map<String, String> m1 = new HashMap<>();
        m1.put("1", "1");
        m1.put("2", "2");

        Map<String, String> m2 = new HashMap<>();
        m2.put("3", "3");
        m2.put("4", "4");

        Map<String, Map<String,String>> mom = new HashMap<>();
        mom.put("m1", m1);
        mom.put("m2", m2);

        DTOWithMapOfMap dtoWithMapOfMap = DTOWithMapOfMap.builder().data1("data1").stringMapOfMap(mom).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of Map String:Map-String:String {m1<=>{1<=>1,2<=>2},m2<=>{3<=>3,4<=>4}},This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMapOfMap);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test3() {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1); l1.add(2);

        List<Integer> l2 = new ArrayList<>();
        l2.add(1); l2.add(2);

        Map<String, List<Integer>> m1 = new HashMap<>();
        m1.put("1", l1);
        m1.put("2", l2);

        List<Integer> l3 = new ArrayList<>();
        l3.add(1); l3.add(2);

        List<Integer> l4 = new ArrayList<>();
        l4.add(1); l4.add(2);

        Map<String, List<Integer>> m2 = new HashMap<>();
        m2.put("3", l3);
        m2.put("4", l4);

        Map<String, Map<String,List<Integer>>> mom = new HashMap<>();
        mom.put("m1", m1);
        mom.put("m2", m2);

        DTOWithMapOfMapOfList dtoWithMapOfMapOfList = DTOWithMapOfMapOfList.builder().data1("data1").stringMapOfMap(mom).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of Map String:Map->String:List::Integer {m1<=>{1<=>[1,2],2<=>[1,2]},m2<=>{3<=>[1,2],4<=>[1,2]}},This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMapOfMapOfList);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test4() {
        Map<String, String> m1 = new HashMap<>();

        Map<String, String> m2 = new HashMap<>();
        m2.put("3", "3");
        m2.put("4", "4");

        Map<String, Map<String,String>> mom = new HashMap<>();
        mom.put("m1", m1);
        mom.put("m2", m2);

        DTOWithMapOfMap dtoWithMapOfMap = DTOWithMapOfMap.builder().data1("data1").stringMapOfMap(mom).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of Map String:Map-String:String {m1<=>{},m2<=>{3<=>3,4<=>4}},This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMapOfMap);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));

        int[] a = new int[] {1,2,3};
        if (a.getClass().isArray()) {
            System.out.println("this is an array");
            if (a.getClass().getComponentType().isPrimitive()) {
                System.out.println(" this is a primitive array");
            }
        }
    }

}
