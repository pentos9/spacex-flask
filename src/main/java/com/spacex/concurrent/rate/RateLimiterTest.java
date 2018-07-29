package com.spacex.concurrent.rate;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterTest.class);
    private static final RateLimiter rateLimiter = RateLimiter.create(100);

    private static class Task implements Runnable {

        private int id;

        public Task(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            logger.info(String.format("[Task] %s is executed!", id));
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Task> tasks = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            tasks.add(new Task(i));
        }

        for (Task task : tasks) {
            rateLimiter.acquire();
            logger.info(String.format("[Task-%s] acquire successfully!", task.getId()));
            exec.execute(task);
        }

        try {
            logger.info("[Sleep] start!");
            TimeUnit.SECONDS.sleep(1L);
            logger.info("[GetUp] now!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();


    }
}
