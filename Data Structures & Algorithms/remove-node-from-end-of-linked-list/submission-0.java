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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        int len = 0;
        while(tail!=null)
        {
            len++;
            tail = tail.next;
        }
        tail = head;
        int i = len-n-1;
        if(len==n)
        return head.next;
        while(i>0)
        {
            tail = tail.next;
            i--;
        }
        if(tail.next!=null){
        ListNode tailNextNext = tail.next.next;
        tail.next = tailNextNext;
        }
        return head;
    }
}
