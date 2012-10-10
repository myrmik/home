package com.ass.elements;

import com.ass.AssConstant;
import com.ass.AssElement;


public class AssDouble extends AssElement {

    private double value;

    public static final String NAME = "Double";

    public AssDouble() {
        super(NAME);
    }

    @Override
    public void parse(String value) {
        if (value == null) return;
        this.value = Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return AssConstant.DECIMAL_FORMAT.format(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
