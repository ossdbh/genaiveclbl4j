package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@GenAIInstance(label = "This is a flat DTO ")
public class DTOWithListOfInheritedComplexObject {
    @GenAILabel(label = "This is String data denoting a name ")
    @GenAILabel(label = "Name is an important piece of information ")
    private String data1;

    @GenAILabel(label = "this is a List of Chlds ")
    List<Chld> chldList;

    @GenAILabel(label = "This is biginteger data denoting heartbeat count till date ")
    private BigInteger data2;
}
