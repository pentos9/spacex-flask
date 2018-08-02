package com.spacex.concurrent.container;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int count = 10;
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        try {
            for (int i = 0; i < count; i++) {
                blockingQueue.put(String.format("%s", new Random().nextInt(100)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(blockingQueue);
        doClear(blockingQueue);
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.poll());
    }

    public static void doClear(LinkedBlockingQueue linkedBlockingQueue) {
        if (linkedBlockingQueue == null) {
            return;
        }

        linkedBlockingQueue.clear();
    }
}
