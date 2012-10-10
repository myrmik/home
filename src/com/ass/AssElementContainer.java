package com.ass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AssElementContainer {
    private static AssElementContainer container;
    private Properties properties;

    private AssElementContainer() {
    }

    public String get(String name) {
        return properties.getProperty(name);
    }

    public static AssElementContainer getContainer() {
        if (container == null) {
            container = new AssElementContainer();
        }

        return container;
    }

    public void fill() throws IOException {
        properties = new Properties();
        FileInputStream in = new FileInputStream(AssConstant.PARSER_BINDING_FILENAME);
        properties.load(in);
        in.close();
    }
}
