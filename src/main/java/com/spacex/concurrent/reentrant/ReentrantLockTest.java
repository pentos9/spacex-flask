package com.spacex.concurrent.reentrant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        AtomicLong adder = new AtomicLong(0);
        ReentrantLock reentrantLock = new ReentrantLock(true);
        ExecutorService exec = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                try {
                    reentrantLock.lock();
                    adder.incrementAndGet();
                    System.out.println(adder.get());
                } finally {
                    reentrantLock.unlock();
                }
            });
        }

        exec.shutdown();

    }
}
