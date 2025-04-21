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
public class GenAILabelMetadataHelperDTO {
    private String labelMetadata;
    private String labelAndDataConcatenator;
}
