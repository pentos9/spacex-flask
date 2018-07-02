package com.spacex.concurrent.barrier.cruncher;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NumberCruncherWorker implements Runnable {
    private Logger logger = LoggerFactory.getLogger(NumberCruncherWorker.class);

    private CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults;
    private Random random = new Random();
    private int NUM_PARTIAL_RESULTS;


    public NumberCruncherWorker(CyclicBarrier cyclicBarrier, List<List<Integer>> partialResults, int NUM_PARTIAL_RESULTS) {
        this.cyclicBarrier = cyclicBarrier;
        this.partialResults = partialResults;
        this.NUM_PARTIAL_RESULTS = NUM_PARTIAL_RESULTS;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        List<Integer> partialResult = Lists.newArrayList();
        for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
            Integer num = random.nextInt(10);
            partialResult.add(num);
        }

        partialResults.add(partialResult);

        try {
            logger.info(String.format("[Worker-%s] Waiting for others to reach barrier!", threadName));
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
