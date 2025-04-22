package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a DTOWithPrimitiveArray ")
public class DTOWithPrimitiveArray {
    @GenAILabel(label = "This is an int array inside DTOWithPrimitiveArray ")
    private int[] intArray;
}

