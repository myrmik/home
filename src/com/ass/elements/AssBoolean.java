package com.ass.elements;

import com.ass.AssElement;

public class AssBoolean extends AssElement {

    private boolean value;

    public static final String NAME = "Boolean";

    public AssBoolean() {
        super(NAME);
    }

    @Override
    public void parse(String value) {
        if (value == null) return;
        this.value = (Integer.parseInt(value) != 0);
    }

    @Override
    public String toString() {
        return value ? "-1" : "0";
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
