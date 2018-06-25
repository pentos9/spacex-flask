package com.spacex.concurrent.countdown.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {

    private Logger logger = LoggerFactory.getLogger(DatabaseHealthChecker.class);

    public DatabaseHealthChecker(CountDownLatch latch) {
        super("Database Service", latch);
    }

    @Override
    public void verifyService() {
        logger.info("[Checking] {}", getServiceName());

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("{} is Up", getServiceName());
    }
}
