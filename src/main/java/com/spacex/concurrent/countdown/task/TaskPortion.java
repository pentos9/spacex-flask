package com.spacex.concurrent.countdown.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class TaskPortion implements Runnable {

    private Logger logger = LoggerFactory.getLogger(TaskPortion.class);

    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        updateDatabase();
    }

    public void updateDatabase() {
        try {
            Thread.sleep(2000L);
            String result = String.format("result from %s", Thread.currentThread().getName());
            WaitingTask.cache.add(result);
        } catch (Exception e) {
            logger.error(String.format("ERROR update database failed:%s", e));
        } finally {
            latch.countDown();
        }
    }
}
