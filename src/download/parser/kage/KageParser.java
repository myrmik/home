package download.parser.kage;

import download.parser.ParseItem;
import download.parser.Parser;
import download.parser.soup.SoupParser;
import org.apache.commons.lang3.Range;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KageParser extends Parser<KageParseResult> {
    private static final Logger logger = Logger.getLogger(KageParser.class);

    private SoupParser htmlParser = new SoupParser();

    public String SUB_URL_PREFIX = "http://www.fansubs.ru/base.php?srt=";

    protected Pattern EPISODES_PATTERN = Pattern.compile("�� ([\\s*\\d*,*\\-*]+)[^\\s\\d,\\-]*");

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

            String[] sRanges = episodesMatcher.group(1).trim().split(",");
            for (String sRange : sRanges) {
                int firstEpisode, lastEpisode;
                String[] episodeRange = sRange.split("-");
                firstEpisode = Integer.valueOf(episodeRange[0].trim());
                if (episodeRange.length == 1) {
                    lastEpisode = firstEpisode;
                } else {
                    lastEpisode = Integer.valueOf(episodeRange[1].trim());
                }
                parseItem.getEpisodeRange().add(Range.between(firstEpisode, lastEpisode));
            }
        } catch (Exception e) {
            parseItem.setErrorMessage(e.getMessage());
            parseItem.getEpisodeRange().clear();
        }
    }
}
