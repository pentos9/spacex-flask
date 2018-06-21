package com.spacex.concurrent.forkjoin;

import java.util.concurrent.RecursiveAction;

public class CustomRecursiveTask extends RecursiveAction {
    @Override
    protected void compute() {
        System.out.println("Inside compute method");
    }
}
