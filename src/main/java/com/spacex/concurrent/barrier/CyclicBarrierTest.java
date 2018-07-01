package com.spacex.concurrent.barrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    private static Logger logger = LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int barrieCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(barrieCount);
        ExecutorService executorService = Executors.newFixedThreadPool(barrieCount);

        for (int i = 0; i < barrieCount; i++) {
            executorService.submit(new CyclicBarrierWriter("Init CyclicBarriers", cyclicBarrier));
        }

        executorService.submit(() -> {
            callback();
        });

        for (int i = 0; i < barrieCount; i++) {
            executorService.submit(new CyclicBarrierWriter("Reuse CyclicBarrier", cyclicBarrier));
        }

        executorService.submit(() -> {
            callback();
        });

        executorService.shutdown();

    }

    public static void callback() {
        logger.info("[Callback] All Job were finished and do some call back job now");
    }
}