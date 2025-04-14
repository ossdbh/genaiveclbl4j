package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAINestedInstance;
import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.NestedPrimitiveDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.PrimitiveDTO;
import org.junit.Test;

public class PrimitiveDTOTest {

    @Test
    public void test() {
        PrimitiveDTO primitiveDTO = PrimitiveDTO.builder().nestedPrimitiveDTO(NestedPrimitiveDTO.builder().build()).build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(primitiveDTO);

        System.out.println("GeneratedLabel: " + generatedLabel);
    }
}
