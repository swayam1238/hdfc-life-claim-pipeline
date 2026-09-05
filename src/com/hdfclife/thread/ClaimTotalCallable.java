package com.hdfclife.thread;

import java.util.concurrent.Callable;

public class ClaimTotalCallable implements Callable<Integer> {
    private final int[] values;
    private final boolean sleepBeforeReturn;

    public ClaimTotalCallable(int[] values) {
        this(values, false);
    }

    public ClaimTotalCallable(int[] values, boolean sleepBeforeReturn) {
        this.values = values;
        this.sleepBeforeReturn = sleepBeforeReturn;
    }

    @Override
    public Integer call() throws Exception {
        if (sleepBeforeReturn) {
            Thread.sleep(30000);
        }
        int sum = 0;
        for (int value : values) sum += value;
        return sum;
    }
}
