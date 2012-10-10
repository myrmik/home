package com.jobs;

import com.download.subs.EpisodeDownloader;
import com.download.subs.KageDownloader;
import com.download.subs.NyaaDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.net.URL;
import java.util.List;

public class DownloadSubJob extends Job {

    private URL url;

    @Override
    @Autowired
    @Qualifier("subDownloader")
    public void setDownloader(EpisodeDownloader nyaaDownloader) {
        super.downloader = nyaaDownloader;
    }

    @Override
    public boolean execute() {
        validate();

        KageDownloader kageDownloader = (KageDownloader) downloader;
        kageDownloader.setUrl(url);

        return super.execute();
    }

    private void validate() {
        if (url == null) {
            errors.add(new JobError("Undefined subtitle url"));
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
