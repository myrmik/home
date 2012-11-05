package com.asevastyanov.torrentEater.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JobManager {
    private long periodInSeconds;

    private List<Job> jobList;

    private Timer timer;

    public JobManager() {
        timer = new Timer("Job Timer");
        jobList = new ArrayList<Job>();
    }

    public void startJobs() {
        if (jobList == null) return;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                executeJobs();
            }
        };

        timer.schedule(timerTask, 0, periodInSeconds * 1000);
    }

    public void stopJobs() {
        timer.cancel();
    }

    private void executeJobs() {
        List<Job> jobToRemove = new ArrayList<Job>();

        for (Job job : jobList) {
            if(job.execute()) {
                jobToRemove.add(job);
            }
        }

        jobList.removeAll(jobToRemove);
    }

    public long getPeriodInSeconds() {
        return periodInSeconds;
    }

    public void setPeriodInSeconds(long periodInSeconds) {
        this.periodInSeconds = periodInSeconds;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }
}
