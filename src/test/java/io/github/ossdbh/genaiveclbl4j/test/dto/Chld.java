package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@GenAIInstance
public class Chld extends Prnt {
    @GenAILabel(label = "This is a String in a deep stacked inherited child class, level 1 ")
    private String childName;

    @GenAILabel(label = "This is another String in a deep stacked inherited child class, level 1 ")
    private String skipThis = "";

    @GenAILabel(label = "This is an int in a deep stacked inherited child class, level 1 ")
    private int childAge;

    @Builder(builderMethodName = "chldBuilder")
    public Chld(String parentName, int parentAge, String childName, String skipThis, int childAge) {
        super(parentName, parentAge);
        this.childName = childName;
        this.skipThis = skipThis;
        this.childAge = childAge;
    }
}
