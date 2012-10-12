package com.asevastyanov.torrentEater.download;

import com.asevastyanov.torrentEater.download.parser.ParseResult;
import com.asevastyanov.torrentEater.download.parser.Parser;

import java.net.URL;
import java.util.List;

public abstract class ParseDownloader<T extends ParseResult> extends Downloader {
    protected Parser<T> parser;

    public ParseDownloader(String name, URL url, List<String> ownersPriority) {
        super(name, url, ownersPriority);
    }

    protected ParseDownloader() {
    }

    public Parser<T> getParser() {
        return parser;
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }
}
