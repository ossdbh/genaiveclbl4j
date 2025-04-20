package io.github.ossdbh.genaiveclbl4j.dto;

import io.github.ossdbh.genaiveclbl4j.constants.Constants;
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
public class GenAILabelDTO {
    @Builder.Default
    private String label = "";

    @Builder.Default
    private String method = "";

    @Builder.Default
    private Object data = null;

    private Class attributeClass;

    @Builder.Default
    private String labelAndDataConcatenator = Constants.LABEL_AND_DATA_CONCATENATOR;
}
