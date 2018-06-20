package com.spacex.concurrent.container;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
    private volatile Map<K, V> initialMap;

    private ReentrantLock lock = new ReentrantLock();

    public CopyOnWriteMap() {
        initialMap = new HashMap<>();
    }

    public CopyOnWriteMap(int capacity) {
        initialMap = new HashMap<>(capacity);
    }

    @Override
    public int size() {
        return initialMap.size();
    }

    @Override
    public boolean isEmpty() {
        return initialMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return initialMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return initialMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return initialMap.get(key);
    }

    @Override
    public V put(K key, V value) {

        try {
            lock.lock();
            Map<K, V> newMap = new HashMap<K, V>(initialMap);
            V val = newMap.put(key, value);
            initialMap = newMap;
            return val;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            lock.lock();
            return initialMap.remove(key);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        try {
            lock.lock();
            Map<K, V> newMap = new HashMap<>(initialMap);
            newMap.putAll(map);
            initialMap = newMap;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void clear() {
        try {
            lock.lock();
            initialMap.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        return initialMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return initialMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return initialMap.entrySet();
    }

    @Override
    public String toString() {
        return initialMap.toString();
    }
}