package com.spacex.concurrent.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAllTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Callable<String>> callableList = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");
        try {
            exec.invokeAll(callableList).stream().map(future -> {
                String result = null;
                try {
                    result = future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return result;
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exec.shutdown();
    }

}
