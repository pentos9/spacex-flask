package com.spacex.concurrent.countdown.stress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StressTestingWithCountDownLatch {

    private static Logger logger = LoggerFactory.getLogger(StressTestingWithCountDownLatch.class);

    public static void main(String[] args) {
        run();
    }

    /**
     * this method can be used to execute a stress test
     * it is awesome and I love it
     */
    public static void run() {
        final int currentCount = 500;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(currentCount);
        ExecutorService exec = Executors.newFixedThreadPool(currentCount);
        for (int i = 0; i < currentCount; i++) {
            exec.submit(() -> {
                try {
                    startLatch.await();
                    testLoad();
                } catch (InterruptedException e) {
                    logger.error(String.format("%s", e));
                } finally {
                    endLatch.countDown();
                }

            });
        }

        startLatch.countDown();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            logger.error(String.format("%s", e));
        }

        exec.shutdown();

    }

    public static void testLoad() {
        logger.info("[Business] testLoad executing,threadName:{},timestamp:{}", Thread.currentThread().getName(), System.currentTimeMillis());
    }
}
