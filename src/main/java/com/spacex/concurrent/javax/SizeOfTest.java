package com.spacex.concurrent.javax;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SizeOfTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        printSize(new ConcurrentHashMap<>());

    }

    private static void printSize(Object object) {
        System.out.println(String.format("class:%s", object.getClass()));
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Instagram", "Instagram");
        concurrentMap.put("Facebook", "Facebook");
        concurrentMap.put("Whatsapp", "Whatsapp");
        concurrentMap.put("Telegram", "Telegram");
        concurrentMap.forEach((key, value) -> {
            System.out.println(key + "->" + value);
        });

        Map<String, String> map = concurrentMap;
        map.forEach((key, value) -> {
            System.out.println("map:" + key + "->" + value);
        });

    }
}
