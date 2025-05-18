package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@GenAIInstance(label = "This is a DTO that has a List of InsideListDTOs ")
public class ListElementSkipDTO {
    private List<InsideListDTO> insideListDTOList;
}
