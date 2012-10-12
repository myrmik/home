package com.asevastyanov.torrentEater.files.process;

import com.asevastyanov.torrentEater.ass.exceptions.ParseException;
import com.asevastyanov.torrentEater.files.FileSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileListProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileListProcessor.class.getSimpleName());
    FileSearcher searcher;
    FileProcessor processor;

    public FileListProcessor(FileSearcher searcher, FileProcessor processor) {
        this.searcher = searcher;
        this.processor = processor;
    }

    public void process() {
        File[] list = searcher.search();
        if (list.length == 0) {
            logger.info("No files");
        }

        int max = getMaxPathSize(list);

        long start = new Date().getTime();

        try {
            for (File f : list) {
                if (f.isFile()) {
                    logger.info(String.format("%1$-" + (max + 6) + "s %2$s", "\n" + f.getName(), "start"));
                    processor.process(f);
                    if (processor.getLastMessage() != null) {
                        logger.info(processor.getLastMessage());
                    }
                    logger.info(String.format("%1$-" + (max + 6) + "s %2$s", "\n" + f.getName(), "end\n"));
                }
            }
        } catch (ParseException e) {
            logger.info(e.printStack());
        } catch (Exception e) {
            logger.debug(e.toString(), e);
        }

        long end = new Date().getTime();
        logger.info("Elapsed time: " + new SimpleDateFormat("mm:ss").format(new Date(end - start)) + "\n");
    }

    private int getMaxPathSize(File[] list) {
        int max = 0;
        for (File f : list) {
            String name = f.getName();
            if (max < name.length()) {
                max = name.length();
            }
        }

        return max;
    }

}
