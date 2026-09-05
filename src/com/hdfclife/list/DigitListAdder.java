package com.hdfclife.list;

public final class DigitListAdder {
    private DigitListAdder() {}

    public static ClaimLinkedList add(ClaimLinkedList a, ClaimLinkedList b) {
        ClaimLinkedList result = new ClaimLinkedList();
        ClaimNode p = a.getHead();
        ClaimNode q = b.getHead();
        int carry = 0;

        while (p != null || q != null || carry != 0) {
            int x = p == null ? 0 : p.amount;
            int y = q == null ? 0 : q.amount;
            int sum = x + y + carry;

            result.addLast(sum % 10);
            carry = sum / 10;

            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        return result;
    }

    public static ClaimLinkedList digitsOf(int number) {
        ClaimLinkedList result = new ClaimLinkedList();
        if (number == 0) {
            result.addLast(0);
            return result;
        }
        while (number > 0) {
            result.addLast(number % 10);
            number /= 10;
        }
        return result;
    }
}
