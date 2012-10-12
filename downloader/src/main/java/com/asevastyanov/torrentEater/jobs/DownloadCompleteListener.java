package com.asevastyanov.torrentEater.jobs;

import java.util.EventListener;

public interface DownloadCompleteListener extends EventListener {
    public void downloadComplete(DownloadCompleteEvent event);
}
