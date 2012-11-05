package com.asevastyanov.torrentEater;

import com.asevastyanov.torrentEater.jobs.SubJob;
import com.asevastyanov.torrentEater.jobs.VideoJob;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public abstract class BaseTest {
    protected String DEST_PATH = "D:\\";

    @Autowired
    private VideoJob videoJob;

    @Autowired
    private SubJob subJob;

    protected SubJob createSubJob() throws Exception {
        subJob.setName("Fairy Tail");

        URL url = new URL("http://www.fansubs.ru/base.php?id=2629");
        subJob.setUrl(url);

        List<String> list = new ArrayList<String>();
        subJob.setOwners(list);

        subJob.setDestinationPath(DEST_PATH);

        subJob.setNeedEpisode(139);

        return subJob;
    }

    protected VideoJob createVideoJob() {
        videoJob.setName("Fairy Tail");

        List<String> ownerList = new ArrayList<String>();
        ownerList.add("HorribleSubs");
        videoJob.setOwners(ownerList);

        List<String> formatList = new ArrayList<String>();
        formatList.add("720p");
        videoJob.setFormatList(formatList);

        videoJob.setDestinationPath(DEST_PATH);

        videoJob.setNeedEpisode(139);

        return videoJob;
    }

}
