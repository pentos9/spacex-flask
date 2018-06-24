package com.spacex.concurrent.countdown.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker {

    private Logger logger = LoggerFactory.getLogger(NetworkHealthChecker.class);

    public NetworkHealthChecker(CountDownLatch latch) {
        super("Network Service", latch);
    }

    @Override
    public void verifyService() {
        logger.info("[Checking] {}", getServiceName());

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("{} is Up!", getServiceName());
    }
}
