package download.subs;

import download.parser.nyaa.NyaaParseResult;
import download.parser.nyaa.NyaaParser;

import java.io.File;
import java.net.URL;
import java.util.List;

public class NyaaDownloader extends SubDownloader<NyaaParseResult> {
    public NyaaDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority, file);
    }

    public NyaaDownloader() {
    }

    @Override
    public File download() {
        ((NyaaParser)parser).setEpisodeName(name);
        return super.download();
    }

    protected String getFileExt() {
        return "torrent";
    }
}
