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
@GenAIInstance(label = "This is a nested DTO label generation test record ")
public class ParentDTO {
    @GenAILabel(label = "This is a string ")
    private String parentDTOStr1;

    @GenAINestedInstance
    private Child1DTO child1DTO;

    @GenAINestedInstance
    private Child2DTO child2DTO;

    @GenAILabel(label = "This is an Integer ")
    private Integer parentDTOInt1;

    @GenAILabel(label = "This is also an Integer ")
    private Integer parentDTOInt2;

    @GenAILabel(label = "This is another Integer ")
    private Integer parentDTOInt3;
}
