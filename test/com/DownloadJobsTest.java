package com;

import com.jobs.DownloadSubJob;
import com.jobs.DownloadVideoJob;
import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DownloadJobsTest extends BaseTest {

    @Test
    public void verifySubDownloader() throws Exception {
        DownloadSubJob subJob = createSubJob();
        File expectedFile = new File(DEST_PATH + "Fairy Tail [119-144].rar");
        assertTrue(subJob.execute());
        assertTrue(expectedFile.exists());
        expectedFile.deleteOnExit();
    }

    @Test
    public void verifyVideoDownloader() {
        DownloadVideoJob videoJob = createVideoJob();
        File expectedFile = new File(DEST_PATH + "[HorribleSubs] Fairy Tail [139] [720p].torrent");
        assertTrue(videoJob.execute());
        assertTrue(expectedFile.exists());
        expectedFile.deleteOnExit();
    }
}
