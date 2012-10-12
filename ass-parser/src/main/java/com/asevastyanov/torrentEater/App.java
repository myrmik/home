package com.asevastyanov.torrentEater;

import com.asevastyanov.torrentEater.ass.AssElementContainer;
import com.asevastyanov.torrentEater.files.FileSearcher;
import com.asevastyanov.torrentEater.files.process.ArcAssFileProcessor;
import com.asevastyanov.torrentEater.files.process.AssFileProcessor;
import com.asevastyanov.torrentEater.files.process.DelFileProcessor;
import com.asevastyanov.torrentEater.files.process.FileListProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        App util = new App();
        util.init();

        util.unpackAndProcessSubs(args);
    }


    private void unpackAndProcessSubs(String[] args) {
        //String dir = ".";
        String dir = "d:\\1\\";
        String dirBack = ".\\assBack\\";
        if(args.length > 0)
          dir = args[0].trim();
        if(args.length > 1)
          dirBack = args[1].trim();

        logger.info("Torrent files deleting...");
        FileListProcessor delListProcessor =
                new FileListProcessor(new FileSearcher(dir, ".*\\.torrent$"), new DelFileProcessor());
        delListProcessor.process();

        logger.info("Ass files unpacking...");
        FileListProcessor arcAssFileProcessor =
                new FileListProcessor(new FileSearcher(dir, ".*\\.rar$|.*\\.zip$"), new ArcAssFileProcessor());
        arcAssFileProcessor.process();

        logger.info("Ass files updating...");
        FileListProcessor fileListProcessor =
                new FileListProcessor(new FileSearcher(dir, ".*\\.ass$"), new AssFileProcessor(dirBack));
        fileListProcessor.process();
    }

    private void init() throws IOException {
        AssElementContainer.getContainer().fill();

        try {
            System.loadLibrary("chilkat");
        } catch (UnsatisfiedLinkError e) {
         logger.error("Native code 'chilkat' library failed to load.\n" + e);
          System.exit(1);
        }
    }
}
