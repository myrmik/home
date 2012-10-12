package com.asevastyanov.torrentEater.ass;

import com.asevastyanov.torrentEater.ass.exceptions.ParseException;

public abstract class AssElement {
    private String elementName;

    public AssElement(String elementName) {
        this.elementName = elementName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public abstract void parse(String data) throws ParseException;
}
