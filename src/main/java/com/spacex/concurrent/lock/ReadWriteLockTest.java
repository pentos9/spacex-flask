package com.spacex.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        run();
    }

    private static class Adder {
        private int number = 0;

        public Adder(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    public static void run() {
        Adder number = new Adder(0);
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        CountDownLatch latch = new CountDownLatch(10);

        Runnable safeTask = () -> {
            Lock lock = readWriteLock.writeLock();
            try {

                lock.lock();
                number.setNumber(number.getNumber() + 1);
            } finally {
                latch.countDown();
                lock.unlock();
            }
        };

        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(safeTask);
        }


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Safe]" + number.getNumber());

        Runnable unsafeTask = () -> {
            number.setNumber(number.getNumber() + 1);
        };

        for (int i = 0; i < 10; i++) {
            exec.submit(unsafeTask);
        }

        System.out.println(number.getNumber());
        exec.shutdown();

    }

    private static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
