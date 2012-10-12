package com.asevastyanov.torrentEater.download.parser;

import java.util.List;

public abstract class ParseResult {
    private List<ParseItem> subItems;

    public ParseResult() {
    }

    public ParseResult(List<ParseItem> subItems) {
        this.subItems = subItems;
    }

    public List<ParseItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<ParseItem> subItems) {
        this.subItems = subItems;
    }
}
