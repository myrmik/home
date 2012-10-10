package com.ass.elements;


import com.ass.model.elements.AssStyleVO;

public class AssStyle extends AssComponent {

    public static final String NAME = "Style";

    public AssStyle() {
        super(NAME);
    }

    protected void setComponentVO() {
        setComponentVO(new AssStyleVO());
    }

    protected void setDefautFormat() {
        if (format == null) {
            String sFormat = "Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, " +
                    "Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, " +
                    "Alignment, MarginL, MarginR, MarginV, Encoding";
            format = new AssFormat();
            format.parse(sFormat);
        }
    }

    public AssStyleVO getStyle() {
        return (AssStyleVO)getComponent();
    }
}
