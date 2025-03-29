package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Prnt {
    @GenAILabel(label = "This is a String in a parent class ")
    private String parentName = "";

    @GenAILabel(label = "This is an int in a parent class ")
    private int parentAge = 0;
}
