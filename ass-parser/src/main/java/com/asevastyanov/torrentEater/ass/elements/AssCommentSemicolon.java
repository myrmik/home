package com.asevastyanov.torrentEater.ass.elements;

import com.asevastyanov.torrentEater.ass.AssElement;

public class AssCommentSemicolon extends AssElement{
    private String value;

    public static final String NAME = "CommentSemicolon";

    public AssCommentSemicolon() {
        super(NAME);
    }

    @Override
    public void parse(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "; " + value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
