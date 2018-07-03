package com.spacex.concurrent.barrier.bank;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankWaterCountTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int barrierCount = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(barrierCount + 1);
        ExecutorService executorService = Executors.newFixedThreadPool(barrierCount + 1);
        ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

        for (int i = 0; i < barrierCount; i++) {
            executorService.submit(() -> {
                int randomValue = new Random().nextInt(100);
                System.out.print(randomValue + " ");
                sheetBankWaterCount.put(Thread.currentThread().getName(), randomValue);
                await(cyclicBarrier);
            });
        }

        await(cyclicBarrier);
        executorService.submit(() -> {
            int result = 0;
            for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
                result += sheet.getValue();
            }
            System.out.println("\n[Result] " + result);
        });

        executorService.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
