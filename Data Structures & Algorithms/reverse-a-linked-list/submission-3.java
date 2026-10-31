/*
## Reverse Linked List — Iterative Approach

### Approach
Reverse a singly linked list in-place using three pointers: `prev`, `curr`, and `next`.

Initially:
- `prev = null`: The reversed portion is empty.
- `curr = head`: The current node being processed.
- `next`: Temporarily stores the next node in the original list before its link is modified.

### Algorithm
For every node in the original list:

1. **Save the next node:** `next = curr.next`
   - Store the reference to the next node before modifying the current node's link.
   - This is essential because reversing the link would otherwise lose access to the remaining list.

2. **Reverse the current link:** `curr.next = prev`
   - Make the current node point to the previous node instead of the next node.
   - For the first node, `prev` is `null`, so the original head becomes the tail of the reversed list.

3. **Move `prev` forward:** `prev = curr`
   - The current node becomes the first node of the reversed portion.
   - This assignment only moves a reference; it does not modify any node's `next` pointer.

4. **Move `curr` forward:** `curr = next`
   - Continue processing the next node from the original list using the saved reference.

Repeat until `curr == null`. At this point, all original links have been reversed, and `prev` points to the new head.

### Example

Original list:

`1 → 2 → 3 → null`

**Iteration 1**
- Save `2` in `next`.
- Set `1.next = null`.
- Move `prev` to `1` and
*/

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // Save the next node before changing the pointer
            ListNode next = curr.next;

            // Reverse the current node's pointer
            curr.next = prev;

            // Move prev and curr one step forward
            prev = curr;
            curr = next;
        }

        // prev is the new head
        return prev;
    }
}