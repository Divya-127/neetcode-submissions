/*
# LeetCode 295 — Find Median from Data Stream

## Problem Statement

Design a data structure that supports two operations:

- `addNum(int num)`: Add an integer to the data stream.
- `findMedian()`: Return the median of all numbers added so far.

The median is:
- The middle element when the number of elements is odd.
- The average of the two middle elements when the number of elements is even.

A straightforward approach would store all numbers and sort them whenever the median is requested. However, sorting repeatedly is inefficient.

## Approach: Two Heaps

Use two priority queues to maintain the lower and upper halves of the data stream.

### 1. Max-Heap — `lower`

```java
lower = new PriorityQueue<>(Collections.reverseOrder());
```

Stores the smaller half of the numbers.

- The largest element in the lower half is always at the root.
- `lower.peek()` gives the maximum of the lower half.

### 2. Min-Heap — `upper`

```java
upper = new PriorityQueue<>();
```

Stores the larger half of the numbers.

- The smallest element in the upper half is always at the root.
- `upper.peek()` gives the minimum of the upper half.

### Important Invariants

The algorithm maintains two properties:

**Ordering invariant**

Every element in `lower` is less than or equal to every element in `upper`.

```text
lower.peek() <= upper.peek()
```

Whenever both heaps are nonempty, the root of `lower` is the largest value in the lower half, and the root of `upper` is the smallest value in the upper half.

**Balance invariant**

The size difference between the heaps is at most one, and `lower` is allowed to contain one extra element.

```text
lower.size() == upper.size()
OR
lower.size() == upper.size() + 1
```

These invariants allow us to find the median without sorting the entire collection.

## Implementation

### 1. Initialization

```java
public MedianFinder() {
    lower = new PriorityQueue<>(Collections.reverseOrder());
    upper = new PriorityQueue<>();
}
```

Initialize a max-heap for the lower half and a min-heap for the upper half.

### 2. Adding a Number — `addNum()`

```java
public void addNum(int num) {
    lower.add(num);
    upper.offer(lower.poll());

    if (upper.size() > lower.size()) {
        lower.offer(upper.poll());
    }
}
```

The insertion process has three steps.

**Step 1: Insert into `lower`.**

```java
lower.add(num);
```

Temporarily add the incoming number to the lower half.

**Step 2: Move the maximum of `lower` to `upper`.**

```java
upper.offer(lower.poll());
```

The max-heap removes its largest element, and the min-heap receives that value.

This transfer ensures that the element moved to `upper` is at least as large as every element remaining in `lower`.

**Step 3: Rebalance if necessary.**

```java
if (upper.size() > lower.size()) {
    lower.offer(upper.poll());
}
```

If `upper` becomes larger than `lower`, move the minimum element of `upper` back to `lower`.

After these steps:
- The ordering invariant is preserved.
- `lower` contains either the same number of elements as `upper` or exactly one more.

This insertion strategy avoids manually deciding which heap should receive the new number. The roots and the balancing steps handle placement automatically.

### 3. Finding the Median — `findMedian()`

```java
public double findMedian() {
    if (lower.size() > upper.size()) {
        return lower.peek();
    }

    return ((double) lower.peek() + upper.peek()) / 2.0;
}
```

There are two cases.

**Case 1: Odd number of elements**

Because `lower` has one extra element, its root is the middle element.

```java
return lower.peek();
```

**Case 2: Even number of elements**

Both heaps have equal sizes. The two middle elements are the maximum of `lower` and the minimum of `upper`.

```java
return ((double) lower.peek() + upper.peek()) / 2.0;
```

Cast to `double` before addition to avoid integer overflow, and use floating-point arithmetic so medians such as `2.5` are preserved.

## Dry Run

Insert the numbers `5, 2, 8, 3`.

| Operation | `lower` root (max) | `upper` root (min) | Median |
|---|---:|---:|---:|
| Add 5 | 5 | — | 5.0 |
| Add 2 | 2 | 5 | 3.5 |
| Add 8 | 5 | 8 | 5.0 |
| Add 3 | 3 | 5 | 4.0 |

The heaps do not need to be fully sorted. They only need to maintain their ordering relative to each other and their size balance.

## Complete Java Solution

```java
import java.util.*;
*/
class MedianFinder {
    PriorityQueue<Integer> lower;
    PriorityQueue<Integer> upper;

    public MedianFinder() {
        lower = new PriorityQueue<>(Collections.reverseOrder());
        upper = new PriorityQueue<>();
    }

    public void addNum(int num) {
        lower.add(num);
        upper.offer(lower.poll());

        if (upper.size() > lower.size()) {
            lower.offer(upper.poll());
        }
    }

    public double findMedian() {
        if (lower.size() > upper.size()) {
            return lower.peek();
        }

        return ((double) lower.peek() + upper.peek()) / 2.0;
    }
}
/*
```

## Complexity Analysis

Let \(n\) be the number of elements currently stored.

| Operation | Time Complexity | Explanation |
|---|---|---|
| Constructor | \(O(1)\) | Initializes two empty heaps. |
| `addNum()` | \(O(\log n)\) | Performs a constant number of heap insertions and removals. |
| `findMedian()` | \(O(1)\) | Reads the roots of the heaps without sorting. |
| Space | \(O(n)\) | Both heaps collectively store all inserted numbers. |

## Patterns Learned

- **Two-heap technique:** Maintain the lower and upper halves of an ordered data stream.
- **Max-heap + min-heap:** Access both middle elements in constant time.
- **Heap balancing:** Restore size invariants by moving elements between heap roots.
- **Streaming algorithms:** Process numbers as they arrive without repeatedly sorting the entire collection.
- **Invariant-based design:** Maintain ordering and balance after every insertion so that median retrieval remains simple.

### Key Takeaway

The median can be maintained dynamically without sorting the entire data stream after each insertion. A max-heap stores the lower half, a min-heap stores the upper half, and a constant number of heap operations keep the two halves balanced.

**The core idea is to maintain the right two middle candidates at the heap roots, rather than keeping every number globally sorted.**
*/