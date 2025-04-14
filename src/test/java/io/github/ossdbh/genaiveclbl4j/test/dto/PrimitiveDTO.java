package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAINestedInstance;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a Primitive DTO ")
public class PrimitiveDTO {
    @GenAILabel(label = "This is a primitiveDTOInt having value ")
    private int primitiveDTOInt;

    @GenAILabel(label = "This is a primitiveDTOFloat having value ")
    private float primitiveDTOFloat;

    @GenAINestedInstance
    private NestedPrimitiveDTO nestedPrimitiveDTO;

    @GenAILabel(label = "This is a primitiveDTOLong having value ")
    private long primitiveDTOLong;
}
