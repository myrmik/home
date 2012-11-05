package com.asevastyanov.torrentEater.jobs;

import com.asevastyanov.torrentEater.model.Episode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;

public class EpisodeJobs {
    private static final Logger logger = LoggerFactory.getLogger(EpisodeJobs.class);

    private Episode episode;

    @Autowired
    private SubJob subJob;

    @Autowired
    private VideoJob videoJob;

    public EpisodeJobs(Episode episode) {
        this.episode = episode;
        initSubJob();
        initVideoJob();

    }

    private void initSubJob() {
        URL url;
        try {
            url = new URL(episode.getSubUrl());
        } catch (MalformedURLException e) {
            logger.debug(e.toString(), e);
            url = null;
        }

        subJob.setName(episode.getName());
        subJob.setUrl(url);
        subJob.setOwners(episode.getSubOwners());
        subJob.setDestinationPath(episode.getDestinationPath());
        subJob.setNeedEpisode(episode.getNeedEpisode());
    }

    private void initVideoJob() {
        videoJob.setName(episode.getName());
        videoJob.setOwners(episode.getVideoOwners());
        videoJob.setFormatList(episode.getVideoFormats());
        videoJob.setDestinationPath(episode.getDestinationPath());
        videoJob.setNeedEpisode(episode.getNeedEpisode());
    }

    public SubJob getSubJob() {
        return subJob;
    }

    public VideoJob getVideoJob() {
        return videoJob;
    }


}
