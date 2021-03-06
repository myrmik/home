package com.asevastyanov.torrentEater.ass;

import com.asevastyanov.torrentEater.ass.elements.AssDefault;
import com.asevastyanov.torrentEater.ass.exceptions.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssElementFactory {
    private static final Logger logger = LoggerFactory.getLogger(AssDocument.class);
    private static AssElementFactory factory;

    public AssElement createElement(String sElement) throws ParseException {
        if (sElement == null) {
            return null;
        }

        AssDefiner definer = AssDefiner.getDefiner();
        definer.parse(sElement);
        String name = definer.getElementName();
        String data = definer.getData();
        String className = AssElementContainer.getContainer().get(name);
        AssElement elem = null;
        try {
            elem = (AssElement) Class.forName("ass.elements." + className).newInstance();
        } catch (ClassNotFoundException e) {
            elem = new AssDefault(name);
        } catch (Exception e) {
            logger.debug(e.toString(), e);
            return null;
        }

        try {
            elem.parse(data);
        } catch (Exception e) {
            throw new ParseException(name, className, data, e);
        }
        return elem;
    }

    public static AssElementFactory getFactory() {
        if (factory == null) {
            factory = new AssElementFactory();
        }
        return factory;
    }
}
