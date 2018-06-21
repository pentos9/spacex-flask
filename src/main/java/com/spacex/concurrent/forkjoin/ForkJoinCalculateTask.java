package com.spacex.concurrent.forkjoin;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculateTask extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 2;

    private int start;
    private int end;

    public ForkJoinCalculateTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean isDoCompute = (start - end) <= THRESHOLD;

        if (isDoCompute) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinCalculateTask leftTask = new ForkJoinCalculateTask(start, middle);
            ForkJoinCalculateTask rightTask = new ForkJoinCalculateTask(middle, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;

        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinCalculateTask task = new ForkJoinCalculateTask(1, 1000);
        Future<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());
        forkJoinPool.shutdown();
    }
}
