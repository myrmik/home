package download.subs;

import download.parser.ParseItem;
import download.parser.kage.KageParseResult;

import java.net.URL;
import java.util.List;

public class KageDownloader extends SubDownloader<KageParseResult> {
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
}
