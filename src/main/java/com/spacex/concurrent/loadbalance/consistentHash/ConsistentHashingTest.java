package com.spacex.concurrent.loadbalance.consistentHash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashingTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final int times = 140;
        List<String> nodes = Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4", "192.168.0.5", "192.168.0.6");
        nodes = new ArrayList<>(nodes);
        ResultMap resultMap = doConsistentHashGet(new HashFunctionHelper(), 10, nodes, times);
        printResultMap(resultMap);

        nodes.add("192.168.0.7");
        ResultMap newResultMap = doConsistentHashGet(new HashFunctionHelper(), 10, nodes, times);
        printResultMap(newResultMap);

    }

    public static ResultMap doConsistentHashGet(HashFunction hashFunction, int virtualNumber, List<String> nodes, int queryTimes) {
        ConsistentHash<String> consistentHash = new ConsistentHash<>(new HashFunctionHelper(), 10, nodes);
        Map<String, Integer> nodeCounterMap = new TreeMap<>();
        Map<String, List<String>> nodeListMap = new TreeMap<>();
        for (int i = 0; i < queryTimes; i++) {
            String node = consistentHash.get(String.valueOf(i));
            if (nodeCounterMap.get(node) == null) {
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(i));
                nodeListMap.put(node, list);
                nodeCounterMap.put(node, 1);
            } else {
                nodeCounterMap.put(node, nodeCounterMap.get(node) + 1);
                List<String> list = nodeListMap.get(node);
                list.add(String.valueOf(i));
                nodeListMap.put(node, list);

            }

        }

        return new ResultMap(nodeCounterMap, nodeListMap);
    }

    private static void printResultMap(ResultMap resultMap) {
        if (resultMap == null) {
            return;
        }

        Map<String, Integer> counterMap = resultMap.getNodeCounterMap();
        Map<String, List<String>> nodeListMap = resultMap.getNodeListMap();

        System.out.println("======Counter======");
        for (Map.Entry<String, Integer> entry : counterMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("======NodeList======");
        for (Map.Entry<String, List<String>> entry : nodeListMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


    }

    private static class ResultMap {
        private Map<String, Integer> nodeCounterMap = new TreeMap<>();
        private Map<String, List<String>> nodeListMap = new TreeMap<>();

        public ResultMap(Map<String, Integer> resultMap, Map<String, List<String>> resultListMap) {
            this.nodeCounterMap = resultMap;
            this.nodeListMap = resultListMap;
        }

        public Map<String, Integer> getNodeCounterMap() {
            return nodeCounterMap;
        }

        public void setNodeCounterMap(Map<String, Integer> nodeCounterMap) {
            this.nodeCounterMap = nodeCounterMap;
        }

        public Map<String, List<String>> getNodeListMap() {
            return nodeListMap;
        }

        public void setNodeListMap(Map<String, List<String>> nodeListMap) {
            this.nodeListMap = nodeListMap;
        }

        @Override
        public String toString() {
            return "ResultMap{" +
                    "nodeCounterMap=" + nodeCounterMap +
                    ", nodeListMap=" + nodeListMap +
                    '}';
        }
    }
}
