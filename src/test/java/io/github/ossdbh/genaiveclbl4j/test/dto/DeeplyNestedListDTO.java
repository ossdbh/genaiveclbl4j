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
@GenAIInstance(label = "This is a DeeplyNestedListDTO ")
public class DeeplyNestedListDTO {
    @GenAILabel(label = "This is a deeplyNestedListDTOint ")
    private int deeplyNestedListDTOint;

    @GenAILabel(label = "This is a l_1_nodeList ")
    private List<L_1_Node> l_1_nodeList;

    @GenAILabel(label = "This is a deeplyNestedListDTOfloat ")
    private float deeplyNestedListDTOfloat;
}
