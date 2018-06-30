package com.spacex.concurrent.page.task;

import java.util.concurrent.CountDownLatch;

public class ExceptionLoadingTask extends AbstractLoadingTask {

    public ExceptionLoadingTask(String serviceName, CountDownLatch latch) {
        super(serviceName, latch);
    }

    @Override
    protected String queryService() {
        throw new RuntimeException("Service Exception");
    }
}
