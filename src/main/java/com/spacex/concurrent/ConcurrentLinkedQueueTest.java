package com.spacex.concurrent;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
        concurrentLinkedQueue.addAll(Arrays.asList("Google", "Apple", "Facebook", "Spacex"));

        Runnable producer = () -> {
            for (int i = 0; i < 20; i++) {
                String item = "Item-" + i;
                concurrentLinkedQueue.offer(item);
                System.out.println(String.format("[Producer - %s] Offer-> %s", Thread.currentThread().getName(), item));
            }
        };

        Runnable consumer = () -> {
            while (concurrentLinkedQueue.size() > 0) {
                System.out.println(String.format("[Consumer - %s] Poll-> %s", Thread.currentThread().getName(), concurrentLinkedQueue.poll()));
            }
        };


        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);

        p.start();
        c.start();

    }
}
