package com.download.parser.nyaa;

import com.download.parser.ParseItem;
import com.download.parser.ParseResult;

import java.util.ArrayList;

public class NyaaParseResult extends ParseResult {
    public NyaaParseResult(ArrayList<ParseItem> subItems) {
        super(subItems);
    }
}
