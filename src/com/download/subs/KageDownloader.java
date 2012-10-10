package com.download.subs;

import com.download.parser.ParseItem;
import com.download.parser.kage.KageParseResult;

import java.net.URL;
import java.util.List;

public class KageDownloader extends EpisodeDownloader<KageParseResult> {
    public KageDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority, file);
    }

    public KageDownloader() {
    }

    protected String getFileExt() {
        return "rar";
    }

    @Override
    public ParseItem defineSubUrl(KageParseResult parseResult) {
       return super.defineSubUrl(parseResult);
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
