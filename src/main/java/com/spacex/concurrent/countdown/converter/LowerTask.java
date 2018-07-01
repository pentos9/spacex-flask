package com.spacex.concurrent.countdown.converter;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.CountDownLatch;

public class LowerTask extends ConvertTask {

    public LowerTask(CountDownLatch latch, String value) {
        super(latch, value);
    }

    @Override
    protected String convert(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        return value.toLowerCase();
    }
}
