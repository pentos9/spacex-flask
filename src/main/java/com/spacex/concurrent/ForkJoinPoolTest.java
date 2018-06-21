package com.spacex.concurrent;

import com.spacex.concurrent.forkjoin.CustomForkJoinTask;
import com.spacex.concurrent.forkjoin.CustomRecursiveTask;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        CustomRecursiveTask task = new CustomRecursiveTask();
        forkJoinPool.invoke(task);
        forkJoinPool.execute(new CustomForkJoinTask());
    }

}
