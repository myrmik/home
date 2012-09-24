package download.parser;

import org.apache.commons.lang3.Range;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseItem implements Comparable {
    private List<Range<Integer>> episodeRange;
    private URL url;
    private String errorMessage;
    private String owner;

    public ParseItem() {
        episodeRange = new ArrayList<Range<Integer>>();
    }

    public List<Range<Integer>> getEpisodeRange() {
        return episodeRange;
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

    public boolean isContainEpisodes(Range<Integer> episodeRange) {
        for (Range<Integer> range : getEpisodeRange()) {
            if (range.containsRange(episodeRange)) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Object o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
