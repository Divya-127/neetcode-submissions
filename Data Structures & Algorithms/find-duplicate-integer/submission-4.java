/*
## Find the Duplicate Number — LeetCode 287

### Approach: Floyd's Tortoise and Hare Algorithm

The problem provides an array of `n + 1` integers, where each integer is in the range `[1, n]`. At least one number is repeated.

The objective is to find a duplicate without modifying the array and using only constant extra space.

### 1. Treat the Array as a Linked List

We can interpret the array as an implicit linked list.

- Each array index represents a node.
- The value at an index represents the next pointer.
- `nums[i]` tells us which index to visit next.

For example:

```text
nums = [1, 3, 4, 2, 2]

0 → 1 → 3 → 2 → 4
        ↑       |
        └───────┘
```

Since at least one number is duplicated, the traversal eventually enters a cycle.

The duplicate number corresponds to the **cycle entry point**.

### 2. Phase 1 — Detect the Cycle

Use Floyd's Tortoise and Hare algorithm with two pointers:

- `slow` moves one step at a time.
- `fast` moves two steps at a time.

```java
slow = nums[slow];
fast = nums[nums[fast]];
```

Continue until both pointers meet.

The meeting point confirms that a cycle exists, but it is not necessarily the cycle entry.

### 3. Phase 2 — Find the Cycle Entry

Once the pointers meet:

1. Reset `slow` to index `0`.
2. Keep `fast` at the meeting point.
3. Move both pointers one step at a time.

```java
slow = nums[slow];
fast = nums[fast];
```

They will meet again at the cycle entry, which is the duplicate number.

### Why Does This Work? — Floyd's Mathematical Property

Let:
- `μ` = distance from the starting point to the cycle entry.
- `λ` = length of the cycle.
- `k` = distance travelled by the slow pointer when the first meeting occurs.

At the first meeting, the fast pointer has travelled twice the distance of the slow pointer.

Therefore:

`2k - k = k`

The difference in their distances is a multiple of the cycle length:

`k = qλ` for some integer `q`.

This means the meeting point is positioned such that resetting one pointer to the start and moving both pointers one step at a time makes them meet at the cycle entry.

### Complexity Analysis

- **Time Complexity: O(n)** — Both phases traverse at most a linear number of positions.
- **Auxiliary Space Complexity: O(1)** — Only two pointers are used.

### Key Takeaways

- An array can represent an implicit linked list through index-based traversal.
- Duplicate values create a cycle in this representation.
- Floyd's algorithm detects the cycle using slow and fast pointers.
- The second phase identifies the cycle entry, which is the duplicate number.
- No sorting, array modification, HashMap, or HashSet is required.
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        while(slow<nums.length)
        {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(slow==fast)
            {
                slow = 0;
                break;
            }
        }
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
            if(slow == fast)
            {
                return slow;
            }
        }
        return slow;
    }
}
