package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTOWithMapOfFlatDTOTest {

    @Test
    public void test1() {
        DTOWithMapOfFlatDTO dtoWithMapOfFlatDTO = DTOWithMapOfFlatDTO.builder().build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,This is Map of String:FlatDTO UNKNOWN_VALUE ,This is int data 0)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMapOfFlatDTO);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test2() {
        DTOWithMapOfFlatDTO dtoWithMapOfFlatDTO = DTOWithMapOfFlatDTO.builder().data1("data1").flatDTOMap(null).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of String:FlatDTO UNKNOWN_VALUE ,This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMapOfFlatDTO);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void test3() {
        List<FlatDTO> l1 = new ArrayList<>();

        Map<String, List<FlatDTO>> map = new HashMap<>();
        map.put("1", null);
        map.put("2", null);
        map.put(null, null);
        map.put("3", l1);

        List<FlatDTO> l2 = new ArrayList<>();
        l2.add(FlatDTO.builder().data1("data1").build());
        l2.add(FlatDTO.builder().data1("data1").data2(BigInteger.valueOf(12345678l)).build());
        map.put("4", l2);

        DTOWithMapOfFlatDTO dtoWithMapOfFlatDTO = DTOWithMapOfFlatDTO.builder().data1("data1").flatDTOMap(map).data2(100).build();

        String expected = "{This is a DTO with a Map (This is String data denoting a name data1||Name is an important piece of information data1,This is Map of String:FlatDTO {UNKNOWN_VALUE<=>UNKNOWN_VALUE,1<=>UNKNOWN_VALUE,2<=>UNKNOWN_VALUE,3<=>[],4<=>[{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )},{This is a flat DTO (This is String data denoting a name data1||Name is an important piece of information data1,This is biginteger data denoting heartbeat count till date 12345678)}]},This is int data 100)}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(dtoWithMapOfFlatDTO);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }

}
