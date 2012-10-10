package com.ass.elements;

import com.ass.AssElement;

public class AssContainer extends AssElement {

    private String value;

    public static final String NAME = "Container";

    public AssContainer() {
        super(NAME);
    }

    @Override
    public void parse(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return '[' + value + ']';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
