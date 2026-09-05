package com.hdfclife.queue;

import com.hdfclife.exception.QueueEmptyException;
import com.hdfclife.exception.QueueFullException;

public class CircularClaimQueue {
    private final int[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public CircularClaimQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        data = new int[capacity];
    }

    public void enqueue(int value) {
        if (isFull()) throw new QueueFullException("Queue is full");
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new QueueEmptyException("Queue is empty");
        int value = data[head];
        head = (head + 1) % data.length;
        size--;
        return value;
    }

    public boolean isFull() { return size == data.length; }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public int[] toArray() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[(head + i) % data.length];
        }
        return result;
    }
}
