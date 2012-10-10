package com.download.parser.soup;

import com.download.parser.Parser;
import org.jsoup.Jsoup;

public class SoupParser extends Parser<JsoupParseResult> {
    @Override
    public JsoupParseResult parse(String input) throws Exception {
        return new JsoupParseResult(Jsoup.parse(input));
    }
}
