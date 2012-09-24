package files.process;

import ass.AssDocument;

import java.io.File;

public class AssFileProcessor implements FileProcessor {
    private String backPath;

    public AssFileProcessor(String backPath) {
        this.backPath = backPath;
    }

    public void process(File f) throws Exception {
        AssDocument document = new AssDocument(f.getPath());

        document.parse();
        document.save(backPath + f.getName());
        document.changeMainStyle("Style: Default,Arial,75,&H00FFFFFF,&H000000FF,&H00000080,&H00000000,1,0,0,0,100,100,0,0,1,4.00,0,2,10,10,10,204");
        document.save(f.getPath());
    }

    public String getLastMessage() {
        return null;
    }
}
