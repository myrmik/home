package ass.model.elements;

import ass.elements.*;

public class AssStyleVO extends AssAbstractVO {
    AssString name;
    AssString fontName;
    AssInteger fontSize;
    AssColor primaryColour;
    AssColor secondaryColour;
    AssColor outlineColour;
    AssColor backColour;
    AssBoolean bold;
    AssBoolean italic;
    AssBoolean underline;
    AssBoolean strikeOut;
    AssDouble scaleX;
    AssDouble scaleY;
    AssDouble spacing;
    AssDouble angle;
    AssBorderStyle borderStyle;
    AssDouble outline;
    AssDouble shadow;
    AssAlignment alignment;
    AssInteger marginL;
    AssInteger marginR;
    AssInteger marginV;
    AssInteger encoding;

    public AssStyleVO() {
    }

    public AssString getName() {
        return name;
    }

    public void setName(AssString name) {
        this.name = name;
    }

    public AssString getFontName() {
        return fontName;
    }

    public void setFontName(AssString fontName) {
        this.fontName = fontName;
    }

    public AssInteger getFontSize() {
        return fontSize;
    }

    public void setFontSize(AssInteger fontSize) {
        this.fontSize = fontSize;
    }

    public AssColor getPrimaryColour() {
        return primaryColour;
    }

    public void setPrimaryColour(AssColor primaryColour) {
        this.primaryColour = primaryColour;
    }

    public AssColor getSecondaryColour() {
        return secondaryColour;
    }

    public void setSecondaryColour(AssColor secondaryColour) {
        this.secondaryColour = secondaryColour;
    }

    public AssColor getOutlineColour() {
        return outlineColour;
    }

    public void setOutlineColour(AssColor outlineColour) {
        this.outlineColour = outlineColour;
    }

    public AssColor getBackColour() {
        return backColour;
    }

    public void setBackColour(AssColor backColour) {
        this.backColour = backColour;
    }

    public AssBoolean getBold() {
        return bold;
    }

    public void setBold(AssBoolean bold) {
        this.bold = bold;
    }

    public AssBoolean getItalic() {
        return italic;
    }

    public void setItalic(AssBoolean italic) {
        this.italic = italic;
    }

    public AssBoolean getUnderline() {
        return underline;
    }

    public void setUnderline(AssBoolean underline) {
        this.underline = underline;
    }

    public AssBoolean getStrikeOut() {
        return strikeOut;
    }

    public void setStrikeOut(AssBoolean strikeOut) {
        this.strikeOut = strikeOut;
    }

    public AssDouble getScaleX() {
        return scaleX;
    }

    public void setScaleX(AssDouble scaleX) {
        this.scaleX = scaleX;
    }

    public AssDouble getScaleY() {
        return scaleY;
    }

    public void setScaleY(AssDouble scaleY) {
        this.scaleY = scaleY;
    }

    public AssDouble getSpacing() {
        return spacing;
    }

    public void setSpacing(AssDouble spacing) {
        this.spacing = spacing;
    }

    public AssDouble getAngle() {
        return angle;
    }

    public void setAngle(AssDouble angle) {
        this.angle = angle;
    }

    public AssBorderStyle getBorderStyle() {
        return borderStyle;
    }

    public void setBorderStyle(AssBorderStyle borderStyle) {
        this.borderStyle = borderStyle;
    }

    public AssDouble getOutline() {
        return outline;
    }

    public void setOutline(AssDouble outline) {
        this.outline = outline;
    }

    public AssDouble getShadow() {
        return shadow;
    }

    public void setShadow(AssDouble shadow) {
        this.shadow = shadow;
    }

    public AssAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(AssAlignment alignment) {
        this.alignment = alignment;
    }

    public AssInteger getMarginL() {
        return marginL;
    }

    public void setMarginL(AssInteger marginL) {
        this.marginL = marginL;
    }

    public AssInteger getMarginR() {
        return marginR;
    }

    public void setMarginR(AssInteger marginR) {
        this.marginR = marginR;
    }

    public AssInteger getMarginV() {
        return marginV;
    }

    public void setMarginV(AssInteger marginV) {
        this.marginV = marginV;
    }

    public AssInteger getEncoding() {
        return encoding;
    }

    public void setEncoding(AssInteger encoding) {
        this.encoding = encoding;
    }
}
