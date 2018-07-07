package com.spacex.concurrent.threadlocal.sequence;


public class SequenceNumber {
    private ThreadLocal<Integer> sequenceNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int getNextNumber() {
        sequenceNum.set(sequenceNum.get() + 1);
        return sequenceNum.get();
    }

}
