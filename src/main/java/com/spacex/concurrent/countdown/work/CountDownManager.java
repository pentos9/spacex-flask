package com.spacex.concurrent.countdown.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CountDownManager implements Runnable {

    private Logger logger = LoggerFactory.getLogger(CountDownManager.class);

    private int id;
    private CountDownLatch latch;

    public CountDownManager(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        logger.info(String.format("[Manager-%s] is ready now and giving command.", id));

        for (int i = 5; 0 < i; i--) {
            logger.info(String.format("[Counter] %s", i));
            latch.countDown();
            sleep(1000L);
        }

        logger.info(String.format("[Manager-%s] All commands is finished.", id));

    }

    public void sleep(long sleepMilliseconds) {
        try {
            Thread.sleep(sleepMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
