package com.spacex.concurrent.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoundRobin {
    private static Integer position = 0;

    public static String getServer() {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        String target = null;

        synchronized (position) {
            if (position > serverList.size() - 1) {
                position = 0;
            }
            target = serverList.get(position);
            position++;
        }
        return target;
    }
}
