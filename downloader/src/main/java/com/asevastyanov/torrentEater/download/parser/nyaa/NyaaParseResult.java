package com.asevastyanov.torrentEater.download.parser.nyaa;

import com.asevastyanov.torrentEater.download.parser.ParseItem;
import com.asevastyanov.torrentEater.download.parser.ParseResult;

import java.util.ArrayList;

public class NyaaParseResult extends ParseResult {
    public NyaaParseResult(ArrayList<ParseItem> subItems) {
        super(subItems);
    }
}
