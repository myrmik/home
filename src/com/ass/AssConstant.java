package com.ass;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Properties;

public class AssConstant {
    static {
        fill();
    }

    private static Properties properties;

    public static final String PARSER_BINDING_FILENAME = getProperty("parser.binding.filename");

    public static DecimalFormat DECIMAL_FORMAT = new DecimalFormat();
    static {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DECIMAL_FORMAT.setDecimalFormatSymbols(dfs);
    }

    public static void fill() {
        properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("config\\parser.config");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private static String getProperty(String name) {
        return properties.getProperty(name);
    }

    private static String getProperty(String name, String def) {
        return properties.getProperty(name, def);
    }
}
