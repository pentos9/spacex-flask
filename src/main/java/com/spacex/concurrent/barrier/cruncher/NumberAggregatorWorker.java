package com.spacex.concurrent.barrier.cruncher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class NumberAggregatorWorker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(NumberAggregatorWorker.class);

    private List<List<Integer>> partialResults;

    public NumberAggregatorWorker(List<List<Integer>> partialResults) {
        this.partialResults = partialResults;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int sum = 0;

        for (List<Integer> threadPartialResult : partialResults) {
            logger.info(String.format("[%s] Generate result", threadName));
            for (Integer num : threadPartialResult) {
                System.out.print(num + " ");
                sum += num;
            }
            System.out.println();
        }

        logger.info(String.format("Final Result=%s", sum));
    }
}
