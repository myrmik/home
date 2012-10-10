package com.jobs;

import com.download.subs.EpisodeDownloader;
import com.utils.IntRange;

import javax.swing.event.EventListenerList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Job {
    protected EpisodeDownloader downloader;

    protected String name;
    protected List<String> ownerList;
    protected String destinationPath;
    protected IntRange needEpisodes;

    protected List<JobError> errors;

    private EventListenerList listenerList;

    protected Job() {
        errors = new ArrayList<JobError>();
        listenerList = new EventListenerList();
    }

    public abstract void setDownloader(EpisodeDownloader downloader);

    public void addClickListener(DownloadCompleteListener l) {
        listenerList.add(DownloadCompleteListener.class, l);
    }

    public void removeClickListener(DownloadCompleteListener l) {
        listenerList.remove(DownloadCompleteListener.class, l);
    }

    protected void fireDownloadCompleted(DownloadCompleteEvent e) {
        DownloadCompleteListener[] dcl = listenerList.getListeners(DownloadCompleteListener.class);
        for (DownloadCompleteListener l : dcl) {
            l.downloadComplete(e);
        }
    }

    public boolean execute() {
        validate();

        downloader.setName(name);
        downloader.setOwnersPriority(ownerList);
        downloader.setToFile(destinationPath);
        downloader.setNeedEpisodes(needEpisodes);

        downloader.download();
        if (downloader.isSuccess()) {
            fireDownloadCompleted(new DownloadCompleteEvent(this));
        }
        return downloader.isSuccess();
    }

    private void validate() {
        if (name == null) {
            errors.add(new JobError("Undefined episode name"));
        }

        if (destinationPath == null) {
            errors.add(new JobError("Undefined destination path"));
        }

        if (needEpisodes == null) needEpisodes = IntRange.ONE;
    }

    public List<JobError> getErrors() {
        return errors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<String> ownerList) {
        this.ownerList = ownerList;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public IntRange getNeedEpisodes() {
        return needEpisodes;
    }

    public void setNeedEpisode(int needEpisode) {
        this.needEpisodes = IntRange.between(needEpisode, needEpisode);
    }

    public void setNeedEpisodes(IntRange needEpisodes) {
        this.needEpisodes = needEpisodes;
    }
}
