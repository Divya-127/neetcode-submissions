/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/* Solve Linked List Cycle using Floyd's Tortoise and Hare algorithm

- Initialize slow and fast pointers at the head.
- Move slow by one node and fast by two nodes.
- Detect a cycle when both pointers reference the same node.
- Use null checks to safely handle lists without cycles.

Time: O(n) | Space: O(1)
*/
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null )
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                return true;
            }
        }
        return false;
    }
}
