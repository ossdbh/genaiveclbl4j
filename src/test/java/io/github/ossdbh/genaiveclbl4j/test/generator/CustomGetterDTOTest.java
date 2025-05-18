package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.NestedNonLombokDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.NonLombokDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.NonLombokDTO1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomGetterDTOTest {

    private NonLombokDTO1 nonLombokDTO1;
    private NonLombokDTO nonLombokDTO;

    @Before
    public void setup() {
        this.nonLombokDTO1 = new NonLombokDTO1();
        this.nonLombokDTO = new NonLombokDTO();
    }

    @Test
    public void test1() {
        String expected = "{This is a NonLombokDTO1 (This is a non lombok string abcdefgh,This is a non lombok string 10000)}";

        this.nonLombokDTO1.SET_str1("abcdefgh");
        this.nonLombokDTO1.SET_int1(10000);

        // The function expects you to pass in a String translation rule
        // that would take the attribute name and then apply String translations
        // to generate a getter method string
        // In this example 's' is the attribute value and "GET_" + s is the logical
        // getter method name for each attribute
        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.nonLombokDTO1, s -> "GET_" + s);
        System.out.println("Format: " + format);
        Assert.assertTrue(expected.equals(format));
    }

    @Test
    public void test2() {
        String expected = "{This is a NonLombokDTO (This is a string pqrstuvw,,This is a string 10000000)}";

        this.nonLombokDTO.SET_str1("pqrstuvw");
        this.nonLombokDTO.SET_int1(10000000);

        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.nonLombokDTO, s -> "GET_" + s);
        System.out.println("Expect: " + expected);
        System.out.println("Actual: " + format);
        Assert.assertTrue(expected.equals(format));
    }

    @Test
    public void test3() {
        String expected = "{This is a NonLombokDTO (This is a string pqrstuvw,{(This is a nonlombok nested string attribute whatisthis,This is a nonlombok nested string attribute1000)},This is a string 10000000)}";

        this.nonLombokDTO.SET_str1("pqrstuvw");

        NestedNonLombokDTO nestedNonLombokDTO = new NestedNonLombokDTO();
        nestedNonLombokDTO.SET_str_nested("whatisthis");
        nestedNonLombokDTO.SET_int_nested(1000);
        this.nonLombokDTO.SET_nestedNonLombokDTO(nestedNonLombokDTO);

        this.nonLombokDTO.SET_int1(10000000);

        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.nonLombokDTO, s -> "GET_" + s);
        System.out.println("Expect: " + expected);
        System.out.println("Actual: " + format);
        Assert.assertTrue(expected.equals(format));
    }
}
