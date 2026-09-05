package com.hdfclife.stack;

import com.hdfclife.exception.StackEmptyException;
import com.hdfclife.exception.StackFullException;

public class ArrayClaimStack implements ClaimStack {
    private final int[] data;
    private int top = -1;

    public ArrayClaimStack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        data = new int[capacity];
    }

    @Override
    public void push(int value) {
        if (top == data.length - 1) {
            throw new StackFullException("Stack is full");
        }
        data[++top] = value;
    }

    @Override
    public int pop() {
        if (isEmpty()) throw new StackEmptyException("Stack is empty");
        return data[top--];
    }

    public int peek() {
        if (isEmpty()) throw new StackEmptyException("Stack is empty");
        return data[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
