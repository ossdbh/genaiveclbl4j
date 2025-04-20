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
@GenAIInstance(label = "This is a nested child dto record @level1 starts ")
public class Child1DTO {
    @GenAILabel(label = "This is a nested String @level1 ")
    private String child1DTOStr1;

    @GenAINestedInstance
    private ChildOfChild11DTO childOfChild11DTO;

    @GenAILabel(label = "This is also a nested String @level1 exiting")
    private Integer child1DTOInt1;

}
