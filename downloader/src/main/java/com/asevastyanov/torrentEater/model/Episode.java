package com.asevastyanov.torrentEater.model;

import java.math.BigDecimal;
import java.util.List;

public class Episode {
    BigDecimal id;
    String name;
    int needEpisode;
    List<String> subOwners;
    List<String> videoOwners;
    List<String> videoFormats;
    String subUrl;
    String destinationPath;
    EpisodeStatus status;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeedEpisode() {
        return needEpisode;
    }

    public void setNeedEpisode(int needEpisode) {
        this.needEpisode = needEpisode;
    }

    public List<String> getSubOwners() {
        return subOwners;
    }

    public void setSubOwners(List<String> subOwners) {
        this.subOwners = subOwners;
    }

    public List<String> getVideoFormats() {
        return videoFormats;
    }

    public void setVideoFormats(List<String> videoFormats) {
        this.videoFormats = videoFormats;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public EpisodeStatus getStatus() {
        return status;
    }

    public void setStatus(EpisodeStatus status) {
        this.status = status;
    }

    public List<String> getVideoOwners() {
        return videoOwners;
    }

    public void setVideoOwners(List<String> videoOwners) {
        this.videoOwners = videoOwners;
    }
}
