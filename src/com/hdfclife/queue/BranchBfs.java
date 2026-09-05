package com.hdfclife.queue;

import java.util.HashSet;
import java.util.Set;

public final class BranchBfs {
    private BranchBfs() {}

    private static class Node {
        String city;
        Node next;
        Node(String city) { this.city = city; }
    }

    private static class StringQueue {
        private Node head;
        private Node tail;

        void enqueue(String value) {
            Node n = new Node(value);
            if (tail == null) head = tail = n;
            else {
                tail.next = n;
                tail = n;
            }
        }

        String dequeue() {
            if (head == null) return null;
            String value = head.city;
            head = head.next;
            if (head == null) tail = null;
            return value;
        }

        boolean isEmpty() { return head == null; }
    }

    public static String[] bfsFromMumbai() {
        StringQueue queue = new StringQueue();
        Set<String> visited = new HashSet<>();
        String[] result = new String[5];
        int count = 0;

        // Adjacency in the exact left-to-right order from the assignment.
        String[][] graph = {
            {"MUMBAI", "PUNE", "DELHI"},
            {"PUNE", "HYDERABAD"},
            {"DELHI", "KOLKATA"},
            {"HYDERABAD", "CHENNAI"},
            {"KOLKATA"},
            {"CHENNAI"}
        };

        queue.enqueue("MUMBAI");

        while (!queue.isEmpty()) {
            String city = queue.dequeue();
            if (!visited.add(city)) continue;

            if (count == result.length) {
                String[] expanded = new String[result.length * 2];
                System.arraycopy(result, 0, expanded, 0, result.length);
                result = expanded;
            }
            result[count++] = city;

            for (String[] row : graph) {
                if (row[0].equals(city)) {
                    for (int i = 1; i < row.length; i++) {
                        if (!visited.contains(row[i])) queue.enqueue(row[i]);
                    }
                    break;
                }
            }
        }

        String[] exact = new String[count];
        System.arraycopy(result, 0, exact, 0, count);
        return exact;
    }
}
