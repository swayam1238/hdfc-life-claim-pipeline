package com.hdfclife.list;

public class ClaimNode {
    public int amount;
    public ClaimNode next;

    public ClaimNode(int amount, ClaimNode next) {
        this.amount = amount;
        this.next = next;
    }
}
