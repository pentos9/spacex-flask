package com.spacex.concurrent.page.task;

import java.util.concurrent.CountDownLatch;

public class CommentListLoadingTask extends AbstractLoadingTask {

    public CommentListLoadingTask(String serviceName, CountDownLatch latch) {
        super(serviceName, latch);
    }

    @Override
    protected String queryService() {
        return "Comment List Loading Data";
    }
}
