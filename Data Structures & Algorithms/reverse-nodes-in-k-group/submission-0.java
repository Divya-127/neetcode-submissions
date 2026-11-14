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


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {

            // Find the kth node of the current group
            ListNode kth = groupPrev;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            // Not enough nodes remaining
            if (kth == null) {
                break;
            }

            // Save the node after this group
            ListNode groupNext = kth.next;

            // Reverse the current group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect the reversed group to the previous group
            ListNode oldGroupHead = groupPrev.next;
            groupPrev.next = kth;

            // Old head is now the tail
            groupPrev = oldGroupHead;
        }

        return dummy.next;
    }
}

