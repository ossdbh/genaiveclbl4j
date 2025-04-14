package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.CollectionsComplexObjectWithFlatDTOListLabeledDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.CollectionsComplexObjectWithFlatDTOListWithNoListLabelDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.FlatDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CollectionsComplexObjectWithFlatDTOListWithLabeledDTOTest {

    private List<FlatDTO> flatDTOList;
    private List<String> skipFieldList;

    @Before
    public void setup() {
        FlatDTO flatDTO1 = FlatDTO.builder().data1("data1").data2(BigInteger.valueOf(1234567891l)).build();
        FlatDTO flatDTO2 = FlatDTO.builder().data1("data2").data2(BigInteger.valueOf(2234567891l)).build();
        FlatDTO flatDTO3 = FlatDTO.builder().data1("data3").data2(BigInteger.valueOf(3234567891l)).build();

        this.flatDTOList = new ArrayList<>();
        flatDTOList.add(flatDTO1);
        flatDTOList.add(flatDTO2);
        flatDTOList.add(flatDTO3);

        this.skipFieldList = new ArrayList<>();
        this.skipFieldList.add("CollectionsComplexObjectWithFlatDTOListLabeledDTO.flatDTOList");
    }

    @Test
    public void test() {
        String expected = "This is a DTO that has a Collections object |This is a float label before collections UNKNOWN_VALUE|This is a String label before UNKNOWN_VALUE|This is a flatDTOList This is a flat DTO |This is String data denoting a name data1|Name is an important piece of information data1|This is biginteger data denoting heartbeat count till date 1234567891|This is a flat DTO |This is String data denoting a name data2|Name is an important piece of information data2|This is biginteger data denoting heartbeat count till date 2234567891|This is a flat DTO |This is String data denoting a name data3|Name is an important piece of information data3|This is biginteger data denoting heartbeat count till date 3234567891|This is a label after UNKNOWN_VALUE";

        CollectionsComplexObjectWithFlatDTOListLabeledDTO collectionsDTO =
                CollectionsComplexObjectWithFlatDTOListLabeledDTO.builder().flatDTOList(flatDTOList).build();

        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(collectionsDTO);
        System.out.println("Generated Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }

    @Test
    public void testSkipList() {
        String expected = "This is a DTO that has a Collections object |This is a float label before collections UNKNOWN_VALUE|This is a String label before UNKNOWN_VALUE|This is a label after UNKNOWN_VALUE";

        CollectionsComplexObjectWithFlatDTOListLabeledDTO collectionsDTO =
                CollectionsComplexObjectWithFlatDTOListLabeledDTO.builder().flatDTOList(flatDTOList).build();

        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(collectionsDTO, this.skipFieldList);
        System.out.println("Generated Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }
}
