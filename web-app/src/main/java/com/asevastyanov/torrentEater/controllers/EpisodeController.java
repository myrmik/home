package com.asevastyanov.torrentEater.controllers;

import com.asevastyanov.torrentEater.jobs.EpisodeJobs;
import com.asevastyanov.torrentEater.model.Episode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/episode", method = RequestMethod.GET)
public class EpisodeController extends BaseController {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Episode login(Episode episode) {
        EpisodeJobs episodeJobs = new EpisodeJobs(episode);


        return null;
    }

}
