package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;

@GenAIInstance(label = "This is a NonLombokDTO2 ")
public class NonLombokDTO2 {

    @GenAILabel(label = "This is a non lombok string in base class with value: ")
    protected String str2;
    @GenAILabel(label = "This is a non lombok int in base class with value: ")
    protected Integer int2;

    public NonLombokDTO2() {}

    public NonLombokDTO2(String str2, Integer int2) {
        this.str2 = str2;
        this.int2 = int2;
    }

    public void SET_str2(String s) {
        this.str2 = s;
    }
    public String GET_str2() {
        return this.str2;
    }

    public void SET_int2(int i) {
        this.int2 = i;
    }
    public Integer GET_int2() {
        return this.int2;
    }
}
