package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a nested child dto record, level 2 instance begins ")
public class ChildOfChild11DTO {
    @GenAILabel(label = "This is a deeply nested string, level 2 ")
    @GenAILabel(label = "Testing multiple labels, level 2 ")
    private String childOfChild1DTOStr1;

    @GenAILabel(label = "This is a deeply nested integer, level 2 exiting ")
    private Integer childOfChild1DTOInt1;
}
