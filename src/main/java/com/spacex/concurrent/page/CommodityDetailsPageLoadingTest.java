package com.spacex.concurrent.page;

import com.google.common.collect.Lists;
import com.spacex.concurrent.page.task.CommentListLoadingTask;
import com.spacex.concurrent.page.task.CommodityBaseInfoLoadingTask;
import com.spacex.concurrent.page.task.CommodityRecommendListLoadingTask;
import com.spacex.concurrent.page.task.ExceptionLoadingTask;
import com.spacex.concurrent.page.task.AbstractLoadingTask;
import com.spacex.concurrent.page.task.OrderInfoLoadingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CommodityDetailsPageLoadingTest {

    private static Logger logger = LoggerFactory.getLogger(CommodityDetailsPageLoadingTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int taskCount = 5;
        CountDownLatch latch = new CountDownLatch(taskCount);
        List<AbstractLoadingTask> loadingTasks = initLoadingTasks(latch);

        List<Object> commodityDetailsDataList = Lists.newArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        for (AbstractLoadingTask task : loadingTasks) {
            Future<String> future = executorService.submit(task);
            try {
                commodityDetailsDataList.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                logger.info(String.format("Error service:%s, Error message: %s", task.getServiceName(), e));
            }
        }

        await(latch);
        executorService.shutdown();
        commodityDetailsDataList.forEach(System.out::println);

    }

    public static List<AbstractLoadingTask> initLoadingTasks(CountDownLatch latch) {
        AbstractLoadingTask orderInfoLoadingTask = new OrderInfoLoadingTask("OrderInfoService", latch);
        AbstractLoadingTask commodityBaseInfoLoadingTask = new CommodityBaseInfoLoadingTask("CommodityBaseInfoService", latch);
        AbstractLoadingTask commodityRecommendListLoadingTask = new CommodityRecommendListLoadingTask("CommodityRecommendService", latch);
        AbstractLoadingTask commentListLoadingTask = new CommentListLoadingTask("CommentService", latch);
        AbstractLoadingTask exceptionLoadingTask = new ExceptionLoadingTask("ExceptionService", latch);

        List<AbstractLoadingTask> tasks = Arrays.asList(orderInfoLoadingTask, commodityBaseInfoLoadingTask, commodityRecommendListLoadingTask, commentListLoadingTask, exceptionLoadingTask);
        return tasks;
    }

    public static void await(CountDownLatch latch) {
        if (latch != null) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
