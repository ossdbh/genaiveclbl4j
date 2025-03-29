package io.github.ossdbh.genaiveclbl4j.dto;

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
}
