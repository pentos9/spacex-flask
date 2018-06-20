package com.spacex.test;

import com.google.common.collect.Maps;
import com.spacex.service.BlackListServiceImpl;

import java.util.Map;

public class BlackListServiceTest {
    public static void main(String[] args) {
        run();
    }

    public static void init() {
        Map<String, Boolean> initBlackList = Maps.newConcurrentMap();
        initBlackList.put("134590", Boolean.TRUE);
        initBlackList.put("398653", Boolean.TRUE);
        initBlackList.put("965324", Boolean.TRUE);
        BlackListServiceImpl.addBlackList(initBlackList);
    }

    public static void run() {
        init();
        Boolean isInBlacklist = BlackListServiceImpl.isBlackList("97892785");
        System.out.println(String.format("isInBlacklist->%s", isInBlacklist));

        isInBlacklist = BlackListServiceImpl.isBlackList("134590");
        System.out.println(String.format("isInBlacklist->%s", isInBlacklist));

        System.out.println(String.format("blackList->%s", BlackListServiceImpl.getBlackList()));

    }
}
