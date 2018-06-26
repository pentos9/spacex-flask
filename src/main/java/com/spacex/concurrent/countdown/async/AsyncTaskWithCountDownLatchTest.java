package com.spacex.concurrent.countdown.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTaskWithCountDownLatchTest {

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskWithCountDownLatchTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        final CountDownLatch latch = new CountDownLatch(2);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        exec.submit(new AsyncTask(latch));
        exec.submit(new AsyncTask(latch));

        exec.submit(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            onComplete();
        });

        exec.shutdown();
    }

    public static void onComplete() {
        logger.info("AsyncTaskWithCountDownLatchTest#onComplete invoked.");
    }
}
