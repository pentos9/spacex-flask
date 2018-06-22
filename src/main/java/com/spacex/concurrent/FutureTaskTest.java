package com.spacex.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Callable<String> callable = () -> {
            Thread.sleep(5000L);
            String threadName = Thread.currentThread().getName();
            return threadName;
        };
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(futureTask);

        while (true) {
            if (futureTask.isDone()) {
                System.out.println("FutureTask is done!");
                try {
                    System.out.println("futureTask#get->" + futureTask.get());
                    executorService.shutdown();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Waiting task to be done");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
