package com.spacex.concurrent.countdown.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class AsyncTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    private final CountDownLatch latch;

    public AsyncTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doJob();
        } finally {
            latch.countDown();
        }

    }

    public void doJob() {
        logger.info("[Task] do job now...");
    }
}
