package download.parser;

import utils.IntRange;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseItem {
    private List<IntRange> episodeRange;
    private URL url;
    private String errorMessage;
    private String owner;
    private String name;
    private String format;

    public ParseItem() {
        episodeRange = new ArrayList<IntRange>();
    }

    public List<IntRange> getEpisodeRange() {
        return episodeRange;
    }

    public void setEpisodeRange(List<IntRange> episodeRange) {
        this.episodeRange = episodeRange;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isContainEpisodes(IntRange episodeRange) {
        for (IntRange range : getEpisodeRange()) {
            if (range.containsRange(episodeRange)) {
                return true;
            }
        }
        return false;
    }
}
