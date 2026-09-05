package com.hdfclife.list;

public final class CycleDetector {
    private CycleDetector() {}

    public static boolean hasCycle(ClaimLinkedList list) {
        ClaimNode slow = list.getHead();
        ClaimNode fast = list.getHead();

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static ClaimNode cycleStart(ClaimLinkedList list) {
        ClaimNode slow = list.getHead();
        ClaimNode fast = list.getHead();

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ClaimNode pointer = list.getHead();
                while (pointer != slow) {
                    pointer = pointer.next;
                    slow = slow.next;
                }
                return pointer;
            }
        }
        return null;
    }
}
