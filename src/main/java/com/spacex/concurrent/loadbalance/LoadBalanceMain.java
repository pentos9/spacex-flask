package com.spacex.concurrent.loadbalance;

public class LoadBalanceMain {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        roundRobin();
    }

    public static void roundRobin() {
        for (int i = 0; i < 100; i++) {
            String serverIp = RoundRobin.getServer();
            System.out.println(String.format("index:%s, %s", i, serverIp));
        }
    }
}
