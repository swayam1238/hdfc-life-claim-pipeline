package com.hdfclife.list;

public final class ListReverser {
    private ListReverser() {}

    public static ClaimLinkedList iterative(ClaimLinkedList original) {
        ClaimLinkedList result = new ClaimLinkedList();
        ClaimNode current = original.getHead();

        ClaimNode previous = null;
        ClaimNode head = current;
        while (current != null) {
            ClaimNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;

        ClaimNode walk = head;
        while (walk != null) {
            result.addLast(walk.amount);
            walk = walk.next;
        }

        // Restore the original list by reversing the temporary links.
        previous = null;
        current = head;
        while (current != null) {
            ClaimNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return result;
    }

    public static ClaimLinkedList recursive(ClaimLinkedList original) {
        ClaimNode reversedHead = reverseRecursive(original.getHead(), null);
        ClaimLinkedList result = new ClaimLinkedList();
        ClaimNode walk = reversedHead;
        while (walk != null) {
            result.addLast(walk.amount);
            walk = walk.next;
        }

        // Restore original.
        reverseRecursive(reversedHead, null);
        return result;
    }

    private static ClaimNode reverseRecursive(ClaimNode current, ClaimNode previous) {
        if (current == null) return previous;
        ClaimNode next = current.next;
        current.next = previous;
        return reverseRecursive(next, current);
    }
}
