package com.spacex.concurrent.forkjoin;

public class CustomForkJoinTask extends java.util.concurrent.ForkJoinTask {
    @Override
    public Object getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Object value) {

    }

    @Override
    protected boolean exec() {
        System.out.println("executing exec method");
        return true;
    }
}
