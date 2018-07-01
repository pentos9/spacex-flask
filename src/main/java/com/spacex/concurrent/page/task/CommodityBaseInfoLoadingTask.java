package com.spacex.concurrent.page.task;

import java.util.concurrent.CountDownLatch;

public class CommodityBaseInfoLoadingTask extends AbstractLoadingTask {

    public CommodityBaseInfoLoadingTask(String serviceName, CountDownLatch latch) {
        super(serviceName, latch);
    }

    @Override
    protected String queryService() {
        return "Commodity Base Info Loading Data";
    }
}
