package com.asevastyanov.torrentEater.ass.model.elements;

import com.asevastyanov.torrentEater.ass.elements.AssInteger;
import com.asevastyanov.torrentEater.ass.elements.AssInteger4;
import com.asevastyanov.torrentEater.ass.elements.AssString;
import com.asevastyanov.torrentEater.ass.elements.AssTime;

public class AssDialogueVO extends AssAbstractVO {
    AssInteger layer;
    AssTime start;
    AssTime end;
    AssString style;
    AssString name;
    AssInteger4 marginL;
    AssInteger4 marginR;
    AssInteger4 marginV;
    AssString effect;
    AssString text;

    public AssInteger getLayer() {
        return layer;
    }

    public void setLayer(AssInteger layer) {
        this.layer = layer;
    }

    public AssTime getStart() {
        return start;
    }

    public void setStart(AssTime start) {
        this.start = start;
    }

    public AssTime getEnd() {
        return end;
    }

    public void setEnd(AssTime end) {
        this.end = end;
    }

    public AssString getStyle() {
        return style;
    }

    public void setStyle(AssString style) {
        this.style = style;
    }

    public AssString getName() {
        return name;
    }

    public void setName(AssString name) {
        this.name = name;
    }

    public AssInteger4 getMarginL() {
        return marginL;
    }

    public void setMarginL(AssInteger4 marginL) {
        this.marginL = marginL;
    }

    public AssInteger4 getMarginR() {
        return marginR;
    }

    public void setMarginR(AssInteger4 marginR) {
        this.marginR = marginR;
    }

    public AssInteger4 getMarginV() {
        return marginV;
    }

    public void setMarginV(AssInteger4 marginV) {
        this.marginV = marginV;
    }

    public AssString getEffect() {
        return effect;
    }

    public void setEffect(AssString effect) {
        this.effect = effect;
    }

    public AssString getText() {
        return text;
    }

    public void setText(AssString text) {
        this.text = text;
    }
}
