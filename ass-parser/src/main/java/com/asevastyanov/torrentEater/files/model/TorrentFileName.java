package com.asevastyanov.torrentEater.files.model;

import com.asevastyanov.torrentEater.utils.StringUtils;

public class TorrentFileName {
    private String submitter;
    private String name;
    private String episode;

    public static final int NEED_RANK = 6;

    public static TorrentFileName parse(String data) {
        data = StringUtils.getRegExpMatch("(.*)\\.[^\\.]+$", data);

        TorrentFileName torrentFileName = new TorrentFileName();
        torrentFileName.setSubmitter(StringUtils.getRegExpMatch("[^\\[]*\\[([^\\]]*)\\]", data));
        torrentFileName.setName(StringUtils.getRegExpMatch("[^\\[]*\\[[^\\]]*\\]([^-]*)-", data));
        torrentFileName.setEpisode(StringUtils.getRegExpMatch("[^\\[]*\\[[^\\]]*\\].*-[^\\d]*(\\d+)", data));
        return torrentFileName;
    }

    public int getRankEquals(TorrentFileName other) {
        int rank = 0;
        if (name.toLowerCase().equals(other.getName().toLowerCase())) {
            rank += 4;
        } else if (StringUtils.fuzzyEquals(name.toLowerCase(), other.getName().toLowerCase(), 5)) {
            rank += 4;
        }

        if (episode.toLowerCase().equals(other.getEpisode().toLowerCase())) {
            rank += 2;
        }
        if (submitter.toLowerCase().equals(other.getSubmitter().toLowerCase())) {
            rank++;
        }

        return rank;
    }

    @Override
    public String toString() {
        return "[" + submitter + "] " + name + " - " + episode;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }
}
