package com.spacex.concurrent;

import com.spacex.concurrent.countdown.signal.ObjectFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownSignalTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int threadCount = 10;
        ObjectFactory objectFactory = new ObjectFactory();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch stopSignal = new CountDownLatch(threadCount);


        List<Thread> tasks = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread task = new Thread(() -> {
                Object object;

                try {
                    System.out.println(String.format("[Thread - %s] waiting now", Thread.currentThread().getName()));
                    startSignal.await();
                    object = objectFactory.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    stopSignal.countDown();
                }
            });

            tasks.add(task);
            task.start();

        }

        startSignal.countDown();
        try {
            stopSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Thread thread : tasks) {
            System.out.println(thread.getName());
        }

    }
}
