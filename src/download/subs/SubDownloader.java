package download.subs;

import download.ParseDownloader;
import download.UrlReader;
import download.parser.ParseItem;
import download.parser.ParseResult;
import org.apache.commons.lang3.Range;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class SubDownloader<PR extends ParseResult> extends ParseDownloader<PR> {
    protected String toFile;

    protected Range<Integer> needEpisodes;

    public SubDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority);
        toFile = file;
    }

    protected SubDownloader() {
    }

    @Override
    public File download() {
        try {
            PR parseResult = parser.parse(UrlReader.readUrlToString(url));
            ParseItem parseItem = defineSubUrl(parseResult);
            if (parseItem == null) {
                return null;
            } else {
                String episodes = parseItem.getEpisodeRange().toString()
                        .replace("..", "-")
                        .replace("[", "")
                        .replace("]", "");
                String fileName = toFile + name + " " + episodes + "." + getFileExt(); // todo add owner
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
        return null; // todo
    }

    public String getToFile() {
        return toFile;
    }

    public void setToFile(String toFile) {
        this.toFile = toFile;
    }

    public Range<Integer> getNeedEpisodes() {
        return needEpisodes;
    }

    public void setNeedEpisodes(Range<Integer> needEpisodes) {
        this.needEpisodes = needEpisodes;
    }

    public void setNeedEpisode(int episode) {
        this.needEpisodes = Range.between(episode, episode);
    }

    protected String getFileExt() {
        return "";
    }
}
