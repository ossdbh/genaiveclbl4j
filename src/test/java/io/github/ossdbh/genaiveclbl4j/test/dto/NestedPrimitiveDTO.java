package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a NestedPrimitiveDTO DTO ")
public class NestedPrimitiveDTO {
    @GenAILabel(label = "This is a nestedPrimitiveDTOInt having value ")
    private int nestedPrimitiveDTOInt;

    @GenAILabel(label = "This is a nestedPrimitiveDTOFloat having value ")
    private float nestedPrimitiveDTOFloat;

    @GenAILabel(label = "This is a nestedPrimitiveDTOLong having value ")
    private long nestedPrimitiveDTOLong;
}
