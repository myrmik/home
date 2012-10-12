package com.asevastyanov.torrentEater.ass;

import com.asevastyanov.torrentEater.ass.patterns.*;

import java.util.ArrayList;
import java.util.List;

public class AssDefiner extends AssElement {

    private static AssDefiner definer;

    private String data;

    private List<AssPattern> patterns;

    private AssDefiner() {
        super("");
        init();
    }

    private void init() {
        patterns = new ArrayList<AssPattern>();
        patterns.add(new CommentPattern(this));
        patterns.add(new ContainerPattern(this));
        patterns.add(new ElementPattern(this));
        patterns.add(new DefaultPattern(this));
    }

    @Override
    public void parse(String data) {
        this.data = null;
        for (AssPattern pattern : patterns) {
            if (pattern.process(data)) {
                return;
            }
        }
    }

    public static AssDefiner getDefiner() {
        if (definer == null) {
            definer = new AssDefiner();
        }
        return definer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

