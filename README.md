# HDFC Life Claim Pipeline

Plain Java implementation of the HDFC Life Claim Pipeline assignment.

## Requirements

- Java 17 or newer
- No Spring
- No Java Streams for the algorithms
- Custom singly linked list and linked-list stack
- `java.util.PriorityQueue` for the priority queue
- `ArrayBlockingQueue` for producer-consumer
- `ExecutorService`, `Future`, and `CompletableFuture` for the thread section

## Compile and run

From the project root:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.hdfclife.Main
```

On Windows PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out com.hdfclife.Main
```

## Complexity

| Algorithm / Operation | Time | Extra Space |
|---|---:|---:|
| Linked-list `addLast` | O(1) | O(1) |
| Linked-list `addFirst` | O(1) | O(1) |
| Linked-list `insertAt` | O(n) worst case | O(1) |
| Linked-list `deleteAt` | O(n) worst case | O(1) |
| Linked-list `nodeAt` | O(n) | O(1) |
| Linked-list `toArray` | O(n) | O(n) for output |
| Iterative reverse | O(n) | O(1) |
| Recursive reverse | O(n) | O(n) call stack |
| Cycle detection | O(n) | O(1) |
| Middle using slow/fast | O(n) | O(1) |
| Add two digit lists | O(max(n,m)) | O(max(n,m)) for result |
| Stack push/pop | O(1) | O(1) |
| Circular queue enqueue/dequeue | O(1) | O(1) besides array |
| BFS | O(V + E) | O(V) |
| PriorityQueue offer/poll | O(log n) | O(n) queue storage |

## Linked list vs fixed array

A linked list is useful when the number of claims can grow or shrink frequently and insertions/deletions are needed without shifting a large contiguous block. It also avoids requiring one large contiguous memory region. The trade-off is slower indexed access and extra memory for node references. A fixed array is preferable when the size is known or bounded, fast indexed access matters, and cache-friendly contiguous storage is valuable. For a queue with a known small capacity, a circular array is especially efficient.

## Expected key results

- Seed sum: `140000`
- Middle: `15000`
- Cycle start after tail -> index 2: `42000`
- `25000 + 18000`: `43000`
- Postfix result: `42000`
- BFS: `MUMBAI, PUNE, DELHI, HYDERABAD, KOLKATA, CHENNAI`
- Priority order: `CLM-03, CLM-01, CLM-05, CLM-02, CLM-04, CLM-06`

## GitHub submission

Create a public repository and push the project:

```bash
git init
git add .
git commit -m "Implement HDFC Life Claim Pipeline"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/hdfc-life-claim-pipeline.git
git push -u origin main
```

Do not commit generated `.class` files or the `out/`, `bin/`, or `target/` directories.
