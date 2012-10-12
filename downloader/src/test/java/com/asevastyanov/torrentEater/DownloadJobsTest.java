package com.asevastyanov.torrentEater;

import com.asevastyanov.torrentEater.jobs.DownloadSubJob;
import com.asevastyanov.torrentEater.jobs.DownloadVideoJob;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class DownloadJobsTest extends BaseTest {

    @Test
    public void verifySubDownloader() throws Exception {
        DownloadSubJob subJob = createSubJob();
        File expectedFile = new File(DEST_PATH + "Fairy Tail [119-144].rar");
        Assert.assertTrue(subJob.execute());
        Assert.assertTrue(expectedFile.exists());
        expectedFile.deleteOnExit();
    }

    @Test
    public void verifyVideoDownloader() {
        DownloadVideoJob videoJob = createVideoJob();
        File expectedFile = new File(DEST_PATH + "[HorribleSubs] Fairy Tail [139] [720p].torrent");
        Assert.assertTrue(videoJob.execute());
        Assert.assertTrue(expectedFile.exists());
        expectedFile.deleteOnExit();
    }
}
