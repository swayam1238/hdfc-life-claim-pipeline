package com.hdfclife;

import com.hdfclife.exception.*;
import com.hdfclife.list.*;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;
import com.hdfclife.queue.*;
import com.hdfclife.stack.*;
import com.hdfclife.thread.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int[] SEED = {25000, 18000, 42000, 15000, 31000, 9000};

    public static void main(String[] args) throws Exception {
        ClaimLinkedList seed = buildSeed();

        // 1. Seed list
        System.out.print("Seed list -> ");
        print(seed);

        // 2. Insert on a copy
        ClaimLinkedList insertCopy = seed.copy();
        insertCopy.insertAt(2, 22000);
        System.out.print("After insertAt(2, 22000) -> ");
        print(insertCopy);

        // 3. Delete on another copy
        ClaimLinkedList deleteCopy = insertCopy.copy();
        deleteCopy.deleteAt(2);
        System.out.print("After deleteAt(2) -> ");
        print(deleteCopy);

        // 4-5. Reverse on copies
        ClaimLinkedList reversedIterative = ListReverser.iterative(seed.copy());
        System.out.print("Reverse iterative -> ");
        print(reversedIterative);

        ClaimLinkedList reversedRecursive = ListReverser.recursive(seed.copy());
        System.out.print("Reverse recursive -> ");
        print(reversedRecursive);

        // 6. Middle
        System.out.println("Middle of seed -> " + middle(seed));

        // 7. No cycle
        ClaimLinkedList cycleCopy = seed.copy();
        System.out.println("hasCycle on seed -> " + CycleDetector.hasCycle(cycleCopy));

        // 8-9. Create cycle: tail -> index 2
        cycleCopy.setTailNext(cycleCopy.nodeAt(2));
        System.out.println("hasCycle after linking tail to index 2 -> " +
                CycleDetector.hasCycle(cycleCopy));

        ClaimNode start = CycleDetector.cycleStart(cycleCopy);
        System.out.println("Cycle start amount -> " + start.amount);

        // Break cycle before doing anything else with this copy.
        cycleCopy.setTailNext(null);

        // 10. Add two numbers
        ClaimLinkedList a = DigitListAdder.digitsOf(25000);
        ClaimLinkedList b = DigitListAdder.digitsOf(18000);
        ClaimLinkedList sum = DigitListAdder.add(a, b);
        System.out.print("Add-two-numbers -> ");
        print(sum);

        // 11-13. Parentheses
        System.out.println("Balanced ((TERM)(ULIP)) -> " +
                ParenthesesChecker.isBalanced("((TERM)(ULIP))"));
        System.out.println("Balanced ((TERM)(ULIP) -> " +
                ParenthesesChecker.isBalanced("((TERM)(ULIP)"));
        System.out.println("Balanced ([]) -> " +
                ParenthesesChecker.isBalanced("([])"));

        // 14. Postfix
        System.out.println("Postfix 25000 18000 + 1000 - -> " +
                PostfixEvaluator.evaluate("25000 18000 + 1000 -"));

        // 15-16. Circular queue
        CircularClaimQueue circular = new CircularClaimQueue(4);
        circular.enqueue(25000);
        circular.enqueue(18000);
        circular.enqueue(42000);
        System.out.println("Circular dequeue() -> " + circular.dequeue());
        circular.enqueue(15000);
        circular.enqueue(31000);
        System.out.print("Circular queue after wrap -> ");
        print(circular.toArray());

        // 17. BFS
        System.out.print("BFS from MUMBAI -> ");
        print(BranchBfs.bfsFromMumbai());

        // 18. Priority queue
        Claim[] claims = {
            new Claim("CLM-01", 25000, "HDFC-LIFE-1001", "Anita Sharma", Urgency.HIGH),
            new Claim("CLM-02", 18000, "HDFC-LIFE-1002", "Rahul Mehta", Urgency.MEDIUM),
            new Claim("CLM-03", 42000, "HDFC-LIFE-1005", "Sneha Patel", Urgency.HIGH),
            new Claim("CLM-04", 15000, "HDFC-LIFE-1004", "Vikram Singh", Urgency.LOW),
            new Claim("CLM-05", 31000, "HDFC-LIFE-1001", "Anita Sharma", Urgency.MEDIUM),
            new Claim("CLM-06", 9000, "HDFC-LIFE-1003", "Priya Nair", Urgency.LOW)
        };

        var desk = ClaimPriorityDesk.createDesk();
        for (Claim claim : claims) desk.offer(claim);

        System.out.print("PriorityQueue poll ids -> ");
        boolean first = true;
        while (!desk.isEmpty()) {
            if (!first) System.out.print(", ");
            System.out.print(desk.poll().getId());
            first = false;
        }
        System.out.println();

        // 19-20. Runnable thread state
        Thread worker = new Thread(new SeedRunnable(SEED), "seed-worker");
        System.out.println("Thread state before start -> " + worker.getState());
        worker.start();
        worker.join();
        System.out.println("Thread state after join -> " + worker.getState());

        // 21-22. Callable/Future
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future = executor.submit(new ClaimTotalCallable(SEED));
        System.out.println("Callable Future.get() sum -> " + future.get());
        System.out.println("isDone after get -> " + future.isDone());

        // 23. CompletableFuture (must execute asynchronously)
        CompletableFuture<Integer> asyncSum =
                CompletableFuture.supplyAsync(() -> total(SEED), executor);
        System.out.println("CompletableFuture.supplyAsync sum -> " + asyncSum.get());

        // 24. cancel(true) on a long-running callable
        Future<Integer> cancellable =
                executor.submit(new ClaimTotalCallable(SEED, true));
        Thread.sleep(100);
        System.out.println("Cancelled future -> " + cancellable.cancel(true));

        // 25. Daemon
        Thread daemon = new Thread(() -> {}, "daemon-worker");
        daemon.setDaemon(true);
        System.out.println("Daemon flag -> " + daemon.isDaemon());

        // 26. Producer-consumer
        List<Integer> taken = ProducerConsumer.runDemo(new int[]{25000, 18000, 42000});
        System.out.print("Producer-consumer takes -> ");
        print(taken.stream().mapToInt(Integer::intValue).toArray());

        executor.shutdownNow();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        // 27-29. Required exception demonstrations
        try {
            seed.nodeAt(99);
        } catch (PipelineException e) {
            System.out.println("Caught message for invalid index 99 -> " + e.getMessage());
        }

        try {
            new ArrayClaimStack(2).pop();
        } catch (PipelineException e) {
            System.out.println("Caught message for empty stack pop -> " + e.getMessage());
        }

        try {
            new CircularClaimQueue(4).dequeue();
        } catch (PipelineException e) {
            System.out.println("Caught message for empty queue dequeue -> " + e.getMessage());
        }
    }

    private static ClaimLinkedList buildSeed() {
        ClaimLinkedList list = new ClaimLinkedList();
        for (int amount : SEED) list.addLast(amount);
        return list;
    }

    private static int middle(ClaimLinkedList list) {
        ClaimNode slow = list.getHead();
        ClaimNode fast = list.getHead();

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.amount;
    }

    private static int total(int[] values) {
        int sum = 0;
        for (int value : values) sum += value;
        return sum;
    }

    private static void print(ClaimLinkedList list) {
        print(list.toArray());
    }

    private static void print(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(values[i]);
        }
        System.out.println();
    }

    private static void print(String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(values[i]);
        }
        System.out.println();
    }

    private static void print(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(values.get(i));
        }
        System.out.println();
    }
}
