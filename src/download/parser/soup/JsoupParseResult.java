package download.parser.soup;

import download.parser.ParseResult;
import org.jsoup.nodes.Document;

public class JsoupParseResult extends ParseResult {
    protected Document document;

    public JsoupParseResult(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
