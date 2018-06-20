package com.spacex.service;

import com.spacex.concurrent.container.CopyOnWriteMap;

import java.util.Map;

public class BlackListServiceImpl {
    private static CopyOnWriteMap<String, Boolean> blackListMap = new CopyOnWriteMap<>(1000);

    public static boolean isBlackList(String id) {
        return blackListMap.get(id) != null;
    }

    public static void addBlackList(String id) {
        blackListMap.put(id, Boolean.TRUE);
    }

    public static void addBlackList(Map<String, Boolean> ids) {
        blackListMap.putAll(ids);
    }

}
