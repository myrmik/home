package com.ass.elements;

import com.ass.AssElement;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class AssFormat extends AssElement {

    public static final String NAME = "Format";

    private ArrayList<String> data = new ArrayList<String>();

    public AssFormat() {
        super(NAME);
    }

    @Override
    public void parse(String data) {
        if (data == null) return;

        String[] vals = data.split(",");
        for (String val : vals) {
            this.data.add(val.trim());
        }
    }

    @Override
    public String toString() {
        return NAME + ": " + StringUtils.join(data, ", ");
    }

    public ArrayList<String> getData() {
        return data;
    }
}
