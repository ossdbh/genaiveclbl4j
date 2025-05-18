package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.DeeplyNestedListDTO;
import io.github.ossdbh.genaiveclbl4j.test.dto.L_1_Node;
import io.github.ossdbh.genaiveclbl4j.test.dto.LeafNodeDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeeplyNestedListDTOTest {

    @Test
    public void test() {
        String expected = "{This is a DeeplyNestedListDTO (This is a deeplyNestedListDTOint 0,This is a l_1_nodeList [{This is a LeafNodeDTO (This is a leafNodeDTOList [{This is a LeafNodeDTO (This is a leafNodeDTOInt 0)},{This is a LeafNodeDTO (This is a leafNodeDTOInt 0)}],This is a String list called l_1_node_list UNKNOWN_VALUE)},{This is a LeafNodeDTO (This is a leafNodeDTOList [{This is a LeafNodeDTO (This is a leafNodeDTOInt 0)},{This is a LeafNodeDTO (This is a leafNodeDTOInt 0)}],This is a String list called l_1_node_list UNKNOWN_VALUE)}],This is a deeplyNestedListDTOfloat 0.0)}";
        LeafNodeDTO leafNodeDTO_11 = LeafNodeDTO.builder().build();
        LeafNodeDTO leafNodeDTO_12 = LeafNodeDTO.builder().build();
        List<LeafNodeDTO> leafNodeDTOList_1 = new ArrayList<>();
        leafNodeDTOList_1.add(leafNodeDTO_11);
        leafNodeDTOList_1.add(leafNodeDTO_12);
        L_1_Node l_1_node_1 = L_1_Node.builder().leafNodeDTOList(leafNodeDTOList_1).build();

        LeafNodeDTO leafNodeDTO_21 = LeafNodeDTO.builder().build();
        LeafNodeDTO leafNodeDTO_22 = LeafNodeDTO.builder().build();
        List<LeafNodeDTO> leafNodeDTOList_2 = new ArrayList<>();
        leafNodeDTOList_2.add(leafNodeDTO_21);
        leafNodeDTOList_2.add(leafNodeDTO_22);
        L_1_Node l_1_node_2 = L_1_Node.builder().leafNodeDTOList(leafNodeDTOList_2).build();

        List<L_1_Node> l_1_nodeList = new ArrayList<>();
        l_1_nodeList.add(l_1_node_1);
        l_1_nodeList.add(l_1_node_2);

        DeeplyNestedListDTO deeplyNestedListDTO = DeeplyNestedListDTO.builder().l_1_nodeList(l_1_nodeList).build();

        String generatedLabel = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(deeplyNestedListDTO);
        System.out.println(generatedLabel);

        Assert.assertTrue(expected.equalsIgnoreCase(generatedLabel));
    }

}
