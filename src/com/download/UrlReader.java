package com.download;

import java.io.*;
import java.net.URL;

public class UrlReader {
    public static String readUrlToString(URL url) throws IOException {
        if (url == null) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "windows-1251"));
        String inputLine;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        return sb.toString();
    }
    
    public static File readUrlToFile(URL url, File file) throws IOException {
        if (url == null) {
            return null;
        }

        BufferedInputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
        byte[] data = new byte[1024];
        int x;
        while((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();

        return file;
    }
}
