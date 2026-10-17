/*
# Last Stone Weight — Max Heap

## Problem
Given an array of stone weights, repeatedly select the two heaviest stones and smash them together.

- If both stones have the same weight, both are destroyed.
- If their weights differ, the lighter stone is destroyed, and the heavier stone is replaced by the difference between their weights.
- Continue until at most one stone remains.

Return the remaining stone's weight, or `0` if no stones remain.

## Approach: Max Heap

Use a Max Heap to efficiently retrieve the two heaviest stones at every step.

1. Insert all stone weights into a Max Heap.
2. While the heap contains at least two stones:
   - Remove the largest stone using `poll()`.
   - Remove the second-largest stone using `poll()` again.
   - Calculate the difference between their weights.
   - If the difference is positive, insert it back into the heap.
3. If the heap becomes empty, return `0`. Otherwise, return the remaining stone's weight.

## Why a Max Heap?

Java's default `PriorityQueue` is a Min Heap, which returns the smallest element first. However, this problem requires the two largest stones in every round.

Using `Collections.reverseOrder()` makes the `PriorityQueue` behave as a Max Heap.

The first `poll()` returns the largest stone. Once it is removed, the second `poll()` returns the next-largest stone. The heap automatically restores its ordering after each removal or insertion, so we never need to sort the stones again.

## Java Implementation

```java
*/
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap =
            new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            heap.offer(stone);
        }

        while (heap.size() > 1) {
            int stone1 = heap.poll();
            int stone2 = heap.poll();

            int stoneValue = Math.abs(stone1 - stone2);

            if (stoneValue > 0) {
                heap.offer(stoneValue);
            }
        }

        if (heap.size() == 0) {
            return 0;
        } else {
            return heap.poll();
        }
    }
}
/*
```

## Complexity Analysis

Let `n` be the number of stones.

- **Building the heap:** `O(n log n)` using repeated insertions.
- **Processing stones:** At most `n - 1` smashing rounds. Each round performs up to two removals and one insertion, each costing `O(log n)`.
- **Overall time:** `O(n log n)`.
- **Space:** `O(n)` for the heap.

## Key Takeaways

- Use a Max Heap when repeatedly retrieving the largest available element.
- Use `poll()` twice to obtain the two largest stones.
- Reinsert the difference only when it is greater than zero.
- Continue while `heap.size() > 1`.
- A heap maintains priority ordering without fully sorting all elements after every operation.

**Pattern to remember:** When a problem repeatedly asks for the current largest or smallest element and modifies the collection after each selection, consider a Max Heap or Min Heap rather than repeatedly sorting the collection.
*/