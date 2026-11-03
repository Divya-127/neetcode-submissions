/*
# Remove Nth Node From End of List — LeetCode 19

## Problem
Given the head of a singly linked list, remove the `n`th node from the end and return the updated head.

Example:

Input: `1 → 2 → 3 → 4 → 5`, `n = 2`

Output: `1 → 2 → 3 → 5`

## Approach: Two-Pass Solution

The idea is to calculate the length of the linked list first, then use that length to identify the node to remove.

### Step 1: Calculate the length

Traverse the entire list and count the number of nodes.

```java
ListNode tail = head;
int len = 0;

while (tail != null) {
    len++;
    tail = tail.next;
}
```

### Step 2: Handle removal of the head

If `n == len`, the node to remove is the first node.

Return `head.next` as the new head.

```java
if (len == n) {
    return head.next;
}
```

This also handles a single-node list, where removing its only node returns `null`.

### Step 3: Find the node before the target

The target node's zero-based index from the beginning is:

`len - n`

However, we need to reach the node immediately before it so that we can update its `next` pointer.

Therefore, the number of steps from the head is:

`len - n - 1`

```java
tail = head;
int i = len - n - 1;

while (i > 0) {
    tail = tail.next;
    i--;
}
```

### Step 4: Remove the target node

Skip the target node by connecting the previous node directly to the target's next node.

```java
tail.next = tail.next.next;
```

## Complete Java Solution

```java
*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        int len = 0;

        // Step 1: Calculate list length
        while (tail != null) {
            len++;
            tail = tail.next;
        }

        // Step 2: Handle removal of head
        if (len == n) {
            return head.next;
        }

        // Step 3: Reach the node before the target
        tail = head;
        int i = len - n - 1;

        while (i > 0) {
            tail = tail.next;
            i--;
        }

        // Step 4: Remove the target node
        tail.next = tail.next.next;

        return head;
    }
}
/*
```

## Dry Run

Input: `1 → 2 → 3 → 4 → 5`, `n = 2`

- Length = 5
- Target index = `5 - 2 = 3` (
*/