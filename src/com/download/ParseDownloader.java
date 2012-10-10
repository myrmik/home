package com.download;

import com.download.parser.ParseResult;
import com.download.parser.Parser;

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
