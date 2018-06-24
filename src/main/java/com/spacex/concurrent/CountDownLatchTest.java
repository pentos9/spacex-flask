package com.spacex.concurrent;

import com.spacex.concurrent.countdown.CountDownWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountDownLatchTest {

    private static Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) {
        run();
        output();
    }

    public static void run() {
        final int threadPoolSize = 5;
        final CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new CountDownWorker(String.valueOf(i), 1000L, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("[Latch] All Jobs are Done");

    }

    public static void output() {
        final int size = 10;
        final CountDownLatch latch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());

        List<Runnable> workers = Stream.generate(() -> {
            Runnable worker = () -> {
                outputScraper.add("Output Scraper");
                logger.info(String.format("[Output Worker - %s ] working now", Thread.currentThread().getName()));
                latch.countDown();
            };
            return worker;
        }).limit(size).collect(Collectors.toList());
        workers.forEach(executorService::submit);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        logger.info(String.format("%s", outputScraper));
    }
}
