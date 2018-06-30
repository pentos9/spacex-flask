package com.spacex.concurrent.page.task;

import java.util.concurrent.CountDownLatch;

public class CommodityRecommendListLoadingTask extends AbstractLoadingTask {
    public CommodityRecommendListLoadingTask(String serviceName, CountDownLatch latch) {
        super(serviceName, latch);
    }

    @Override
    protected String queryService() {
        return "Commodity Recommend List Loading Data";
    }
}
