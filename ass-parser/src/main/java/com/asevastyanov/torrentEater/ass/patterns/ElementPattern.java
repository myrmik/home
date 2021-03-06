package com.asevastyanov.torrentEater.ass.patterns;

import com.asevastyanov.torrentEater.ass.AssDefiner;
import java.util.regex.Pattern;

public class ElementPattern extends DefaultPattern {

    public ElementPattern(AssDefiner definer) {
        super(definer);
        setPattern(Pattern.compile("([^:]*):(.*)"));
    }

    @Override
    protected void setDefinerData(String data) {
        setName(matcher.group(1).trim());
        setData(matcher.group(2).trim());
    }
}