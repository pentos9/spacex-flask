package com.spacex.concurrent.barrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWriter implements Runnable {

    private Logger logger = LoggerFactory.getLogger(CyclicBarrierWriter.class);

    private String name;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWriter(String name, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            doJob();
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void doJob() {
        try {
            logger.info("[Cyclic Worker] {} {} do job now,time:{}", Thread.currentThread().getName(), name, LocalDateTime.now());
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
