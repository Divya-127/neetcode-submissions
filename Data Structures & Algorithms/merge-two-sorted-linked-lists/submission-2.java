/*
# LeetCode 21 – Merge Two Sorted Lists

## Approach: Iterative Merge Using a Dummy Node

### 1. Problem Understanding

We are given two sorted linked lists, `list1` and `list2`.

We need to merge them into a single sorted linked list by rearranging the existing node references.

For example:

```text
list1: 1 → 3 → 5 → null
list2: 2 → 4 → 6 → null

Output: 1 → 2 → 3 → 4 → 5 → 6 → null
```

Since both input lists are already sorted, we do not need to sort them again. We simply compare their current nodes and select the smaller one.

### 2. Core Intuition

The algorithm maintains three important references:

- **dummy:** A placeholder node placed before the merged list. It helps us avoid special handling for the first node.
- **tail:** Points to the last node in the merged list. Every newly selected node is attached after it.
- **list1 and list2:** Track the current unprocessed nodes of the two input lists.

Initially:

```text
dummy → null
  ↑
 tail

list1 → 1 → 3 → 5
list2 → 2 → 4 → 6
```

Both `dummy` and `tail` point to the same placeholder node.

### 3. Why Use a Dummy Node?

Without a dummy node, we would need separate logic to determine and initialize the head of the merged list.

Instead, we create:

```java
ListNode dummy = new ListNode(-1);
ListNode tail = dummy;
```

Whenever we attach a node using:

```java
tail.next = list1;
```

or:

```java
tail.next = list2;
```

we modify the `next` reference of the node currently pointed to by `tail`.

Since `dummy` and `tail` initially reference the same object, the first attachment also updates `dummy.next`.

As `tail` advances, `dummy` remains at the beginning of the merged list.

Therefore, the actual merged head is always available through:

```java
dummy.next
```

### 4. Algorithm

**Step 1: Initialize the dummy node and tail pointer.**

```java
ListNode dummy = new ListNode(-1);
ListNode tail = dummy;
```

**Step 2: Compare nodes while both lists are non-empty.**

```java
while (list1 != null && list2 != null)
```

We can safely compare values because both pointers reference valid nodes.

**Step 3: Attach the smaller node.**

If `list1.val <= list2.val`:

```java
tail.next = list1;
list1 = list1.next;
```

Otherwise:

```java
tail.next = list2;
list2 = list2.next;
```

The selected node is attached to the merged list, and its original list pointer advances.

**Step 4: Advance the tail pointer.**

```java
tail = tail.next;
```

This moves `tail` to the newly attached node, ensuring it always points to the last node of the merged list.

**Step 5: Attach the remaining list.**

Once either input list becomes empty, the other list may still contain nodes.

Since both input lists were already sorted, the remaining nodes are also in sorted order. No additional comparisons are required.

```java
tail.next = list1 != null ? list1 : list2;
```

**Step 6: Return the actual head.**

```java
return dummy.next;
```

We return `dummy.next` rather than `dummy`, because the dummy node is only a placeholder and is not part of the required result.

### 5. Dry Run

Input:

```text
list1: 1 → 3 → 5
list2: 2 → 4 → 6
```

| Iteration | list1 | list2 | Selected node | Merged list |
|---|---:|---:|---:|---|
| 1 | 1 | 2 | 1 | 1 |
| 2 | 3 | 2 | 2 | 1 → 2 |
| 3 | 3 | 4 | 3 | 1 → 2 → 3 |
| 4 | 5 | 4 | 4 | 1 → 2 → 3 → 4 |
| 5 | 5 | 6 | 5 | 1 → 2 → 3 → 4 → 5 |

At this point:

```text
list1: null
list2: 6 → null
```

The main loop terminates because one list is empty.

We attach the remaining list:

```java
tail.next = list2;
```

Final result:

```text
1 → 2 → 3 → 4 → 5 → 6 → null
```

### 6. Java Implementation

```java
*/
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // Merge while both lists have nodes
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }

            // Move tail to the newly attached node
            tail = tail.next;
        }

        // Attach whichever list still has remaining nodes
        tail.next = list1 != null ? list1 : list2;

        // Skip the dummy node and return the merged list
        return dummy.next;
    }
}
/*
```

### 7. Complexity Analysis

Let:
- `n` = number of nodes in `list1`
- `m` = number of nodes in `list2`

**Time Complexity: O(n + m)**

Each node is processed at most once. Every iteration performs a constant number of pointer operations and one value comparison.

**Auxiliary Space Complexity: O(1)**

We reuse the existing linked-list nodes and only maintain a constant number of references (`dummy`, `tail`, `list1`, and `list2`).

No additional data structure or new nodes are required for the merged result, apart from the single dummy node.

### 8. Important Takeaways

1. A dummy node simplifies linked-list construction by eliminating special handling for the first node.
2. `tail.next = selectedNode` attaches a node but does not move `tail`.
3. `tail = tail.next` advances the pointer to the newly attached node.
4. Advance the pointer of the list from which the node was selected.
5. Once one list becomes empty, the remaining sorted list can be attached directly.
6. Always return `dummy.next`, not the dummy node itself.

**Pattern to remember:** Dummy Node + Tail Pointer + Two-Pointer Comparison.

This pattern is useful in other linked-list problems involving merging, constructing, or rearranging lists.
*/