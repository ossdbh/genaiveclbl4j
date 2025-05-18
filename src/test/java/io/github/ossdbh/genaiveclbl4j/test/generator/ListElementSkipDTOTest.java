package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.InsideListDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.ListElementSkipDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ListElementSkipDTOTest {

    @Test
    public void test() {
        String expected = "{This is a DTO that has a List of InsideListDTOs ([{(this is sampleString1 dto1_sampleString1,this is sampleString2 dto1_sampleString2)},{(this is sampleString2 dto2_sampleString2)}],UNKNOWN_VALUE)}";

        List<InsideListDTO> insideListDTOList = new ArrayList<>();
        insideListDTOList.add(InsideListDTO.builder().sampleString1("dto1_sampleString1").sampleString2("dto1_sampleString2").build());
        insideListDTOList.add(InsideListDTO.builder().sampleString1("dto2_sampleString1").sampleString2("dto2_sampleString2").build());

        List<String> skipElements = new ArrayList<>();
        // We want to skip the field sampleString1 inside the DTO InsideListDTO at index 1 in the list insideListDTOList
        skipElements.add("ListElementSkipDTO.insideListDTOList.[1].InsideListDTO.sampleString1");

        ListElementSkipDTO listElementSkipDTO = ListElementSkipDTO.builder().insideListDTOList(insideListDTOList).build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(listElementSkipDTO, skipElements);

        System.out.println("expect: " + expected);
        System.out.println();
        System.out.println("actual: " + generatedLabel);

        Assert.assertTrue(generatedLabel.equals(expected));
    }

    @Test
    public void test2() {
        String expected = "{This is a DTO that has a List of InsideListDTOs ([{(this is sampleString1 dto1_sampleString1,this is sampleString2 dto1_sampleString2)},{(this is sampleString2 dto2_sampleString2)}],[{(this is sampleString1 dto3_sampleString1,)}])}";

        List<InsideListDTO> insideListDTOList = new ArrayList<>();
        insideListDTOList.add(InsideListDTO.builder().sampleString1("dto1_sampleString1").sampleString2("dto1_sampleString2").build());
        insideListDTOList.add(InsideListDTO.builder().sampleString1("dto2_sampleString1").sampleString2("dto2_sampleString2").build());

        List<InsideListDTO> insideListDTOList2 = new ArrayList<>();
        insideListDTOList2.add(InsideListDTO.builder().sampleString1("dto3_sampleString1").sampleString2("dto3_sampleString2").build());

        List<String> skipElements = new ArrayList<>();
        // We want to skip the field sampleString1 inside the DTO InsideListDTO at index 1 in the list insideListDTOList
        skipElements.add("ListElementSkipDTO.insideListDTOList.[1].InsideListDTO.sampleString1");
        skipElements.add("ListElementSkipDTO.insideListDTOList2.[0].InsideListDTO.sampleString2");

        ListElementSkipDTO listElementSkipDTO = ListElementSkipDTO.builder()
                .insideListDTOList(insideListDTOList)
                .insideListDTOList2(insideListDTOList2)
                .build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(listElementSkipDTO, skipElements);

        System.out.println("expect: " + expected);
        System.out.println();
        System.out.println("actual: " + generatedLabel);

        Assert.assertTrue(generatedLabel.equals(expected));
    }
}
