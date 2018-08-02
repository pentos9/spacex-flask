package com.spacex.concurrent.exchanger;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerTest {

    private static class Producer implements Runnable {
        private List<String> buffer;
        private Exchanger<List<String>> exchanger;

        public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(String.format("[Producer-%s produce", i));
                for (int j = 1; j <= 3; j++) {
                    buffer.add(String.format("Producer Buffer: %s | %s<-->%s", Thread.currentThread().getName(), i, j));
                }

                try {
                    exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static class Consumer implements Runnable {
        private List<String> buffer;
        private Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {

                try {
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("[Consumer] " + i + " Times Extract.");

                for (int j = 1; j <= 3; j++) {
                    System.out.println(String.format("[Buffer Consumer] %s extract result-> %s", Thread.currentThread().getName(), buffer.get(0)));
                    buffer.remove(0);
                }
            }


        }
    }

    public static void main(String[] args) {
        List<String> producerBuffer = Lists.newArrayList();
        List<String> consumerBuffer = Lists.newArrayList();
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
        Thread producer = new Thread(new Producer(producerBuffer, exchanger));
        Thread consumer = new Thread(new Consumer(consumerBuffer, exchanger));
        producer.start();
        consumer.start();
    }
}
