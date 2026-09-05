package com.hdfclife.list;

import com.hdfclife.exception.EmptyListException;
import com.hdfclife.exception.InvalidIndexException;

public class ClaimLinkedList {
    private ClaimNode head;
    private ClaimNode tail;
    private int size;

    public void addLast(int amount) {
        ClaimNode node = new ClaimNode(amount, null);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addFirst(int amount) {
        ClaimNode node = new ClaimNode(amount, head);
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    public void insertAt(int index, int amount) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        if (index == 0) {
            addFirst(amount);
            return;
        }
        if (index == size) {
            addLast(amount);
            return;
        }

        ClaimNode prev = nodeAt(index - 1);
        prev.next = new ClaimNode(amount, prev.next);
        size++;
    }

    public int deleteAt(int index) {
        if (size == 0) {
            throw new EmptyListException("Cannot delete from an empty list");
        }
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }

        ClaimNode removed;
        if (index == 0) {
            removed = head;
            head = head.next;
            size--;
            if (size == 0) tail = null;
            removed.next = null;
            return removed.amount;
        }

        ClaimNode prev = nodeAt(index - 1);
        removed = prev.next;
        prev.next = removed.next;
        if (removed == tail) {
            tail = prev;
        }
        removed.next = null;
        size--;
        return removed.amount;
    }

    public ClaimNode nodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        ClaimNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public int[] toArray() {
        int[] result = new int[size];
        ClaimNode current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.amount;
            current = current.next;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public ClaimNode getHead() {
        return head;
    }

    public ClaimNode getTail() {
        return tail;
    }

    public void setTailNext(ClaimNode node) {
        if (tail != null) {
            tail.next = node;
        }
    }

    public ClaimLinkedList copy() {
        ClaimLinkedList copy = new ClaimLinkedList();
        ClaimNode current = head;
        for (int i = 0; i < size; i++) {
            copy.addLast(current.amount);
            current = current.next;
        }
        return copy;
    }

    public void print() {
        int[] values = toArray();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(values[i]);
        }
        System.out.println();
    }
}
