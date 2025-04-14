package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.SimpleObjectWithListOfListDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleObjectWithListOfListDTOTest {

    private SimpleObjectWithListOfListDTO simpleObjectWithListOfListDTO;

    @Before
    public void setup() {
        List<String> l1= new ArrayList<>();
        l1.add("l1_s1");
        l1.add("l1_s2");

        List<String> l2= new ArrayList<>();
        l2.add("l2_s1");
        l2.add("l2_s2");

        List<List<String>> ll = new ArrayList<>();
        ll.add(l1);
        ll.add(l2);

        this.simpleObjectWithListOfListDTO = SimpleObjectWithListOfListDTO.builder().stringListOfList(ll).build();
    }

    @Test
    public void test() {
        String expected = "This is a DTO that has a Collections object |This is a float someFloat with value 0.0|This is a label intBefore with value 0|This is a list element l1_s1|l1_s2|l2_s1|l2_s2|This is a label strAfter with value UNKNOWN_VALUE";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.simpleObjectWithListOfListDTO);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }
}
