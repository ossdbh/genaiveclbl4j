package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;

@GenAIInstance
public class NestedNonLombokDTO {

    @GenAILabel(label = "This is a nonlombok nested string attribute ")
    private String str_nested;
    @GenAILabel(label = "This is a nonlombok nested string attribute")
    private Integer int_nested;

    public void SET_str_nested(String s) {
        this.str_nested = s;
    }
    public String GET_str_nested() {
        return this.str_nested;
    }

    public void SET_int_nested(int i) {
        this.int_nested = i;
    }
    public Integer GET_int_nested() {
        return this.int_nested;
    }
}
