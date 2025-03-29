package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;

@GenAIInstance
public class NonLombokDTO1 {

    @GenAILabel(label = "This is a non lombok string ")
    private String str1;
    @GenAILabel(label = "This is a non lombok string ")
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
}
