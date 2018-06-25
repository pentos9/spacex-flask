package com.spacex.concurrent.countdown.signal;

public class ObjectFactory {
    private volatile Object object;

    public ObjectFactory() {
        this.object = object;
    }

    public Object getInstance() {
        if (object == null) {
            synchronized (this) {
                object = new Object();
            }
        }
        return object;
    }
}
