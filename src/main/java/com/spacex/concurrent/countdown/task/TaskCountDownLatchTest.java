package com.spacex.concurrent.countdown.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskCountDownLatchTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(4);
        exec.submit(new WaitingTask(latch));
        for (int i = 0; i < 4; i++) {
            exec.submit(new TaskPortion(latch));
        }
        exec.shutdown();
    }
}
