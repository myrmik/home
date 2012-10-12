package com.asevastyanov.torrentEater.ass;

import com.asevastyanov.torrentEater.ass.elements.AssDefault;
import com.asevastyanov.torrentEater.ass.elements.AssDialogue;
import com.asevastyanov.torrentEater.ass.elements.AssInteger;
import com.asevastyanov.torrentEater.ass.elements.AssStyle;
import com.asevastyanov.torrentEater.ass.exceptions.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.asevastyanov.torrentEater.utils.CharsetDetector;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssDocument {
    private static final Logger logger = LoggerFactory.getLogger(AssDocument.class);
    private String path;
    private ArrayList<AssElement> elems = new ArrayList<AssElement>();

    public AssDocument(String path) {
        this.path = path;
    }

    public void parse() throws Exception {
        BufferedReader in;
        AssElementFactory factory = AssElementFactory.getFactory();
        try {
            String charset = CharsetDetector.detectCyr(path);
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
            while (in.ready()) {
                String s = in.readLine();
                AssElement element = factory.createElement(s.trim());
                elems.add(element);
            }
            in.close();
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            logger.debug(e.toString(), e);
            throw e;
        }
    }

    public void save(String path) {
        BufferedWriter out;
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();

            setStylesEncoding(204);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "windows-1251"));
            for (AssElement elem : elems) {
                out.write(elem.toString());
                out.newLine();
            }
            out.close();
        } catch (Exception e) {
            logger.debug(e.toString(), e);
        }
    }

    public void changeMainStyle(String style) throws ParseException {
        AssElementFactory factory = AssElementFactory.getFactory();
        AssStyle newStyle = (AssStyle)factory.createElement(style);
        AssStyle mainStyle = findMainStyle();
        AssDefault playResX = (AssDefault)getElementByName("PlayResX");

        int newFontSize = (int)(Integer.parseInt(playResX.getValue()) * 0.06);
        newStyle.getStyle().setFontSize(new AssInteger(newFontSize));
        newStyle.getStyle().setName(mainStyle.getStyle().getName());

        setStyle(mainStyle.getStyle().getName().toString(), newStyle);
    }

    private void setStyle(String name, AssStyle style) {
        List<AssElement> styles = elems;
        for (int i = 0; i < styles.size(); i++) {
            AssElement element = styles.get(i);
            if (element instanceof AssStyle) {
                if (((AssStyle)element).getStyle().getName().toString().equals(name)) {
                    styles.set(i, style);
                    return;
                }
            }
        }
    }

    private AssStyle findMainStyle() {
        List<AssElement> dialogue = selectElems(AssDialogue.class);
        HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
        for (AssElement element : dialogue) {
            String key = ((AssDialogue)element).getDialogue().getStyle().toString();
            freqMap.put(key, freqMap.get(key) == null ? 1 : freqMap.get(key) + 1);
        }

        Integer max = 0;
        String style = "";
        for (String key : freqMap.keySet()) {
            if (max < freqMap.get(key)) {
                max = freqMap.get(key);
                style = key;
            }
        }

        return findStyle(style);
    }

    private List<AssElement> selectElems(Class clazz) {
        List<AssElement> res = new ArrayList<AssElement>();
        for (AssElement elem : elems) {
            if (elem.getClass() == clazz) {
                res.add(elem);
            }
        }
        return res;
    }

    private AssStyle findStyle(String style) {
        List<AssElement> styles = selectElems(AssStyle.class);
        for (AssElement element : styles) {
            if (((AssStyle)element).getStyle().getName().toString().equals(style)) {
                return (AssStyle)element;
            }
        }
        return null;
    }

    private void setStylesEncoding(int encoding) {
        for (AssElement elem : elems) {
            if (elem instanceof AssStyle) {
                ((AssStyle)elem).getStyle().setEncoding(new AssInteger(encoding));
            }
        }
    }

    private AssElement getElementByName(String name) {
        for (AssElement elem : elems) {
            if (elem.getElementName().equals(name)) {
                return elem;
            }
        }
        return null;
    }
}
