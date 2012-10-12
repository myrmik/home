package com.asevastyanov.torrentEater.jobs;

import com.asevastyanov.torrentEater.download.subs.EpisodeDownloader;
import com.asevastyanov.torrentEater.download.subs.NyaaDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class DownloadVideoJob extends Job {

    private List<String> formatList;

    @Override
    @Autowired
    @Qualifier("videoDownloader")
    public void setDownloader(EpisodeDownloader nyaaDownloader) {
        super.downloader = nyaaDownloader;
    }

    @Override
    public boolean execute() {
        NyaaDownloader nyaaDownloader = (NyaaDownloader) downloader;
        nyaaDownloader.setFormatPriority(formatList);

        return super.execute();
    }

    public List<String> getFormatList() {
        return formatList;
    }

    public void setFormatList(List<String> formatList) {
        this.formatList = formatList;
    }
}
