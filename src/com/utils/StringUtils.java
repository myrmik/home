package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String getFileNameFromPath(String path) {
        return getRegExpMatch("\\\\([^\\\\]*)$", path);
    }

    public static String getFileNameFromPathShort(String path) {
        return getRegExpMatch("\\\\([^\\\\]*)\\.[^\\.]*$", path);
    }

    public static String getRootDir(String path) {
        String result = getRegExpMatch("^([^\\\\]*)\\\\", path);
        if (path.equals(result)) {
            return null;
        } else {
            return result;
        }
    }

    public static String getRegExpMatch(String regExp, String data) {
        Matcher matcher = Pattern.compile(regExp).matcher(data);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return data.trim();
        }
    }

    public static boolean fuzzyEquals(String s1, String s2, int numOfFuzzyChars) {
        for (int i = s1.length(); i >= numOfFuzzyChars; i--) {
            for (int j = 0; j <= s1.length() - i; j++) {
                CharSequence search = s1.subSequence(j, j + i);
                if (s2.contains(search)) {
                    return true;
                }
            }
        }

        return false;
    }
}
