package ass.model.elements;

import ass.elements.AssFormat;
import utils.ReflectUtils;

import java.util.HashMap;

public abstract class AssAbstractVO implements AssComponentVO {
    public void setHMData(HashMap hmData) {
        ReflectUtils.setHMData(this, hmData);
    }

    public String toString(AssFormat format) {
        HashMap elements = ReflectUtils.getHMData(this);
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for (String name : format.getData()) {
            if (!first) sb.append(',');
            if (first) first = false;
            sb.append(elements.get(name.toUpperCase()));
        }
        return sb.toString();
    }
}
