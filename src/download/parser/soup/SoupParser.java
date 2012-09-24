package download.parser.soup;

import download.parser.ParseResult;
import download.parser.Parser;
import org.jsoup.Jsoup;

import java.io.IOException;

public class SoupParser extends Parser<JsoupParseResult> {
    @Override
    public JsoupParseResult parse(String input) throws Exception {
        return new JsoupParseResult(Jsoup.parse(input));
    }
}
