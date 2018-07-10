package com.spacex.concurrent.threadlocal.random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadLocalRandomTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        logger.info("[Local Random With Latch]");
        final int threadCount = 200;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.submit(() -> {
                try {
                    await(startLatch);
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int sleepTime = random.nextInt(1000);
                    sleep(sleepTime);
                    logger.info("[sleepTime=]" + sleepTime + "," + random.nextInt());
                } finally {
                    endLatch.countDown();
                }

            });
        }

        startLatch.countDown();
        await(endLatch);
        logger.info("[Random] Test done!");
        exec.shutdown();
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
