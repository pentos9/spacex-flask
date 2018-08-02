package com.spacex.concurrent.container;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class QueueTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int count = 10;
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < count; i++) {
            priorityQueue.add(String.format("%s", new Random().nextInt()));
        }

        System.out.println(priorityQueue.peek());
        System.out.println("head:" + priorityQueue.peek());

        System.out.println("[Print] Before remove two elements!");
        printAll(priorityQueue.iterator());
        poll(priorityQueue, 5);
        System.out.println("[Print] After remove two elements!");
        printAll(priorityQueue.iterator());

    }

    public static void printAll(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void poll(PriorityQueue priorityQueue, int pollNumber) {
        if (priorityQueue == null) {
            return;
        }

        for (int i = 0; i < pollNumber; i++) {
            priorityQueue.poll();
        }
    }
}
