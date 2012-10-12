package com.asevastyanov.torrentEater.jobs;

import java.util.EventObject;

public class DownloadCompleteEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DownloadCompleteEvent(Object source) {
        super(source);
    }
}
