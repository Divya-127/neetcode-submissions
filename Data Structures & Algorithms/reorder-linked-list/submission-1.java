/*
# Reorder List — LeetCode 143

## Problem
Reorder a singly linked list in-place so that the nodes appear in this order:

`L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...`

The node values and links must be rearranged without creating new list nodes.

## Approach: Find Middle + Reverse + Merge

The solution consists of three stages.

### 1. Find the middle and split the list

Use the slow and fast pointer technique:
- `slow` moves one node at a time.
- `fast` moves two nodes at a time.
- When `fast` reaches the end, `slow` identifies the end of the first half.

```java
ListNode slow = head;
ListNode fast = head;

while (fast.next != null && fast.next.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

Split the list into two independent lists:

```java
ListNode secondHalf = slow.next;
slow.next = null;
```

**Why save `slow.next` first?**

We must preserve the reference to the beginning of the second half before disconnecting the first half. Otherwise, we lose access to the remaining nodes.

Example:

Before splitting:
`0 → 1 → 2 → 3 → 4`

After splitting:
- First half: `0 → 1 → 2`
- Second half: `3 → 4`

### 2. Reverse the second half

Reverse the second half using three pointers:
- `prev`: previously processed node.
- `curr`: current node.
- `next`: temporarily saves the next node before reversing the link.

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }

    return prev;
}
```

After reversal:

First half: `0 → 1 → 2`

Reversed second half: `4 → 3`

Reversing the second half allows us to access the original last node first.

### 3. Merge both halves alternately

Initialize two pointers:

```java
ListNode first = head;
ListNode second = secondReversed;
```

At every iteration:

1. Save `first.next` and `second.next`.
2. Connect the first node to the second node.
3. Connect the second node to the original next node of the first list.
4. Advance both pointers using the saved references.

```java
while (second != null) {
    ListNode firstNext = first.next;
    ListNode secondNext = second.next;

    first.next = second;
    second.next = firstNext;

    first = firstNext;
    second = secondNext;
}
```

**Why save the next references first?**

Changing `first.next` overwrites the original link to the next node in the first half. Similarly, changing `second.next` overwrites its original link. Saving both references ensures that neither remaining list becomes inaccessible.

## Complete Java Solution

```java
*/
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list
        ListNode secondHalf = slow.next;
        slow.next = null;

        // Step 2: Reverse the second half
        ListNode secondReversed = reverseList(secondHalf);

        // Step 3: Merge alternately
        ListNode first = head;
        ListNode second = secondReversed;

        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
/*
```

## Dry Run

Input:

`0 → 1 → 2 → 3 → 4`

| Stage | First half | Second half |
|---|---|---|
| Split | `0 → 1 → 2` | `3 → 4` |
| Reverse | `0 → 1 → 2` | `4 → 3` |
| Merge | `0 → 4 → 1 → 3 → 2` | — |

For an even-length list such as `1 → 2 → 3 → 4 → 5 → 6`, the result is:

`1 → 6 → 2 → 5 → 3 → 4`

## Complexity Analysis

- **Time Complexity: O(n)**
  - Finding the middle takes O(n).
  - Reversing the second half takes O(n).
  - Merging both halves takes O(n).
  - Each stage traverses the list only a constant number of times.

- **Auxiliary Space Complexity: O(1)**
  - The solution uses a constant number of pointers.
  - No additional list nodes, arrays, or collections are created.

## Key Learnings

1. Slow and fast pointers can locate the middle of a linked list.
2. Always save a reference before overwriting a `next` pointer.
3. Reversing a sublist makes its original last node accessible first.
4. A linked list can be rearranged in-place by changing references.
5. For odd-length lists, the extra middle node remains at the end.
6. The second half is never longer than the first half, so `while (second != null)` is sufficient for merging.

**Core Pattern:** Find Middle → Split → Reverse Second Half → Merge Alternately.
*/