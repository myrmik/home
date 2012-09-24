package files.process;

import ass.exceptions.ParseException;
import files.FileSearcher;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileListProcessor {
    private static final Logger logger = Logger.getLogger(FileListProcessor.class.getSimpleName());
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
            logger.debug(e);
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
