package com.spacex.concurrent.loadbalance.consistentHash;

public interface HashFunction {
    int hash(Object object);
}
