package com.spacex.concurrent.countdown.converter;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public abstract class ConvertTask implements Callable<String> {
    private final CountDownLatch latch;

    private String value;

    public ConvertTask(CountDownLatch latch, String value) {
        this.latch = latch;
        this.value = value;
    }

    @Override
    public String call() {
        value = convert(value);
        latch.countDown();
        return value;
    }

    protected abstract String convert(String value);
}
