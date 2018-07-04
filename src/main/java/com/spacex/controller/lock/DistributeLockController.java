package com.spacex.controller.lock;

import com.spacex.service.lock.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("flask")
public class DistributeLockController {

    private Logger logger = LoggerFactory.getLogger(DistributeLockController.class);

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private DistributedLocker distributedLocker;

    private int counter = 0;

    @RequestMapping("/lock")
    public String distributeLockWithRedisson(int concurrency) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String result = latchWithStress(concurrency);
        stopWatch.stop();
        logger.info("distributeLockWithRedisson->TotalTimeMillis:{}", stopWatch.getTotalTimeMillis());
        return result;
    }

    @RequestMapping("/doLock")
    public String doLock() {
        RLock lock = distributedLocker.lock("spacex:flask");
        try {
            logger.info("[Lock] DistributeLockController#doLock do something after get lock");
        } finally {
            lock.unlock();
        }
        return "done";
    }

    private String latchWithStress(Integer concurrency) {
        int latchCount = 500;
        if (concurrency != null) {
            latchCount = concurrency;
        }
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(latchCount);
        ExecutorService exec = Executors.newFixedThreadPool(latchCount);

        for (int i = 0; i < latchCount; i++) {
            exec.submit(() -> {
                try {
                    await(startLatch);
                    doSafeJob();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        startLatch.countDown();
        await(endLatch);
        exec.shutdown();
        logger.info("[Lock] Finished now,result={}", counter);

        return String.valueOf(counter);

    }

    private String doSafeJob() {
        RLock lock = redissonClient.getLock("flask:rocket:counter");
        int result = 0;
        try {
            lock.lock();
            logger.info("[Lock] Do something after get lock");

            // do distributed task here
            result = adder(1);
            logger.info("[Task] doing safe distribute task now and result:{}", result);

        } finally {
            lock.unlock();
        }
        return String.valueOf(result);
    }

    private String doUnsafeJob() {
        int result = adder(1);
        logger.info("[Unsafe Task] doing task now and result:{}", result);
        return String.valueOf(result);
    }

    private void await(CountDownLatch latch) {
        try {
            if (latch == null) {
                return;
            }
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int adder(int delta) {
        counter = counter + delta;
        return counter;
    }
}
