package com.spacex.concurrent.lock.downgrade;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDowngradeTest {

    private volatile boolean cacheValid = false;
    private int currentValue = 0;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    private AtomicLong readCounter = new AtomicLong(0);
    private AtomicLong writerCounter = new AtomicLong(0);


    public void run() {
        final int threadCount = 100;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.submit(() -> {
                try {
                    await(startLatch);
                    processCacheDataDownGrading(new Random().nextInt(100));
                } finally {
                    endLatch.countDown();
                }

            });
        }

        startLatch.countDown();
        await(endLatch);
        System.out.println(String.format("[Job] Done! currentValue:%s", currentValue));
        System.out.println(String.format("read thread counter:%s,write thread counter:%s", readCounter.get(), writerCounter.get()));
        exec.shutdown();

    }

    private void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processCacheDataDownGrading(int newValue) {
        readLock.lock();

        if (!cacheValid) {
            readCounter.incrementAndGet();
            readLock.unlock();
            System.out.println("[Release] Lock:" + readCounter.get());
            writeLock.lock();
            writerCounter.incrementAndGet();
            System.out.println("[Write Lock] Lock");
            try {
                if (!cacheValid) {
                    currentValue = newValue;
                    cacheValid = true;
                    readLock.lock();
                }

            } finally {
                writeLock.unlock();
            }

        } else {
            System.out.println("[Print]" + readCounter.get());
        }

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.getReadLockCount() > 0) {
                readLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDowngradeTest rocketMan = new ReadWriteLockDowngradeTest();
        rocketMan.run();
    }
}
