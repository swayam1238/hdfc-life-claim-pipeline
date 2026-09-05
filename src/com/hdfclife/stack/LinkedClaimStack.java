package com.hdfclife.stack;

import com.hdfclife.exception.StackEmptyException;
import com.hdfclife.list.ClaimNode;

public class LinkedClaimStack implements ClaimStack {
    private ClaimNode top;

    @Override
    public void push(int value) {
        top = new ClaimNode(value, top);
    }

    @Override
    public int pop() {
        if (isEmpty()) throw new StackEmptyException("Stack is empty");
        int value = top.amount;
        top = top.next;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}
