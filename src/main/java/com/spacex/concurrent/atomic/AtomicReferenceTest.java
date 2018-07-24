package com.spacex.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {
        withStampReference();
    }

    public static void run() {
        AtomicReference<Person> p = new AtomicReference<>(new Person(20));
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                p.set(new Person(p.get().getAge() + 10));
                System.out.println("Atomic Check by first thread:" + Thread.currentThread().getName() + "is " + p.get().getAge());
            }
        });


        Thread t2 = new Thread(() -> {
            Person per = p.get();
            for (int j = 1; j < 3; j++) {
                System.err.println(p.get().equals(per) + "_" + per.getAge() + "_" + p.get().getAge());
                p.compareAndSet(per, new Person(p.get().getAge() + 10));
                System.out.println("[t2] Atomic Check by first thread:" + Thread.currentThread().getName() + " is " + p.get().getAge());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value:" + p.get().getAge());
    }

    public static void withStampReference() {

        final AtomicInteger stampHolder = new AtomicInteger(1);
        AtomicStampedReference<Person> s = new AtomicStampedReference<>(new Person(20), stampHolder.get());
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("[T1-WithStampReference] Stamp Value for first thread:" + stampHolder.get());
                s.compareAndSet(s.getReference(), new Person(s.getReference().getAge() + 10), stampHolder.get(), stampHolder.addAndGet(1));
                System.out.println("[T1-WithStampReference] Atomic Check by first thread:" + Thread.currentThread().getName() + " is " + s.getReference().getAge());
            }
        });


        Thread t2 = new Thread(() -> {
            for (int j = 1; j <= 3; j++) {
                System.out.println("[T2-WithStampReference] stamp value for second thread:" + stampHolder.get());
                s.compareAndSet(s.getReference(), new Person(s.getReference().getAge() + 10), stampHolder.get(), stampHolder.addAndGet(1));
                System.out.println("[T2-WithStampReference] Atomic Check by first thread:" + Thread.currentThread().getName() + " is " + s.getReference().getAge());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value:" + s.getReference().getAge());

    }


    private static class Person {
        private int age;

        public Person(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
