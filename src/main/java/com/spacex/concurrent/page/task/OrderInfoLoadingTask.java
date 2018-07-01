package com.spacex.concurrent.page.task;

import java.util.concurrent.CountDownLatch;

public class OrderInfoLoadingTask extends AbstractLoadingTask {
    public OrderInfoLoadingTask(String serviceName, CountDownLatch latch) {
        super(serviceName, latch);
    }

    @Override
    protected String queryService() {
        return "Order Info Loading Data";
    }
}
