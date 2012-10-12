package com.asevastyanov.torrentEater;

import com.asevastyanov.torrentEater.jobs.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JobLauncherTest extends BaseTest {

    public final static long TIMEOUT = 10000;

    @Test
    public void verifySuccessDownload() throws Exception {
        final boolean[] isSubDownloadSuccess = {false};
        final boolean[] isVideoDownloadSuccess = {false};

        DownloadSubJob subJob = createSubJob();
        subJob.addClickListener(new DownloadCompleteListener() {
            public void downloadComplete(DownloadCompleteEvent event) {
                isSubDownloadSuccess[0] = true;
            }
        });

        DownloadVideoJob videoJob = createVideoJob();
        videoJob.addClickListener(new DownloadCompleteListener() {
            public void downloadComplete(DownloadCompleteEvent event) {
                isVideoDownloadSuccess[0] = true;
            }
        });


        JobLauncher launcher = new JobLauncher();
        launcher.setPeriodInSeconds(5);
        launcher.getJobList().add(subJob);
        launcher.getJobList().add(videoJob);

        launcher.start();

        Assert.assertTrue(waitUntilSuccess(TIMEOUT, isSubDownloadSuccess, isVideoDownloadSuccess));
    }

    private boolean waitUntilSuccess(long timeout, boolean[]... isSuccess) throws Exception {
        for(int i = 0; i < timeout/1000; i++) {
            boolean res = true;
            for(boolean[] success : isSuccess) {
                res = res && success[0];
            }
            if (res) {
                return true;
            }
            synchronized (this) {
                wait(1000);
            }
        }
        return false;
    }

}
