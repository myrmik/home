package com.asevastyanov.torrentEater.files.process;

import java.io.File;

public interface FileProcessor {
    public void process(File f) throws Exception;
    public String getLastMessage();
}
