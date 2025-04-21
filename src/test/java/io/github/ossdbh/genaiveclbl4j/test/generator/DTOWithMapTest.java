package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DTOWithMapTest {

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
        Map<String, String> m = new HashMap<>();
        m.put("1", "1");
        m.put("2", "2");

        DTOWithMap dtoWithMap = DTOWithMap.builder().data1("data1").stringMap(m).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of String:String {1<=>1,2<=>2},This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMap);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

}
