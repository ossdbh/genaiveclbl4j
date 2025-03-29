package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a nested child dto record, another level 1 instance begins ")
public class Child2DTO {
    @GenAILabel(label = "This is a nested string, level 1 ")
    private String child2DTOStr1;

    @GenAILabel(label = "This is also a nested string, another level 1 exiting")
    private Integer child2DTOInt1;
}
