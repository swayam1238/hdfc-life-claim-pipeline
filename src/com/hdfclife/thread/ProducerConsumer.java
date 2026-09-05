package com.hdfclife.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public final class ProducerConsumer {
    private ProducerConsumer() {}

    public static List<Integer> runDemo(int[] values) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        List<Integer> taken = new ArrayList<>();

        Thread producer = new Thread(() -> {
            try {
                for (int value : values) {
                    queue.put(value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "claim-producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < values.length; i++) {
                    taken.add(queue.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "claim-consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        return taken;
    }
}
