package com.spacex.concurrent.countdown.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CountDownWorker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(CountDownWorker.class);
    private int id;
    private CountDownLatch latch;

    public CountDownWorker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        logger.info(String.format("[Worker-%s] Waiting for Job...", id));
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info(String.format("[Worker-%s] Received Job now and start to do it...", id));

    }
}
