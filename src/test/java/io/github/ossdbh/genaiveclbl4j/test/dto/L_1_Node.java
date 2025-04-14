package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a LeafNodeDTO ")
public class L_1_Node {
    @GenAILabel(label = "This is a leafNodeDTOList ")
    List<LeafNodeDTO> leafNodeDTOList;

    @GenAILabel(label = "This is a String list called l_1_node_list ")
    List<String> l_1_node_list;
}
