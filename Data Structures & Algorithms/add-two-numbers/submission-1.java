/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
 ## Add Two Numbers — LeetCode 2

### Approach: Linked List Traversal with Carry

The problem gives two non-empty linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each node contains a single digit.

The objective is to add the two numbers and return the result as a new linked list, also in reverse order.

### Algorithm

1. Initialize `carry = 0`.
2. Create a dummy node to serve as the starting point of the result list.
3. Initialize a `tail` pointer to the dummy node. This pointer tracks the last node in the result list.
4. Traverse both input lists while at least one list has remaining nodes or a carry is still present.

For each iteration:

- Extract the current digit from each list. If a list is exhausted (`null`), treat its digit as `0`.
- Calculate the sum of both digits and the previous carry.
- Calculate the new carry using integer division by 10.
- Calculate the digit to insert into the result using modulo 10.
- Attach a newly created node containing that digit to `tail.next`.
- Advance `tail` to `tail.next`.
- Advance the input pointers if they are not `null`.

5. Return `dummy.next`, which points to the head of the newly constructed result list.

### Important Concepts

**1. Handling Different List Lengths**

The loop condition uses:

```java
while (l1 != null || l2 != null || carry != 0)
```

This ensures that:
- Both lists are processed while both contain nodes.
- Remaining digits are processed even if one list finishes earlier.
- An additional node is created if a carry remains after both lists are exhausted.

**2. Carry Calculation**

For every digit position:

```java
int sum = l1Val + l2Val + carry;
carry = sum / 10;
int digit = sum % 10;
```

Integer division determines the carry, while modulo 10 extracts the digit to be stored in the result.

**3. Dummy and Tail Pointers**

- `dummy` remains at the beginning of the result list.
- `tail` always points to the last node in the result list.
- `tail.next = new ListNode(digit)` attaches a new node.
- `tail = tail.next` advances the tail pointer to the newly attached node.

The dummy node simplifies result construction by avoiding special handling for the first node.

### Complexity Analysis

Let `n` and `m` be the lengths of the two input lists.

- **Time Complexity: O(max(n, m))** — Each node is visited once, with at most one additional iteration for a final carry.
- **Auxiliary Space Complexity: O(1)** — Only a constant number of pointers and variables are used, excluding the output list.
- **Output Space Complexity: O(max(n, m) + 1)** — A new linked list is constructed to store the sum.

### Key Takeaways

- Linked lists can represent large numbers without converting them into primitive numeric types.
- Reverse-order digit storage allows addition to proceed naturally from least significant to most significant digit.
- Carry must be maintained across iterations.
- When adding linked lists, exhausted lists can contribute zero.
- Dummy and tail pointers provide a clean way to construct a result linked list.
*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while(l1!=null || l2!=null || carry!=0)
        {
            int l1Val = l1==null?0:l1.val;
            int l2Val = l2==null?0:l2.val;
            int sum = l1Val + l2Val + carry;
            if(sum>=10)
            {
                carry = sum/10;
                tail.next = new ListNode(sum%10);
            }else{
                carry = 0;
                tail.next = new ListNode(sum);
            }
            if(l1!=null)
            {
                l1 = l1.next;
            }
            if(l2!=null)
            {
                l2 = l2.next;
            }
            tail = tail.next;
        }
        return dummy.next;
    }
}
