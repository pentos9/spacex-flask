package com.spacex.concurrent.countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownWorker implements Runnable {

    private String name;
    private long delay;
    private CountDownLatch countDownLatch;

    public CountDownWorker(String name, long delay, CountDownLatch countDownLatch) {
        this.name = name;
        this.delay = delay;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        sleep(delay);
        countDownLatch.countDown();
        System.out.println(String.format("[Worker] - name %s Finished Job", name));
    }

    public void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
