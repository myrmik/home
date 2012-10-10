package com.ass.elements;

import com.ass.AssElement;
import com.ass.AssElementFactory;
import com.ass.exceptions.ParseException;
import com.ass.model.elements.AssComponentVO;

import java.util.HashMap;

public abstract class AssComponent extends AssElement {

    private AssComponentVO componentVO;

    protected AssFormat format;

    public AssComponent(String elementName) {
        super(elementName);
        setComponentVO();
        setDefautFormat();
    }

    @Override
    public void parse(String data) throws ParseException {
        if (data == null) return;

        AssElementFactory factory = AssElementFactory.getFactory();
        String[] vals = data.split(",", format.getData().size());
        int count = 0;
        HashMap<String, AssElement> hashMap = new HashMap<String, AssElement>();
        for (String name : format.getData()) {
            AssElement element = null;
            element = factory.createElement(this.getElementName() + "." + name);
            String s = vals[count++];
            try {
                element.parse(s);
            } catch (Exception e) {
                throw new ParseException(name, element.getClass().getSimpleName(), s, e);
            }
            hashMap.put(name.toUpperCase(), element);
        }
        componentVO.setHMData(hashMap);
    }

    public AssFormat getFormat() {
        return format;
    }

    public void setFormat(AssFormat format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return getElementName() + ": " + componentVO.toString(format);
    }

    protected void setComponentVO(AssComponentVO componentVO) {
        this.componentVO = componentVO;
    }

    public AssComponentVO getComponent() {
        return componentVO;
    }

    abstract void setComponentVO();
    abstract void setDefautFormat();
}
