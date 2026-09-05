package com.hdfclife.queue;

import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;

import java.util.PriorityQueue;

public final class ClaimPriorityDesk {
    private ClaimPriorityDesk() {}

    public static PriorityQueue<Claim> createDesk() {
        return new PriorityQueue<>((a, b) -> {
            int urgencyCompare = Integer.compare(rank(b.getUrgency()), rank(a.getUrgency()));
            if (urgencyCompare != 0) return urgencyCompare;
            return Integer.compare(b.getAmount(), a.getAmount());
        });
    }

    private static int rank(Urgency urgency) {
        return switch (urgency) {
            case HIGH -> 3;
            case MEDIUM -> 2;
            case LOW -> 1;
        };
    }
}
