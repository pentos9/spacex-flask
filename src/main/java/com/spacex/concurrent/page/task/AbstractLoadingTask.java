package com.spacex.concurrent.page.task;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractLoadingTask implements Callable<String> {
    private String serviceName;
    private CountDownLatch latch;

    public AbstractLoadingTask(String serviceName, CountDownLatch latch) {
        this.serviceName = serviceName;
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try {
            return queryService();
        } finally {
            latch.countDown();
        }
    }

    protected abstract String queryService();

    public String getServiceName() {
        return serviceName;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
