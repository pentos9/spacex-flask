package com.spacex.concurrent.executor.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static com.spacex.concurrent.executor.custom.CustomThreadPoolExecutor.THREAD_POOL_EXECUTOR;

public class CustomThreadPoolExecutorTest {

    private static Logger logger = LoggerFactory.getLogger(CustomThreadPoolExecutorTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        AtomicLong counter = new AtomicLong(1);
        final int threadCount = 1000;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        Runnable asyncTask = () -> {
            logger.info(String.format("[AsyncTask#%s] trigger now!", counter.getAndIncrement()));
        };

        for (int i = 0; i < threadCount; i++) {
            THREAD_POOL_EXECUTOR.execute(asyncTask);
        }

        THREAD_POOL_EXECUTOR.shutdown();

    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
