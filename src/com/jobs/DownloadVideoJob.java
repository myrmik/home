package com.jobs;

import com.download.subs.EpisodeDownloader;
import com.download.subs.NyaaDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
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
