package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.FlatDTO;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class GenAiVecMultiLabelTest {

    @Test
    public void test() {
        String expected = "This is a flat DTO |This is String data denoting a name Data1_Name|Name is an important piece of information Data1_Name|This is biginteger data denoting heartbeat count till date 1743289014512";
        FlatDTO flatDTO = FlatDTO.builder().data1("Data1_Name").data2(BigInteger.valueOf(1743289014512l)).build();

        String label = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(flatDTO);
        System.out.println("Label: " + label);

        Assert.assertTrue(label.equals(expected));
    }
}
