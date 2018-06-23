package com.spacex.concurrent.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {

    private Logger logger = LoggerFactory.getLogger(TaskWithResult.class);

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        logger.info(String.format("task-%s executing", id));
        return String.format("Task-%s", id);
    }
}
