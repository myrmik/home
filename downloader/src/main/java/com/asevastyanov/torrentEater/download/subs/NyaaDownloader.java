package com.asevastyanov.torrentEater.download.subs;

import com.asevastyanov.torrentEater.download.parser.ParseItem;
import com.asevastyanov.torrentEater.download.parser.nyaa.NyaaParseResult;
import com.asevastyanov.torrentEater.download.parser.nyaa.NyaaParser;
import com.asevastyanov.torrentEater.utils.IntRange;

import java.io.File;
import java.io.IOException;
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
    public File download() {
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
            if (parseItem.isContainEpisodes(needEpisodes) && isContainOnlyOneEpisode(parseItem)) {
                return parseItem;
            }
        }
        return null;
    }

    /**
     *
     * @param pattern e.g. - *[${owner}]*${name}*${episode}*[${format}]*
     * @return
     */
    public File download(String pattern) {
        try {
            String encEpisodeName = URLEncoder.encode(name + " " + needEpisodes, "UTF-8");
            this.url = new URL(NYAA_URL_PREFIX + encEpisodeName);
        } catch (IOException e) {
            logger.debug("Wrong URL: " + url, e);
            return null;
        }

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

    @Override
    public void setNeedEpisodes(IntRange needEpisodes) {
        if (needEpisodes.periodSize() > 0) {
            logger.warn("For the NyaaDownloader episode range must equals ZERO !!!");
            logger.warn("Was: " + needEpisodes + ", Now: " + needEpisodes.getMin());
            super.setNeedEpisodes(IntRange.valueOf(needEpisodes.getMin()));
        } else {
            super.setNeedEpisodes(needEpisodes);
        }
    }

    public boolean isContainOnlyOneEpisode(ParseItem parseItem) {
        if (parseItem.getEpisodeRange().size() != 1) {
            return false;
        }

        IntRange range = parseItem.getEpisodeRange().get(0);
        return range != null && range.periodSize() == 0;
    }
}
