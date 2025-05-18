package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@GenAIInstance
public class InsideListDTO {
    @GenAILabel(label = "this is sampleString1 ")
    private String sampleString1;

    @GenAILabel(label = "this is sampleString2 ")
    private String sampleString2;
}
