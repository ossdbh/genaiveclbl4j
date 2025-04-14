package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
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
@GenAIInstance(label = "This is a LeafNodeDTO ")
public class LeafNodeDTO {
    @GenAILabel(label = "This is a leafNodeDTOInt ")
    private int leafNodeDTOInt;
}
