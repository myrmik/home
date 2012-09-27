package utils;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

public final class IntRange {
    private Range<Integer> range;

    private IntRange(int fromInclusive, int toInclusive) {
        range = Range.between(fromInclusive, toInclusive);
    }

    public static IntRange between(int fromInclusive, int toInclusive) {
        return new IntRange(fromInclusive, toInclusive);
    }

    public boolean contains(int element) {
        return range.contains(element);
    }

    public boolean containsRange(IntRange range) {
        return this.range.containsRange(range.range);
    }

    @Override
    public String toString() {
        boolean isOne = range.getMinimum().equals(range.getMaximum());
        if (isOne) {
            return range.getMinimum().toString();
        } else {
            return range.getMinimum() + "-" + range.getMaximum();
        }
    }

    public static List<IntRange> parse(String value) {
        if (value == null) return null;
        List<IntRange> res = new ArrayList<IntRange>();

        String[] sRanges = value.trim().split(",");
        for (String sRange : sRanges) {
            int firstEpisode, lastEpisode;
            String[] episodeRange = sRange.split("-");
            firstEpisode = Integer.valueOf(episodeRange[0].trim());
            if (episodeRange.length == 1) {
                lastEpisode = firstEpisode;
            } else {
                lastEpisode = Integer.valueOf(episodeRange[1].trim());
            }
            res.add(IntRange.between(firstEpisode, lastEpisode));
        }
        return res;
    }
}
