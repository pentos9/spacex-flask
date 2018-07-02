package com.spacex.concurrent.barrier.cruncher;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberCyclicBarrierTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int barrierCount = 3;
        final int loop = 5;
        List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());

        CyclicBarrier cyclicBarrier = new CyclicBarrier(barrierCount + 1);
        ExecutorService executorService = Executors.newFixedThreadPool(barrierCount * loop);

        for (int i = 0; i < barrierCount; i++) {
            executorService.submit(new NumberCruncherWorker(cyclicBarrier, partialResults, loop));
        }

        await(cyclicBarrier);
        executorService.submit(new NumberAggregatorWorker(partialResults));

        executorService.shutdown();

    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
