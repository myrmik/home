import ass.AssElementContainer;
import download.subs.KageDownloader;
import download.subs.NyaaDownloader;
import files.FileSearcher;
import files.process.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class assUtil {
    private static final Logger logger = Logger.getLogger(assUtil.class);


    public static void main(String[] args) throws Exception {
        assUtil util = new assUtil();
        util.init();

        //util.unpackAndProcessSubs(args);
        //util.subDownloadTest();
        util.videoDownloadTest();



    }

    private void subDownloadTest() {
        try {
            KageDownloader subDownloader = (KageDownloader)new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"}).getBean("subDownloader");

            subDownloader.setName("Fairy Tail");

            URL url = new URL("http://www.fansubs.ru/base.php?id=2629");
            subDownloader.setUrl(url);

            List<String> list = new ArrayList<String>();
            subDownloader.setOwnersPriority(list);

            subDownloader.setToFile("D:\\");

            subDownloader.setNeedEpisode(139);

            subDownloader.download();
        } catch (Exception e) {
            logger.debug(e);
        }
    }

    private void videoDownloadTest() {
        try {
            NyaaDownloader videoDownloader = (NyaaDownloader)new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"}).getBean("videoDownloader");

            videoDownloader.setName("Fairy Tail");

            List<String> ownerList = new ArrayList<String>();
            ownerList.add("HorribleSubs");
            videoDownloader.setOwnersPriority(ownerList);

            List<String> formatList = new ArrayList<String>();
            formatList.add("720p");
            videoDownloader.setFormatPriority(formatList);

            videoDownloader.setToFile("D:\\");

            videoDownloader.setNeedEpisode(139);

            videoDownloader.download();
        } catch (Exception e) {
            logger.debug(e);
        }
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
