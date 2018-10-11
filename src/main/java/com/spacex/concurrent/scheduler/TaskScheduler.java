package com.spacex.concurrent.scheduler;


import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler extends Timer {

    public TaskScheduler() {
        super(true);
    }

    public TaskScheduler(boolean isDaemon) {
        super(isDaemon);
    }

    public static TaskScheduler newInstance(boolean isDaemon) {
        return new TaskScheduler(isDaemon);
    }

    public void scheduler(TimerTask timerTask, long delay, long period) {
        super.scheduleAtFixedRate(timerTask, delay, period);
    }
}
