package com.asevastyanov.torrentEater.ass.elements;

import com.asevastyanov.torrentEater.ass.AssElement;

import java.awt.*;

public class AssColor extends AssElement {
    private Color color;

    public static final String NAME = "Color";

    public AssColor() {
        super(NAME);
    }

    @Override
    public void parse(String AABBGGRR) {
        if (AABBGGRR == null) return;

        int a = 0, b = 0, g = 0, r = 0;
        try {
            a = Integer.parseInt(AABBGGRR.substring(2, 4), 16);
            b = Integer.parseInt(AABBGGRR.substring(4, 6), 16);
            g = Integer.parseInt(AABBGGRR.substring(6, 8), 16);
            r = Integer.parseInt(AABBGGRR.substring(8, 10), 16);
        } catch (Exception e) {}

        color = new Color(r, g, b, a);
    }

    @Override
    public String toString() {
        return "&H"
                + (color.getAlpha() < 16 ? '0' : "")
                + Integer.toHexString(color.getAlpha()).toUpperCase()
                + (color.getBlue() < 16 ? '0' : "")
                + Integer.toHexString(color.getBlue()).toUpperCase()
                + (color.getGreen() < 16 ? '0' : "")
                + Integer.toHexString(color.getGreen()).toUpperCase()
                + (color.getRed() < 16 ? '0' : "")
                + Integer.toHexString(color.getRed()).toUpperCase();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
