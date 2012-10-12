package com.asevastyanov.torrentEater.download.parser.kage;

import com.asevastyanov.torrentEater.download.parser.ParseItem;
import com.asevastyanov.torrentEater.download.parser.ParseResult;

import java.util.ArrayList;

public class KageParseResult extends ParseResult {

    public KageParseResult(ArrayList<ParseItem> subItems) {
        super(subItems);
    }
}
