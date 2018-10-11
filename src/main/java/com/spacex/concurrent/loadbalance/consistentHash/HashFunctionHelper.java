package com.spacex.concurrent.loadbalance.consistentHash;

import org.apache.commons.codec.digest.DigestUtils;

public class HashFunctionHelper implements HashFunction {

    @Override
    public int hash(Object object) {
        return DigestUtils.md5Hex(object.toString()).hashCode();
    }
}
