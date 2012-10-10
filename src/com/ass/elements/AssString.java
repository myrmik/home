package com.ass.elements;

import com.ass.AssElement;

public class AssString extends AssElement {

    private String value;

    public static final String NAME = "String";

    public AssString() {
        super(NAME);
    }

    @Override
    public void parse(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
