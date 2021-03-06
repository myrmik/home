package com.asevastyanov.torrentEater.download.parser.nyaa;

import com.asevastyanov.torrentEater.download.parser.ParseItem;
import com.asevastyanov.torrentEater.download.parser.Parser;
import com.asevastyanov.torrentEater.download.parser.soup.SoupParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.asevastyanov.torrentEater.utils.IntRange;
import com.asevastyanov.torrentEater.utils.VideoTorrentMatcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NyaaParser extends Parser<NyaaParseResult> {
    private static final Logger logger = LoggerFactory.getLogger(NyaaParser.class);

    private SoupParser htmlParser = new SoupParser();

    protected VideoTorrentMatcher videoTorrentMatcher;

    @Override
    public NyaaParseResult parse(String input) throws Exception {
        Document doc = htmlParser.parse(input).getDocument();
        Elements nameElements = doc.select("table[class=tlist] tr td[class=tlistname] a");
        Elements urlElements = doc.select("table[class=tlist] tr td[class=tlistdownload] a");

        if (nameElements == null || urlElements == null || nameElements.size() != urlElements.size()) {
            throw new Exception("Parsing failed");
        }

        ArrayList<ParseItem> list = new ArrayList<ParseItem>();
        for (int i = 0; i < nameElements.size(); i++) {
            ParseItem item = new ParseItem();
            item.setUrl(new URL(urlElements.get(i).attr("href")));
            parseOwnerAndEpisodes(nameElements.get(i).text(), item);
            list.add(item);
        }
        return new NyaaParseResult(list);
    }

    private void parseOwnerAndEpisodes(String input, ParseItem parseItem) throws Exception {
        try {
            if (videoTorrentMatcher == null) {
                throw new Exception("The torrent pattern is not defined!");
            }
            if (!videoTorrentMatcher.find(input)) {
                throw new Exception("Cannot find episodes for: '" + input + "'");
            }

            String owner = videoTorrentMatcher.getOwner();
            String name = videoTorrentMatcher.getName();
            String format = videoTorrentMatcher.getFormat();
            List<IntRange> episodes = videoTorrentMatcher.getEpisodes();

            parseItem.setOwner(owner);
            parseItem.setName(name);
            parseItem.setFormat(format);
            parseItem.setEpisodeRange(episodes);
        } catch (Exception e) {
            parseItem.setErrorMessage(e.toString());
            parseItem.getEpisodeRange().clear();
        }

    }

    /**
     *
     * @param pattern e.g. - *[${owner}]*${name}*${episode}*[${format}]*
     */
    public void setPattern(String pattern) {
        videoTorrentMatcher = new VideoTorrentMatcher(pattern);
    }
}
