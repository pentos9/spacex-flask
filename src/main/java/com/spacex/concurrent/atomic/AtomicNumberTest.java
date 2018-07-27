package com.spacex.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicNumberTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        AtomicLong atomicLong = new AtomicLong(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        ExecutorService exec = Executors.newFixedThreadPool(20);
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(String.format("[AtomicInteger] ->%s", atomicInteger.getAndAdd(1)));
                System.out.println(String.format("[AtomicBoolean] ->%s", atomicBoolean.getAndSet(false)));
                System.out.println(String.format("[AtomicLong] ->%s", atomicLong.getAndAdd(2)));
            }
        };

        exec.execute(task);
        exec.shutdown();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Final]:" + atomicInteger.get());
        System.out.println("[Final]:" + atomicBoolean.get());
        System.out.println("[Final]:" + atomicLong.get());
    }
}
