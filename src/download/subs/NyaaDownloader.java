package download.subs;

import download.parser.ParseItem;
import download.parser.nyaa.NyaaParseResult;
import download.parser.nyaa.NyaaParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NyaaDownloader extends EpisodeDownloader<NyaaParseResult> {

    public String NYAA_URL_PREFIX = "http://www.nyaa.eu/?page=search&cats=0_0&filter=0&term=";

    private final String DEFAULT_PATTERN = "*[${owner}]${name}${episode}*[${format}]*";

    List<String> formatPriority;

    public NyaaDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority, file);
    }

    public NyaaDownloader() {
    }

    @Override
    public File download() throws MalformedURLException, UnsupportedEncodingException {
        return download(DEFAULT_PATTERN);
    }

    @Override
    public ParseItem defineSubUrl(NyaaParseResult parseResult) {
        Collections.sort(parseResult.getSubItems(), new Comparator<ParseItem>() {
            public int compare(ParseItem o1, ParseItem o2) {
                int res =  comparePriority(ownersPriority, o1.getOwner(), o2.getOwner());
                if (res == 0) {
                    res = comparePriority(formatPriority, o1.getFormat(), o2.getFormat());
                }
                return res;
            }
        });

        for (ParseItem parseItem : parseResult.getSubItems()) {
            if (parseItem.isContainEpisodes(needEpisodes)) {
                return parseItem;
            }
        }
        return null;
    }

    private int comparePriority(List priority, Object o1, Object o2) {
        int prior1 = o1 != null ? priority.indexOf(o1) : -1;
        int prior2 = o2 != null ? priority.indexOf(o2) : -1;
        return (prior1 >= 0 ? prior1 : Integer.MAX_VALUE) - (prior2 >= 0 ? prior2 : Integer.MAX_VALUE);
    }

    /**
     *
     * @param pattern e.g. - *[${owner}]*${name}*${episode}*[${format}]*
     * @return
     */
    public File download(String pattern) throws MalformedURLException, UnsupportedEncodingException {
        String encEpisodeName = URLEncoder.encode(name + " " + needEpisodes, "UTF-8");
        this.url = new URL(NYAA_URL_PREFIX + encEpisodeName);

        ((NyaaParser)parser).setPattern(pattern);

        return super.download();
    }

    protected String getFileExt() {
        return "torrent";
    }

    public List<String> getFormatPriority() {
        return formatPriority;
    }

    public void setFormatPriority(List<String> formatPriority) {
        this.formatPriority = formatPriority;
    }
}
