package com.spacex.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentContainerTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int threadCount = 100;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        ThreadSafeArrayList<String> threadSafeArrayList = new ThreadSafeArrayList<>();
        final AtomicLong counter = new AtomicLong(0);

        for (int i = 0; i < threadCount; i++) {
            exec.submit(() -> {
                try {
                    await(startLatch);
                    threadSafeArrayList.add(String.format("Worker-%s", counter.getAndAdd(1)));
                } finally {
                    endLatch.countDown();
                }
            });
        }

        startLatch.countDown();
        await(endLatch);

        System.out.println(String.format("[Worker] %s", counter.get()));
        System.out.println(threadSafeArrayList.get(0));
        exec.shutdown();
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
