package io.github.ossdbh.genaiveclbl4j.test.dto;

import io.github.ossdbh.genaiveclbl4j.annotation.GenAIInstance;
import io.github.ossdbh.genaiveclbl4j.annotation.GenAILabel;

@GenAIInstance
public class NonLombokDTO3 extends NonLombokDTO2 {

    @GenAILabel(label = "This is a non lombok string in inherited class with value: ")
    private String str3;
    @GenAILabel(label = "This is a non lombok int in inherited class with value: ")
    @GenAILabel(label = "I also support multiple labels on the same field ")
    private Integer int3;

    public NonLombokDTO3() {
        super();
    }

    public NonLombokDTO3(String str2, Integer int1, String str3, Integer int3) {
        super(str2, int1);
        this.str3 = str3;
        this.int3 = int3;
    }

    public void SET_str3(String s) {
        this.str3 = s;
    }
    public String GET_str3() {
        return this.str3;
    }

    public void SET_int3(int i) {
        this.int3 = i;
    }
    public Integer GET_int3() {
        return this.int3;
    }
}
