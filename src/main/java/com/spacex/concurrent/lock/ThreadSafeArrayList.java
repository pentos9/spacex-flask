package com.spacex.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeArrayList<E> {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final List<E> list = new ArrayList<>();

    public void add(E object) {
        try {
            writeLock.lock();
            list.add(object);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        try {
            readLock.lock();
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

}
