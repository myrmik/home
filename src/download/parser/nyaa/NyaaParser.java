package download.parser.nyaa;

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

public class NyaaParser extends Parser<NyaaParseResult> {
    private static final Logger logger = Logger.getLogger(NyaaParser.class);

    private SoupParser htmlParser = new SoupParser();

    private String episodeName;

    public String NYAA_URL_PREFIX = "http://www.nyaa.eu/?page=search&cats=0_0&filter=0&term=";

    protected Pattern EPISODES_OWNER_PATTERN;

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
            Matcher episodesMatcher = EPISODES_OWNER_PATTERN.matcher(input);
            if (!episodesMatcher.find()) {
                throw new Exception("Cannot parse episodes for: '" + input + "'");
            }

            String owner = episodesMatcher.group(1);
            int episode = Integer.valueOf(episodesMatcher.group(2)); //todo add episode range support

            parseItem.setOwner(owner);
            parseItem.getEpisodeRange().add(Range.between(episode, episode));
        } catch (Exception e) {
            parseItem.setErrorMessage(e.toString());
            parseItem.getEpisodeRange().clear();
        }

    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
        EPISODES_OWNER_PATTERN = Pattern.compile(".*\\[(.*)\\].*" + episodeName.trim().replace(" ", ".*") + "[^\\d]*(\\d+)");
    }
}
