package files.process;

import java.io.File;

public class DelFileProcessor implements FileProcessor {

    public DelFileProcessor() {
    }

    public void process(File f) throws Exception {
        f.delete();
    }

    public String getLastMessage() {
        return null;
    }
}
