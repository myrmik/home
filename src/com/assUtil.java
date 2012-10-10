package com;

import com.ass.AssElementContainer;
import com.download.subs.KageDownloader;
import com.files.FileSearcher;
import com.files.process.*;
import com.jobs.DownloadVideoJob;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class assUtil {
    private static final Logger logger = Logger.getLogger(assUtil.class);

    public static void main(String[] args) throws Exception {
        assUtil util = new assUtil();
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
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        BasicConfigurator.configure();
        AssElementContainer.getContainer().fill();

        try {
            System.loadLibrary("chilkat");
        } catch (UnsatisfiedLinkError e) {
         logger.error("Native code 'chilkat' library failed to load.\n" + e);
          System.exit(1);
        }
    }
}
