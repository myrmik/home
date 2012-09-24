package files;

import org.apache.log4j.Logger;

import java.io.File;

public class FileSearcher {
    private static final Logger logger = Logger.getLogger(FileSearcher.class);
    private String path;
    private String regExp;

    public FileSearcher(String regExp) {
        this.path = ".";
        this.regExp = regExp;
    }

    public FileSearcher(String path, String regExp) {
        this.path = path.toLowerCase();
        this.regExp = regExp;
    }

    public File[] search() {
        File file = new File(path);
        File[] list = file.listFiles(new PatternFileFilter(regExp));
        if (list == null || list.length == 0) {
            return new File[0];
        }

        return list;
    }

}
