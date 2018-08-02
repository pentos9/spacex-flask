package com.spacex.concurrent.container;

import java.util.LinkedList;
import java.util.Random;

public class DequeTest {

    private static class CustomDeque<T> {
        private LinkedList<T> deque = new LinkedList<>();

        public void addFirst(T e) {
            deque.add(e);
        }

        public void addLast(T e) {
            deque.addLast(e);
        }

        public T getFirst() {
            return deque.getFirst();
        }

        public T getLast() {
            return deque.getLast();
        }

        public void removeFirst() {
            deque.removeFirst();
        }

        public void removeLast() {
            deque.removeLast();
        }

        public int size() {
            return deque.size();
        }

        @Override
        public String toString() {
            return deque.toString();
        }
    }


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        CustomDeque<String> deque = new CustomDeque<>();
        fillDeque(deque, 100);
        System.out.println(deque);
        clear(deque);
        System.out.println(deque);
        fillDeque(deque, 50);
        System.out.println(deque);
        clear(deque);
        System.out.println(deque);
    }

    public static void fillDeque(CustomDeque<String> deque, int number) {
        if (deque == null) {
            return;
        }

        for (int i = 0; i < number; i++) {
            deque.addFirst(String.format("%s", new Random().nextInt(number)));
        }
    }

    public static void clear(CustomDeque deque) {
        if (deque == null) {
            return;
        }

        while (deque.size() != 0) {
            deque.removeFirst();
        }
    }
}
