package com.spacex.concurrent.mis;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class GuavaToolTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        List<String> musicians = ImmutableList.of("Bach", "Mozart", "Chopin");
        musicians.stream().forEach(System.out::println);

        Map<String, String> map = ImmutableMap.of("Spotify", "Swiss", "Youtobe", "USA");
        map.forEach((key, value) -> {
            System.out.println(String.format("key->%s,value->%s", key, value));
        });

        Map<String, String> wonderfulMap = Maps.newHashMapWithExpectedSize(10);
        wonderfulMap.put("K1", "V1");
        wonderfulMap.put("K2", "V2");
        wonderfulMap.put("K3", "V3");

        wonderfulMap.forEach((key, value) -> {
            System.out.println(String.format("key:%s,value:%s", key, value));
        });
    }
}