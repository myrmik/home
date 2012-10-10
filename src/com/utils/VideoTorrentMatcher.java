package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoTorrentMatcher {

    private Pattern PLACEHOLDER = Pattern.compile("\\$\\{([^\\}]*)\\}");

    private final String OWNER = "owner";
    private final String NAME = "name";
    private final String EPISODE = "episode";
    private final String FORMAT = "format";

    private Pattern pattern;
    private Matcher matcher;

    private List<String> placeholders;

    public VideoTorrentMatcher(String pattern) {
        parse(pattern);
    }

    /**
     * Parses the pattern
     *
     * @param pattern e.g. - *[${owner}]*${name}*${episode}*[${format}]*
     */
    private void parse(String pattern) {
        placeholders = getPlaceholders(pattern);

        pattern = pattern.replace("*", ".*")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("${" + OWNER + "}", "(.*)")
                .replace("${" + NAME + "}", "([^\\d]*)")
                .replace("${" + EPISODE + "}", "[^\\d]*([\\d*,*\\-*]+)")
                .replace("${" + FORMAT + "}", "(\\d+p)");
        this.pattern = Pattern.compile(pattern);
    }

    private List<String> getPlaceholders(String input) {
        List<String> placeholderOrder = new ArrayList<String>();
        List placeholders = Arrays.asList(OWNER, NAME, EPISODE, FORMAT);
        Matcher matcher = PLACEHOLDER.matcher(input);
        while (matcher.find()) {
            String ph = matcher.group(1);
            if (placeholders.contains(ph)) {
                placeholderOrder.add(ph);
            }
        }
        return placeholderOrder;
    }

    public boolean find(String value) {
        value = value.replace("_", " ");
        matcher = pattern.matcher(value);
        return matcher.find();
    }

    public String getGroupValueFor(String placeholder) {

        int group = placeholders.indexOf(placeholder) + 1;
        if (group == 0 || matcher == null) {
            return null;
        }
        return matcher.group(group);
    }

    private String formatName(String name) {
        if (name == null) return null;
        name = name.trim();
        if (name.startsWith("-")) {
            name = name.replaceFirst("-", "");
        }
        if (name.endsWith("-")) {
            name = name.substring(0, name.length() - 1);
        }
        return name.trim();
    }

    public String getOwner() {
        return getGroupValueFor(OWNER);
    }

    public String getName() {
        return formatName(getGroupValueFor(NAME));
    }

    public List<IntRange> getEpisodes() {
        return IntRange.parse(getGroupValueFor(EPISODE));
    }

    public String getFormat() {
        return getGroupValueFor(FORMAT);
    }

}
