package ass.model.elements;

import ass.elements.AssFormat;

import java.util.HashMap;

public interface AssComponentVO {
    public void setHMData(HashMap hmData);
    public String toString(AssFormat format);
}
