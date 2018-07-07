package com.spacex.concurrent.threadlocal;

import com.spacex.concurrent.threadlocal.sequence.SequenceNumber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadLocalTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        SequenceNumber sequenceNumber = new SequenceNumber();
        final int loopCount = 10;
        ExecutorService exec = Executors.newFixedThreadPool(100);
        IntStream.range(0, 3).forEach(item -> {
            exec.submit(() -> {
                for (int i = 0; i < loopCount; i++) {
                    Integer number = sequenceNumber.getNextNumber();
                    System.out.println(String.format("[Thread-%s] [sn]-> %s", item, number));
                }
            });
        });

        exec.shutdown();


    }
}