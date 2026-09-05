package com.hdfclife.thread;

public class SeedRunnable implements Runnable {
    private final int[] values;

    public SeedRunnable(int[] values) {
        this.values = values;
    }

    @Override
    public void run() {
        long sum = 0;
        for (int value : values) sum += value;
    }
}
