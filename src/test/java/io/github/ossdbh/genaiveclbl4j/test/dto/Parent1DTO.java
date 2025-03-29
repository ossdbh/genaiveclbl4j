package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAINestedInstance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a nested DTO label generation test record ")
public class Parent1DTO {
    @GenAILabel(label = "This is a string ")
    private String parentDTOStr1;

    @GenAINestedInstance
    private Child1DTO child1DTO;

    @GenAILabel(label = "This is an Integer ")
    private Integer parentDTOInt1;

    @GenAILabel(label = "This is also an Integer ")
    private Integer parentDTOInt2;

    @GenAILabel(label = "This is another Integer ")
    private Integer parentDTOInt3;
}
