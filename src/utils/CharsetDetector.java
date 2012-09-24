package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CharsetDetector {
    public static String detectCyr(String filePath) throws IOException {
        final int LOWERCASE = 3;
        final int UPPERCASE = 1;

        HashMap<String, Integer> charsets = new HashMap<String, Integer>();
        charsets.put("koi8-r", 0);
        charsets.put("windows-1251", 0);
        charsets.put("cp866", 0);
        charsets.put("utf-8", 0);

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));

        int ch;
        while ((ch = in.read()) != -1) {

            //non-russian characters
            if (ch < 128 || ch > 256) continue;

            //CP866
            if ((ch > 159 && ch < 176) || (ch > 223 && ch < 242))
                charsets.put("cp866", charsets.get("cp866") + LOWERCASE);
            if ((ch > 127 && ch < 160)) charsets.put("cp866", charsets.get("cp866") + UPPERCASE);
            //KOI8-R
            if ((ch > 191 && ch < 223)) charsets.put("koi8-r", charsets.get("koi8-r") + LOWERCASE);
            if ((ch > 222 && ch < 256)) charsets.put("koi8-r", charsets.get("koi8-r") + UPPERCASE);
            //WIN-1251
            if (ch > 223 && ch < 256) charsets.put("windows-1251", charsets.get("windows-1251") + LOWERCASE);
            if (ch > 191 && ch < 224) charsets.put("windows-1251", charsets.get("windows-1251") + UPPERCASE);
            //ISO-8859-5
            if (ch > 207 && ch < 240) charsets.put("utf-8", charsets.get("utf-8") + LOWERCASE);
            if (ch > 175 && ch < 208) charsets.put("utf-8", charsets.get("utf-8") + UPPERCASE);
        }

        return getMaxCharset(charsets, "utf-8");
    }

    private static String getMaxCharset(HashMap<String, Integer> map, String def) {
        String charset = def;
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                charset = entry.getKey();
            }
        }
        return charset;
    }

}
