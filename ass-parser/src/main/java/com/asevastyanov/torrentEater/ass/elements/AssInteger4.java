package com.asevastyanov.torrentEater.ass.elements;

public class AssInteger4 extends AssInteger {
    public static final String NAME = "Integer4";

    public AssInteger4() {
        super(NAME);
    }

    @Override
    public String toString() {
        String val = Integer.toString(getValue());
        String res = "0000" + val;
        return res.substring(val.length(), res.length());
    }
}
