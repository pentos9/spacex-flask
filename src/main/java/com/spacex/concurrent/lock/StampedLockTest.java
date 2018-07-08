package com.spacex.concurrent.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    private static Logger logger = LoggerFactory.getLogger(StampedLockTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        //stampedLock is not reentrant
        StampedLock stampedLock = new StampedLock();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        Map<String, String> map = new HashMap<String, String>();
        exec.submit(() -> {
            long stamp = stampedLock.writeLock();
            try {
                map.put("foo", "bar");
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        });
        sleep(1000);
        logger.info(String.format("%s", map));


        Runnable readTask = () -> {
            long stamp = stampedLock.readLock();
            try {
                String value = map.get("foo");
                logger.info(value);
                sleep(1000L);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        };

        exec.submit(readTask);
        exec.submit(readTask);
        exec.shutdown();
    }

    private static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
