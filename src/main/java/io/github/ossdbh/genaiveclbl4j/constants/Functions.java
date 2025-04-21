package io.github.ossdbh.genaiveclbl4j.constants;

import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelDTO;
import io.github.ossdbh.genaiveclbl4j.dto.GenAILabelMetadataHelperDTO;

import java.util.List;
import java.util.function.BiFunction;

public interface Functions {

    BiFunction<List<GenAILabelDTO>, GenAILabelMetadataHelperDTO, List<GenAILabelDTO>> addGenAILabelMetadataMarker = (list, genAILabelMetadataHelperDTO) -> {
        list.add(
                GenAILabelDTO.builder()
                        .label(null)
                        .method(null)
                        .data(genAILabelMetadataHelperDTO.getLabelMetadata())
                        .attributeClass(null)
                        .labelAndDataConcatenator(genAILabelMetadataHelperDTO.getLabelAndDataConcatenator())
                        .build());
        return list;
    };

}
