package com.spacex.concurrent.countdown.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchWithHookTest {

    private static Logger logger = LoggerFactory.getLogger(LatchWithHookTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        exec.submit(() -> {
            latch.countDown();
            logger.info("[Latch] do job after trigger.");
        });
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("[Hook] trigger...");
        }));

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
