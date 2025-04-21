package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.CollectionsSimpleObjectDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.CollectionsSimpleObjectMultiLabeledListDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionsSimpleObjectDTOTest {
    private List<String> stringArrayList = new ArrayList<>();
    private CollectionsSimpleObjectDTO collectionsSimpleObjectDTO;
    private CollectionsSimpleObjectMultiLabeledListDTO collectionsSimpleObjectMultiLabeledListDTO;

    @Before
    public void setup() {
        List<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("list_string1");
        stringArrayList.add("list_string2");
        stringArrayList.add("list_string3");

        this.collectionsSimpleObjectDTO = CollectionsSimpleObjectDTO.builder()
                .someFloat(1234.0f)
                .strBefore("before the list")
                .stringList(stringArrayList)
                .strAfter("after the list")
                .build();

        this.collectionsSimpleObjectMultiLabeledListDTO = CollectionsSimpleObjectMultiLabeledListDTO.builder()
                .someFloat(1234.0f)
                .strBefore("before the list")
                .stringList(stringArrayList)
                .strAfter("after the list")
                .build();
    }

    @Test
    public void testSingleListLabel() {
        String expected = "{This is a DTO that has a Collections object (This is a float label before collections 1234.0,This is a label before before the list,This is a list element [list_string1,list_string2,list_string3],This is a label after after the list)}";

        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(collectionsSimpleObjectDTO);
        System.out.println("Generated Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }

    @Test
    public void testSingleListLabelSkip() {
        String expected = "{This is a DTO that has a Collections object (This is a float label before collections 1234.0,This is a label before before the list,This is a label after after the list)}";

        List<String> l = new ArrayList<>();
        l.add("CollectionsSimpleObjectDTO.stringList");

        // We pass a list of labels to skip
        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(collectionsSimpleObjectDTO, l);
        System.out.println("Generated Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }

    @Test
    public void testMultiListLabel() {
        String expected = "{This is a DTO that has a Collections object (This is a float label before collections 1234.0,This is a label before before the list,This is a list element first label [list_string1,list_string2,list_string3]||This is a list element second label [list_string1,list_string2,list_string3],This is a label after after the list)}";

        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.collectionsSimpleObjectMultiLabeledListDTO);
        System.out.println("Generated Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }

    @Test
    public void testNull() {
        String expected = "";
        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(null);
        System.out.println("Generated Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }
}
