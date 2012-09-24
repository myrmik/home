package download.parser.nyaa;

import download.parser.ParseItem;
import download.parser.ParseResult;

import java.util.ArrayList;

public class NyaaParseResult extends ParseResult {
    public NyaaParseResult(ArrayList<ParseItem> subItems) {
        super(subItems);
    }
}
