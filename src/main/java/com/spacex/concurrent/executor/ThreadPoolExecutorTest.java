package com.spacex.concurrent.executor;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(1000);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        long KEEP_ALIVE_TIME = 5;
        int MAXIMUM_POOL_SIZE = 50;
        int CORE_POOL_SIZE = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue);


        threadPoolExecutor.submit(() -> {
            int loop = 0;
            while (loop < 100) {
                logger.info(String.format("[Producer - %s ] Producing %s...", Thread.currentThread().getName(), loop));
                loop++;
                sleep(100L);
            }
        });


        threadPoolExecutor.submit(() -> {
            int loop = 0;
            while (loop < 100) {
                logger.info(String.format("[Consumer - %s ] Consuming %s ...", Thread.currentThread().getName(), loop));
                loop++;
                sleep(100L);
            }
        });

        threadPoolExecutor.shutdown();


    }


    public static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
