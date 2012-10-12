package com.asevastyanov.torrentEater.download.parser.kage;

import com.asevastyanov.torrentEater.download.parser.ParseItem;
import com.asevastyanov.torrentEater.download.parser.Parser;
import com.asevastyanov.torrentEater.download.parser.soup.SoupParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.asevastyanov.torrentEater.utils.IntRange;

import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KageParser extends Parser<KageParseResult> {
    private static final Logger logger = LoggerFactory.getLogger(KageParser.class);

    private SoupParser htmlParser = new SoupParser();

    public String SUB_URL_PREFIX = "http://www.fansubs.ru/base.php?srt=";

    protected Pattern EPISODES_PATTERN = Pattern.compile("าย ([\\s*\\d*,*\\-*]+)[^\\s\\d,\\-]*");

    @Override
    public KageParseResult parse(String input) throws Exception {
        Document doc = htmlParser.parse(input).getDocument();
        Elements subUrls = doc.select("table[width=750]:not(table[align]) input[name=srt]");
        Elements episodes = doc.select("table[width=750]:not(table[align]) tr font b");

        if (subUrls == null || episodes == null || episodes.size() != subUrls.size()) {
            throw new Exception("Parsing failed");
        }

        ArrayList<ParseItem> list = new ArrayList<ParseItem>();
        for (int i = 0; i < subUrls.size(); i++) {
            ParseItem item = new ParseItem();
            item.setUrl(new URL(SUB_URL_PREFIX + subUrls.get(i).attr("value")));
            parseEpisodes(episodes.get(i).text(), item);
            list.add(item);
        }
        return new KageParseResult(list);
        // todo implement owner parse
    }

    private void parseEpisodes(String input, ParseItem parseItem) {
        try {
            Matcher episodesMatcher = EPISODES_PATTERN.matcher(input);
            if (!episodesMatcher.find()) {
                throw new Exception("Cannot parse episodes for: '" + input + "'");
            }

            parseItem.setEpisodeRange(IntRange.parse(episodesMatcher.group(1)));
        } catch (Exception e) {
            parseItem.setErrorMessage(e.getMessage());
            parseItem.getEpisodeRange().clear();
        }
    }
}
