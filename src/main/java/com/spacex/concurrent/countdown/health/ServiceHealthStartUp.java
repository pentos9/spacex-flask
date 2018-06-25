package com.spacex.concurrent.countdown.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceHealthStartUp {

    private static Logger logger = LoggerFactory.getLogger(ServiceHealthStartUp.class);

    public ServiceHealthStartUp() {
    }

    public static boolean checkEternalServices() {

        final int latchSize = 3;
        final int threadPoolSize = 5;
        CountDownLatch latch = new CountDownLatch(latchSize);
        List<BaseHealthChecker> services = new ArrayList<>();
        services.add(new NetworkHealthChecker(latch));
        services.add(new CacheHealthChecker(latch));
        services.add(new DatabaseHealthChecker(latch));

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        services.forEach(executorService::submit);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        for (final BaseHealthChecker checker : services) {
            if (!checker.isServiceUp()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean result = checkEternalServices();
        logger.info("External Service is completed!The Result is:{}", result);
    }
}
