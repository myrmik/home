package download.subs;

import download.ParseDownloader;
import download.UrlReader;
import download.parser.ParseItem;
import download.parser.ParseResult;
import utils.IntRange;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class EpisodeDownloader<PR extends ParseResult> extends ParseDownloader<PR> {
    protected String toFile;

    protected IntRange needEpisodes;

    public EpisodeDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority);
        toFile = file;
    }

    protected EpisodeDownloader() {
    }

    @Override
    public File download() throws MalformedURLException, UnsupportedEncodingException {
        try {
            PR parseResult = parser.parse(UrlReader.readUrlToString(url));
            ParseItem parseItem = defineSubUrl(parseResult);
            if (parseItem == null) {
                return null;
            } else {
                String episodes = parseItem.getEpisodeRange().toString();
                String owner = parseItem.getOwner() != null ? "[" + parseItem.getOwner() + "] " : "";
                String videoFormat = (parseItem.getFormat() != null ? " [" + parseItem.getFormat() + "]" : "");
                String fileName = toFile
                        + owner
                        + name + " "
                        + episodes
                        + videoFormat
                        + "." + getFileExt();
                return UrlReader.readUrlToFile(parseItem.getUrl(), new File(fileName));
            }
        } catch (Exception e) {
            logger.debug(e.toString(), e);
            return null;
        }
    }

    public ParseItem defineSubUrl(PR parseResult) {
        Collections.sort(parseResult.getSubItems(), new Comparator<ParseItem>() {
            public int compare(ParseItem o1, ParseItem o2) {
                int prior1 = o1.getOwner() != null ? ownersPriority.indexOf(o1.getOwner()) : -1;
                int prior2 = o2.getOwner() != null ? ownersPriority.indexOf(o2.getOwner()) : -1;
                return (prior1 >= 0 ? prior1 : Integer.MAX_VALUE) - (prior2 >= 0 ? prior2 : Integer.MAX_VALUE);
            }
        });

        for (ParseItem parseItem : parseResult.getSubItems()) {
            if (parseItem.isContainEpisodes(needEpisodes)) {
                return parseItem;
            }
        }
        return null;
    }

    public String getToFile() {
        return toFile;
    }

    public void setToFile(String toFile) {
        this.toFile = toFile;
    }

    public IntRange getNeedEpisodes() {
        return needEpisodes;
    }

    public void setNeedEpisodes(IntRange needEpisodes) {
        this.needEpisodes = needEpisodes;
    }

    public void setNeedEpisode(int episode) {
        this.needEpisodes = IntRange.between(episode, episode);
    }

    protected String getFileExt() {
        return "";
    }
}
