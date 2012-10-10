package com.ass.elements;

import com.ass.AssElement;

public class AssDefault extends AssElement {
    private String value;

    public AssDefault(String name) {
        super(name);
    }

    @Override
    public void parse(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getElementName() + (value == null ? "" : ": " + value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
