package com.spacex.concurrent.countdown.health;

import java.util.concurrent.CountDownLatch;

public class BadHealthChecker extends BaseHealthChecker {

    public BadHealthChecker(CountDownLatch latch) {
        super("Bad Health Service", latch);
    }

    @Override
    public void verifyService() {
        throw new RuntimeException("[Error] Service is not available!");
    }
}
