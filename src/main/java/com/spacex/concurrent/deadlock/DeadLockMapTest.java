package com.spacex.concurrent.deadlock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeadLockMapTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final Map<String, String> map = new HashMap<>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
