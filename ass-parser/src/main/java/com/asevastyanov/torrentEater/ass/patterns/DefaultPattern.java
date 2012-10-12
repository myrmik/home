package com.asevastyanov.torrentEater.ass.patterns;

import com.asevastyanov.torrentEater.ass.AssDefiner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultPattern implements AssPattern {
    private Pattern pattern = Pattern.compile(".*");
    protected Matcher matcher;
    protected AssDefiner definer;

    public DefaultPattern(AssDefiner definer) {
        this.definer = definer;
    }

    public boolean process(String data) {
        matcher = pattern.matcher(data);
        if (matcher.find()) {
            setDefinerData(data);
            return true;
        }
        return false;
    }

    protected void setDefinerData(String data) {
        setName(data);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    protected void setName(String name) {
        definer.setElementName(name);
    }

    protected void setData(String data) {
        definer.setData(data);
    }
}
