package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAINestedInstance;

@GenAIInstance(label = "This is a NonLombokDTO ")
public class NonLombokDTO {

    @GenAILabel(label = "This is a string ")
    private String str1;

    @GenAINestedInstance
    private NestedNonLombokDTO nestedNonLombokDTO;

    @GenAILabel(label = "This is a string ")
    private Integer int1;

    public void SET_str1(String s) {
        this.str1 = s;
    }
    public String GET_str1() {
        return this.str1;
    }

    public void SET_int1(int i) {
        this.int1 = i;
    }
    public Integer GET_int1() {
        return this.int1;
    }

    public void SET_nestedNonLombokDTO(NestedNonLombokDTO nestedNonLombokDTO) {
        this.nestedNonLombokDTO = nestedNonLombokDTO;
    }
    public NestedNonLombokDTO GET_nestedNonLombokDTO() {
        return this.nestedNonLombokDTO;
    }
}
