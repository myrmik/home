package com.asevastyanov.torrentEater.download.subs;

import com.asevastyanov.torrentEater.download.ParseDownloader;
import com.asevastyanov.torrentEater.download.UrlReader;
import com.asevastyanov.torrentEater.download.parser.ParseItem;
import com.asevastyanov.torrentEater.download.parser.ParseResult;
import com.asevastyanov.torrentEater.utils.IntRange;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class EpisodeDownloader<PR extends ParseResult> extends ParseDownloader<PR> {
    protected String toFile;

    protected IntRange needEpisodes;

    protected boolean success;

    public EpisodeDownloader(String name, URL url, List<String> ownersPriority, String file) {
        super(name, url, ownersPriority);
        toFile = file;
    }

    protected EpisodeDownloader() {
    }

    @Override
    public File download() {
        File result = null;
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
                result = UrlReader.readUrlToFile(parseItem.getUrl(), new File(fileName));
            }
        } catch (Exception e) {
            logger.debug(e.toString(), e);
            return null;
        }

        success = (result != null);
        return result;
    }

    public ParseItem defineSubUrl(PR parseResult) {
        Collections.sort(parseResult.getSubItems(), new Comparator<ParseItem>() {
            public int compare(ParseItem o1, ParseItem o2) {
                return comparePriority(ownersPriority, o1.getOwner(), o2.getOwner());
            }
        });

        for (ParseItem parseItem : parseResult.getSubItems()) {
            if (parseItem.isContainEpisodes(needEpisodes)) {
                return parseItem;
            }
        }
        return null;
    }

    protected int comparePriority(List priority, Object o1, Object o2) {
        int prior1 = o1 != null ? priority.indexOf(o1) : -1;
        int prior2 = o2 != null ? priority.indexOf(o2) : -1;
        return (prior1 >= 0 ? prior1 : Integer.MAX_VALUE) - (prior2 >= 0 ? prior2 : Integer.MAX_VALUE);
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

    public boolean isSuccess() {
        return success;
    }
}
