package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAINestedInstance;
import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.NestedPrimitiveDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.PrimitiveDTO;
import org.junit.Assert;
import org.junit.Test;

public class PrimitiveDTOTest {

    @Test
    public void test() {
        String expected = "{This is a Primitive DTO (This is a primitiveDTOInt having value 0,This is a primitiveDTOFloat having value 0.0,{This is a NestedPrimitiveDTO DTO (This is a nestedPrimitiveDTOInt having value 0,This is a nestedPrimitiveDTOFloat having value 0.0,This is a nestedPrimitiveDTOLong having value 0)}This is a primitiveDTOLong having value 0)}";
        PrimitiveDTO primitiveDTO = PrimitiveDTO.builder().nestedPrimitiveDTO(NestedPrimitiveDTO.builder().build()).build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(primitiveDTO);

        System.out.println("GeneratedLabel: " + generatedLabel);

        Assert.assertTrue(expected.equals(generatedLabel));
    }
}
