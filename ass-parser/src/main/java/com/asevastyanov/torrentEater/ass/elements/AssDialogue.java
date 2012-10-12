package com.asevastyanov.torrentEater.ass.elements;

import com.asevastyanov.torrentEater.ass.model.elements.AssDialogueVO;

public class AssDialogue extends AssComponent {

    public static final String NAME = "Dialogue";

    public AssDialogue() {
        super(NAME);
    }

    protected void setComponentVO() {
        setComponentVO(new AssDialogueVO());
    }

    protected void setDefautFormat() {
        if (format == null) {
            String sFormat = "Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text";
            format = new AssFormat();
            format.parse(sFormat);
        }
    }

    public AssDialogueVO getDialogue() {
        return (AssDialogueVO)getComponent();
    }
}
