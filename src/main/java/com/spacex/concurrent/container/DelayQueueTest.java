package com.spacex.concurrent.container;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DelayQueue<DelayTask> queue = new DelayQueue<>();
        queue.add(new DelayTask(5000));//get it after 5 seconds
        queue.add(new DelayTask((2000)));
        queue.add(new DelayTask((3000)));

        System.out.println(String.format("[DelayTask] - %s", LocalDateTime.now()));
        while (true) {
            if (queue.isEmpty()) {
                System.out.println("All Task were finished!");
                break;
            }

            DelayTask task = null;
            try {
                task = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("[DelayTask] %s - time:%s", task, LocalDateTime.now()));
        }
    }

    private static class DelayTask implements Delayed {

        private long delay;
        private long expire;

        public DelayTask(long delay) {
            this.delay = delay;
            expire = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return expire - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed object) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - object.getDelay(TimeUnit.SECONDS));
        }

        @Override
        public String toString() {
            return "DelayTask{" +
                    "delay=" + delay +
                    ", expire=" + expire +
                    '}';
        }
    }
}
