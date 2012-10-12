package com.asevastyanov.torrentEater.download.parser.soup;

import com.asevastyanov.torrentEater.download.parser.Parser;
import org.jsoup.Jsoup;

public class SoupParser extends Parser<JsoupParseResult> {
    @Override
    public JsoupParseResult parse(String input) throws Exception {
        return new JsoupParseResult(Jsoup.parse(input));
    }
}
