package com.spacex.concurrent.reference;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        stamp();
    }

    public static void run() {
        AtomicReference<String> atomicReference = new AtomicReference<>("old-o");
        String initialValue = atomicReference.get();
        String targetValue = "target";
        atomicReference.compareAndSet(initialValue, targetValue);
        System.out.println(atomicReference.get());
    }

    public static void stamp() {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 0);
        int stamp = atomicStampedReference.getStamp();
        Integer reference = atomicStampedReference.getReference();
        Thread worker1 = new Thread(() -> {
            System.out.println("[Worker-1] reference:" + reference + ",stamp:" + stamp);
            boolean isSet = atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1);
            System.out.println(String.format("[Worker-1] isSet:%s", isSet));
        });

        Thread worker2 = new Thread(() -> {
            System.out.println("[Worker-2] reference:" + reference + ",stamp:" + stamp);
            boolean isSet = atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1);
            System.out.println(String.format("[Worker-2] isSet:%s", isSet));
        });

        worker1.start();
        try {
            worker1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker2.start();
        try {
            worker1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }
}
