package download.subs;

import download.parser.ParseItem;
import download.parser.nyaa.NyaaParseResult;
import download.parser.nyaa.NyaaParser;

import java.io.File;
import java.net.URL;
import java.util.List;

public class NyaaDownloader extends SubDownloader<NyaaParseResult> {

    private final String DEFAULT_PATTERN = "*[${owner}]${name}${episode}*[${format}]*";

    public NyaaDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority, file);
    }

    public NyaaDownloader() {
    }

    @Override
    public File download() {
        return download(DEFAULT_PATTERN);
    }

    @Override
    public ParseItem defineSubUrl(NyaaParseResult parseResult) {
        // todo filter by episode name
        return super.defineSubUrl(parseResult);
    }

    /**
     *
     * @param pattern e.g. - *[${owner}]*${name}*${episode}*[${format}]*
     * @return
     */
    public File download(String pattern) {
        ((NyaaParser)parser).setPattern(pattern);
        return super.download();
    }

    protected String getFileExt() {
        return "torrent";
    }
}
