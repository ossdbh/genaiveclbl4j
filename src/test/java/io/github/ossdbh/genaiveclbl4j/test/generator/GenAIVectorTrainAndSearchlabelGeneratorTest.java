package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenAIVectorTrainAndSearchlabelGeneratorTest {

    ParentDTO parentDTO = null;
    Parent1DTO parentDTO1 = null;


    @Before
    public void setup() {
        this.parentDTO = ParentDTO.builder()
                .parentDTOStr1("l_val")
                .child1DTO(Child1DTO.builder()
                        .child1DTOStr1("l1_val")
                        .childOfChild11DTO(ChildOfChild11DTO.builder()
                                .childOfChild1DTOStr1("l2_val")
                                .childOfChild1DTOInt1(20)
                                .build())
                        .child1DTOInt1(10)
                        .build())
                .child2DTO(Child2DTO.builder()
                        .child2DTOStr1("l1_val")
                        .child2DTOInt1(30)
                        .build())
                .parentDTOInt1(40)
                .parentDTOInt2(50)
                .parentDTOInt3(123)
                .build();


        this.parentDTO1 = Parent1DTO.builder()
                .parentDTOStr1("l")
                .child1DTO(Child1DTO.builder()
                        .child1DTOStr1("level2")
                        .child1DTOInt1(10)
                        .build())
                .parentDTOInt1(40)
                .parentDTOInt2(50)
                .parentDTOInt3(123)
                .build();
    }

    @Test
    public void testFWRecordGenerator() {
        String expected = "(This is a nested DTO label generation test record (This is a string l_val,This is a nested child dto record @level1 starts (This is a nested String @level1 l1_val,This is a nested child dto record, level 2 instance begins (This is a deeply nested string, level 2 l2_val||Testing multiple labels, level 2 l2_val,This is a deeply nested integer, level 2 exiting 20)This is also a nested String @level1 exiting10)This is a nested child dto record another @level1 instance begins (This is a nested string @level1 l1_val,This is also a nested string another @level1 exiting 30)This is an Integer 40,This is also an Integer 50,This is another Integer 123))";
        String fwFormattedRecord = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.parentDTO);

        System.out.println(fwFormattedRecord);

        Assert.assertTrue(expected.equals(fwFormattedRecord));
    }

    @Test
    public void testFWRecordGeneratorSkip() {
        String expected = "(This is a nested DTO label generation test record (This is a string l_val,This is a nested child dto record @level1 starts (This is a nested String @level1 l1_val,This is a nested child dto record, level 2 instance begins This is also a nested String @level1 exiting10)This is a nested child dto record another @level1 instance begins (This is a nested string @level1 l1_val,This is also a nested string another @level1 exiting 30)This is also an Integer 50,This is another Integer 123))";
        List<String> l = new ArrayList<>();
        l.add("ParentDTO.parentDTOInt1");
        l.add("ParentDTO.Child1DTO.ChildOfChild11DTO");
        String fwFormattedRecord = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.parentDTO, l);

        System.out.println(fwFormattedRecord);

        // We skipped the 3 level nested dto ChildOfChild11DTO and parent level attribute parentDTOInt1
        // Hence the assert value as compared to the previous test is level32010 removed and right justified 40 removed
        Assert.assertTrue(expected.equals(fwFormattedRecord));
    }

    @Test
    public void testListToMap() {
        List<String> l = new ArrayList<>();
        l.add("ParentDTO.parentDTOInt1");
        l.add("ParentDTO.Child1DTO.ChildOfChild11DTO");
        Assert.assertTrue(l.stream().collect(Collectors.toMap(Function.identity(), s -> s.length())).entrySet().size() == 2);
    }

}
