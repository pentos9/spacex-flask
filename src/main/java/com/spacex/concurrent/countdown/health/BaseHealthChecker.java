package com.spacex.concurrent.countdown.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(BaseHealthChecker.class);

    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp;


    public BaseHealthChecker(String serviceName, CountDownLatch latch) {
        super();
        this.serviceName = serviceName;
        this.latch = latch;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable throwable) {
            serviceUp = false;
            logger.error(String.format("%s", throwable));
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
        logger.info("[Check] {} is done!", getServiceName());
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public abstract void verifyService();
}
