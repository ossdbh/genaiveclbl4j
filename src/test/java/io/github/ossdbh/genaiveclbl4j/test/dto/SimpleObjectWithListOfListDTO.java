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
public class SimpleObjectWithListOfListDTO {
    @GenAILabel(label = "This is a float someFloat with value ")
    private float someFloat;

    @GenAILabel(label = "This is a label intBefore with value ")
    private int intBefore;

    @GenAILabel(label = "This is a list element ")
    private List<List<String>> stringListOfList;

    @GenAILabel(label = "This is a label strAfter with value ")
    private String strAfter;
}
