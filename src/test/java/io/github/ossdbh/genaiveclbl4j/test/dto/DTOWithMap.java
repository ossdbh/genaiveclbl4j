package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a DTO with a Map ")
public class DTOWithMap {
    @GenAILabel(label = "This is String data denoting a name ")
    @GenAILabel(label = "Name is an important piece of information ")
    private String data1;

    @GenAILabel(label = "This is Map of String:String ")
    private Map<String, String> stringMap;

    @GenAILabel(label = "This is int data ")
    private int data2;
}
