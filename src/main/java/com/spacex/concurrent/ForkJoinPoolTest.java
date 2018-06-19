package com.spacex.concurrent;

import com.spacex.concurrent.forkjoin.CustomForkJoinTask;
import com.spacex.concurrent.forkjoin.RecursiveTask;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        RecursiveTask task = new RecursiveTask();
        forkJoinPool.invoke(task);
        forkJoinPool.execute(new CustomForkJoinTask());
    }

}
