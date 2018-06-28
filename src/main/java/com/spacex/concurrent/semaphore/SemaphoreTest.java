package com.spacex.concurrent.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static Logger logger = LoggerFactory.getLogger(SemaphoreTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        logger.info("");
        Semaphore mutex = new Semaphore(1);
        mutex.tryAcquire();
        try {
            long result = sumUp(10);
            logger.info("[Result] result={}", result);
        } finally {
            mutex.release();
        }
    }

    public static long sumUp(int n) {
        if (n <= 1) {
            return 1;
        }

        return sumUp(n - 1) + 1;
    }
}

