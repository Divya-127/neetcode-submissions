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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
        return null;
        return merge(lists,0,lists.length-1);
    }

    public ListNode merge(ListNode[] lists,int left,int right)
    {
        if(left == right)
        {
            return lists[left];
        }
        if(right == left+1)
            return mergeTwoLists(lists[left],lists[right]);
        int mid = left + (right-left)/2;
        ListNode leftList = merge(lists,left,mid);
        ListNode rightList = merge(lists,mid+1,right);
        return mergeTwoLists(leftList,rightList);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2)
    {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(list1!=null && list2!=null)
        {
            if(list1.val>list2.val)
            {
                tail.next = list2;
                list2 = list2.next;
            }else{
                tail.next = list1;
                list1 = list1.next;
            }
            tail = tail.next;
        }
        tail.next = list1==null?list2:list1;
        return dummy.next;
    }

}
