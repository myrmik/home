package com.ass.model.elements;

import com.ass.elements.AssFormat;

import java.util.HashMap;

public interface AssComponentVO {
    public void setHMData(HashMap hmData);
    public String toString(AssFormat format);
}
