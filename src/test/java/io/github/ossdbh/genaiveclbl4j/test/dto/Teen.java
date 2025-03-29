package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import lombok.Builder;
import lombok.Getter;

@Getter
@GenAIInstance
public class Teen extends Chld {
    @GenAILabel(label = "This is a String in a deep stacked inherited child class, level 2")
    private String address;
    @GenAILabel(label = "This is an int in a deep stacked inherited child class, level 2")
    private Integer fld3;
    @GenAILabel(label = "This is another String in a deep stacked inherited child class, level 2")
    private String fld4;
    @GenAILabel(label = "This is another int in a deep stacked inherited child class, level 2")
    private Integer fld5;

    @Builder(builderMethodName = "teenBuilder")
    public Teen(String parentName, int parentAge, String childName, String skipThis, int childAge, String address, Integer fld3, String fld4, Integer fld5) {
        super(parentName, parentAge, childName, skipThis, childAge);
        this.address = address;
        this.fld3 = fld3;
        this.fld4 = fld4;
        this.fld5 = fld5;
    }
}
