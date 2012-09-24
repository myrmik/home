package download;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.List;

public abstract class Downloader {
    protected static final Logger logger = Logger.getLogger(Downloader.class);

    protected String name;
    protected URL url;
    protected List<String> ownersPriority;

    public Downloader(String name, URL url, List<String> ownersPriority) {
        this.name = name;
        this.url = url;
        this.ownersPriority = ownersPriority;
    }

    protected Downloader() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOwnersPriority() {
        return ownersPriority;
    }

    public void setOwnersPriority(List<String> ownersPriority) {
        this.ownersPriority = ownersPriority;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public abstract File download();

    @Override
    public String toString() {
        return "Downloader{" +
                "name='" + name + '\'' +
                ", url=" + url +
                ", ownersPriority=" + ownersPriority +
                '}';
    }
}
