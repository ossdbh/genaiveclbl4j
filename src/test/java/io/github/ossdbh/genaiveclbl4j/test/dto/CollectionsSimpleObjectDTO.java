package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a DTO that has a Collections object ")
public class CollectionsSimpleObjectDTO {
    @GenAILabel(label = "This is a float label before collections ")
    private Float someFloat;

    @GenAILabel(label = "This is a label before ")
    private String strBefore;

    @GenAILabel(label = "This is a list element ")
    private List<String> stringList;

    @GenAILabel(label = "This is a label after ")
    private String strAfter;
}
