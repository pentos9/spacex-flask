package com.spacex.concurrent.countdown.converter;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConverterCountDownTest {

    private static Logger logger = LoggerFactory.getLogger(ConverterCountDownTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final CountDownLatch latch = new CountDownLatch(2);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        String message = "Micmacs A La Gare";
        List<Callable<String>> tasks = Arrays.asList(new UpperTask(latch, message), new LowerTask(latch, message));
        List<String> results = Lists.newArrayList();
        for (Callable<String> task : tasks) {
            Future<String> future = exec.submit(task);
            try {
                results.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        await(latch);
        results.forEach(System.out::println);
        exec.shutdown();
        logger.info("[Converter] All job were finished");
    }

    public static void await(CountDownLatch latch) {
        if (latch != null) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
