package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithMap;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithMapOfMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
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

}
