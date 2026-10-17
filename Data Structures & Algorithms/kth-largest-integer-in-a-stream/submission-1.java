/*
# Kth Largest Element in a Stream — Min Heap of Size K

## Problem
Design a class that maintains a stream of integers and returns the kth largest element after each new number is added.

## Approach: Min Heap of Size K

Maintain a Min Heap containing only the **k largest elements** seen so far.

1. Insert each incoming number into the Min Heap.
2. If the heap size exceeds `k`, remove the smallest element using `poll()`.
3. The heap now contains at most the `k` largest elements seen so far.
4. The root, returned by `peek()`, is the smallest among those retained elements — therefore, it is the **kth largest element** once at least `k` elements have been processed.

The same helper method is used by the constructor and `add()`, avoiding duplicate logic.

## Why a Min Heap instead of a Max Heap?

The goal is to find the kth largest element, not to sort all the elements.

A Min Heap of size `k` makes the root the kth largest element because it retains the largest `k` values and exposes the smallest among them.

For example, with `k = 3`, suppose the stream contains:

`[3, 2, 1, 5, 6, 4]`

The final heap retains the three largest elements: `{4, 5, 6}`. Its root is `4`, the third-largest element.

A Max Heap would expose the largest element first. To obtain the kth largest element with a Max Heap, we would typically need to remove the maximum `k - 1` times before reaching the answer. That is unnecessary when the stream updates incrementally.

## Min Heap vs. Max Heap: When to Use Which?

| Requirement | Recommended structure | Reason |
|---|---|---|
| Kth largest element in a stream | Min Heap of size `k` | Root is the kth largest |
| Kth smallest element in a stream | Max Heap of size `k` | Root is the kth smallest |
| K largest elements | Min Heap of size `k` | Root lets us discard the smallest retained candidate |
| K smallest elements | Max Heap of size `k` | Root lets us discard the largest retained candidate |
| Repeatedly retrieve the global minimum | Min Heap | Minimum is at the root |
| Repeatedly retrieve the global maximum | Max Heap | Maximum is at the root |
| Process elements by priority | Min or Max Heap | Choose according to the required priority order |

**Key principle:** When retaining the largest `k` elements, use a Min Heap so the weakest retained candidate is easy to remove. When retaining the smallest `k` elements, use a Max Heap for the same reason.

## Java Implementation

```java
*/
class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();

        for (int num : nums) {
            addToHeap(num);
        }
    }

    public int add(int val) {
        addToHeap(val);
        return heap.peek();
    }

    private void addToHeap(int val) {
        heap.offer(val);

        if (heap.size() > k) {
            heap.poll();
        }
    }
}
/*
```

Java's `PriorityQueue<Integer>` is a Min Heap by default.

## Complexity Analysis

Let `n` be the number of initial elements and `k` the requested rank.

- **Constructor:** `O(n log k)` — each insertion or removal costs at most `O(log k)`.
- **Each `add()`:** `O(log k)` — insert the new value and, if needed, remove the minimum.
- **Space:** `O(k)` — the heap never contains more than `k` elements after processing each value.

## Important Edge Cases

- **Duplicates:** Count as separate elements. For `[5, 5, 4]` with `k = 2`, the answer is `5`.
- **Negative numbers:** Work naturally with Java's default integer ordering.
- **Fewer than `k` values initially:** The heap contains fewer than `k` elements until more values arrive. The problem guarantees that `add()` is called when at least `k` stream elements exist overall.

## Pattern to Remember

For streaming Top K problems, don't automatically sort the entire input. Keep a heap of size `k` and discard the weakest candidate whenever the heap grows beyond `k`.

This reduces storage from `O(n)` to `O(k)` and gives `O(log k)` updates, making it suitable for large or continuously growing streams.
*/