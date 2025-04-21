package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.Chld;
import io.github.ossdbh.genaiveclbl4j.test.dto.DTOWithListOfInheritedComplexObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DTOWithListOfInheritedComplexObjectTest {

    private DTOWithListOfInheritedComplexObject dtoWithListOfInheritedComplexObject;
    private List<String> skipFieldList;

    @Before
    public void setup() {
        List<Chld> chldList = new ArrayList<>();
        chldList.add(Chld.chldBuilder().build());
        chldList.add(Chld.chldBuilder().build());
        this.dtoWithListOfInheritedComplexObject = DTOWithListOfInheritedComplexObject.builder().chldList(chldList).build();

        this.skipFieldList = new ArrayList<>();
        this.skipFieldList.add("DTOWithListOfInheritedComplexObject.chldList.Chld.parentName");
    }

    @Test
    public void test() {
        String expected = "{This is a flat DTO (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,this is a List of Chlds [{This is a Chld Instance (This is a String in a parent class UNKNOWN_VALUE,This is an int in a parent class 0,This is a String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is another String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is an int in a deep stacked inherited child class @level1 0)},{This is a Chld Instance (This is a String in a parent class UNKNOWN_VALUE,This is an int in a parent class 0,This is a String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is another String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is an int in a deep stacked inherited child class @level1 0)}],This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.dtoWithListOfInheritedComplexObject);
        System.out.println("Generated label: " + generatedLabel);

        // Assert.assertTrue(expected.equals(generatedLabel));
    }

    @Test
    public void testSkipFieldInPrnt() {
        String expected = "{This is a flat DTO (This is String data denoting a name UNKNOWN_VALUE||Name is an important piece of information UNKNOWN_VALUE,this is a List of Chlds [{This is a Chld Instance (This is an int in a parent class 0,This is a String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is another String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is an int in a deep stacked inherited child class @level1 0)},{This is a Chld Instance (This is an int in a parent class 0,This is a String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is another String in a deep stacked inherited child class @level1 UNKNOWN_VALUE,This is an int in a deep stacked inherited child class @level1 0)}],This is biginteger data denoting heartbeat count till date UNKNOWN_VALUE )}";

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.dtoWithListOfInheritedComplexObject, this.skipFieldList);
        System.out.println("Generated Label: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }
}
