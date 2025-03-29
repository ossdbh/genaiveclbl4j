package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.NonLombokDTO3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomGetterInheritanceDTOTest {

    private NonLombokDTO3 nonLombokDTO3;

    @Before
    public void setup() {
        this.nonLombokDTO3 = new NonLombokDTO3("BaseClass", 100, "DerivedClass", 1000);
    }

    @Test
    public void test1() {

        // The function expects you to pass in a String translation rule
        // that would take the attribute name and then apply String translations
        // to generate a getter method string
        // In this example 's' is the attribute value and "GET_" + s is the logical
        // getter method name for each attribute
        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.nonLombokDTO3, s -> "GET_" + s);
        System.out.println(format);
        Assert.assertTrue("This is a non lombok string in base class with value: BaseClass | This is a non lombok int in base class with value: 100 | This is a non lombok string in inherited class with value: DerivedClass | This is a non lombok int in inherited class with value: 1000 | I also support multiple labels on the same field 1000".equals(format));
    }
}
