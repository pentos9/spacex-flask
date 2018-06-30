package com.spacex.concurrent.countdown.work;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManagerWorkerTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int COUNT = 5;
        ExecutorService exec = Executors.newFixedThreadPool(COUNT * 2);
        CountDownLatch latch = new CountDownLatch(COUNT);

        for (int i = 0; i < COUNT; i++) {
            exec.submit(new CountDownWorker(i, latch));
        }

        exec.submit(new CountDownManager(new Random().nextInt(), latch));

        exec.shutdown();
    }
}
