package com.spacex.concurrent.countdown.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WaitingTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(WaitingTask.class);

    public static List<String> cache = Collections.synchronizedList(new LinkedList<String>());

    private CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        logger.info("[WaitingTask] waiting for update database!");
        try {
            latch.await();
            commitResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void commitResult() {
        if (cache.size() != 4) {
            logger.error("update database failed and roll back now");
        } else {
            for (String result : cache) {
                logger.info(String.format("update result to database and result %s", result));
            }
        }
    }
}
