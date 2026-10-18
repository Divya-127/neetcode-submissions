/*
## Kth Largest Element in an Array — LeetCode 215

### Approach: Min Heap of Size K

**Core idea:** Maintain a Min Heap containing only the `k` largest elements encountered so far. The smallest element among these `k` elements is the kth largest element overall.

### Why a Min Heap?

A Min Heap gives us access to the smallest element in `O(1)` time using `peek()`.

- Insert each number into the Min Heap.
- If the heap size exceeds `k`, remove the smallest element using `poll()`.
- This ensures that the heap retains only the `k` largest elements processed so far.
- After processing all numbers, the root (`peek()`) is the kth largest element.

**Example:**

`nums = [3, 2, 1, 5, 6, 4], k = 2`

The heap retains the two largest elements: `5` and `6`.

The root is `5`, which is the second-largest element.

### Java Implementation

```java
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
/*
```

### Complexity Analysis

- **Time Complexity:** `O(n log k)` — Each of the `n` elements is inserted into the heap, and the heap never exceeds `k + 1` elements.
- **Auxiliary Space Complexity:** `O(k)` — The heap retains at most `k` elements.

### Key Takeaway

To find the **kth largest element**, use a Min Heap of size `k`. To find the **kth smallest element**, use a Max Heap of size `k`.

The heap does not need to sort all elements. It efficiently discards elements that cannot belong to the final top `k`.

**Pattern:** Top K Elements → Bounded Heap → Root contains the kth element.
*/