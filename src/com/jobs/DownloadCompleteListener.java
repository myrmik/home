package com.jobs;

import java.util.EventListener;

public interface DownloadCompleteListener extends EventListener {
    public void downloadComplete(DownloadCompleteEvent event);
}
