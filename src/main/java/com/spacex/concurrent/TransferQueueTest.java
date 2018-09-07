package com.spacex.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        exec.submit(new Producer("producer-1", queue));

        exec.submit(new Consumer("consumer-1", queue));
        exec.submit(new Consumer("consumer-2", queue));

        exec.shutdown();
    }

    static class Producer implements Runnable {
        private final String name;
        private final TransferQueue<String> queue;

        public Producer(String name, TransferQueue<String> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("");
            for (int i = 0; i < 10; i++) {
                try {
                    queue.transfer("[Producer]" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("[After] transformation！");
            System.out.println(name + " is over!");
        }
    }

    static class Consumer implements Runnable {

        private final String name;
        private final TransferQueue<String> queue;
        private final Random random = new Random(System.currentTimeMillis());

        public Consumer(String name, TransferQueue<String> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {

            for (int i = 0; i < 5; i++) {
                try {
                    String value = queue.take();
                    System.out.println(name + "[Take] " + value);
                    TimeUnit.SECONDS.sleep(random.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(name + "is over!");
        }
    }
}
