/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
# Merge K Sorted Lists — LeetCode 23

## Approach: Divide and Conquer (Recursive)

The goal is to merge K sorted linked lists into one sorted linked list.

Instead of merging the lists sequentially, we use a Divide and Conquer strategy to reduce the total merging work.

### 1. Core Idea

Reuse the existing `mergeTwoLists()` function from Merge Two Sorted Lists (LeetCode 21).

Rather than merging:

```text
L1 + L2 → M1
M1 + L3 → M2
M2 + L4 → Final
```

Divide the input lists into two halves and merge them recursively:

```text
             [L1, L2, L3, L4]
                    |
             /             \
         [L1, L2]         [L3, L4]
          /    \           /    \
         L1    L2         L3    L4
          \    /           \    /
           M1               M2
             \             /
                Final
```

### 2. Recursive Implementation Logic

The `mergeKLists()` method handles the initial validation and invokes a recursive helper with the complete index range.

The helper `merge(lists, left, right)` performs the following:

**Base cases:**

- If `left == right`, only one list remains, so return `lists[left]` directly.
- If `right == left + 1`, exactly two lists remain, so merge them using `mergeTwoLists()`.

**Recursive case:**

1. Calculate the midpoint:

   ```java
   int mid = left + (right - left) / 2;
   ```

2. Recursively merge the left half:

   ```java
   ListNode leftList = merge(lists, left, mid);
   ```

3. Recursively merge the right half:

   ```java
   ListNode rightList = merge(lists, mid + 1, right);
   ```

4. Merge the two resulting sorted lists:

   ```java
   return mergeTwoLists(leftList, rightList);
   ```

### 3. How Recursion Works

The recursive calls divide the array of list heads into smaller ranges until each range contains one or two lists.

Once the base cases are reached, the recursion starts unwinding:

- Individual pairs of sorted lists are merged.
- Their merged results are returned to the parent recursive call.
- The parent merges those two results.
- This continues until one final sorted linked list is produced.

The important distinction is that recursion divides the **array of list heads**, not the individual linked lists.

### 4. Complexity Analysis

Let:
- N = Total number of nodes across all linked lists.
- K = Number of linked lists.

**Time Complexity: O(N log K)**

- Each level of recursion processes approximately N nodes across all merges.
- The number of levels required to reduce K lists to one is O(log K).
- Therefore, total time complexity is O(N log K).

**Space Complexity: O(log K)**

- Recursive call stack requires O(log K) space.
- `mergeTwoLists()` is iterative and uses O(1) auxiliary space.
- No additional linked lists are created; existing nodes are relinked.

### 5. Comparison with Sequential Merging

| Approach | Time Complexity |
|---|---|
| Sequential merging | O(NK) |
| Divide and Conquer | O(N log K) |

Sequential merging repeatedly processes an increasingly large merged list.

Divide and Conquer balances the merging work by combining lists in pairs at each level.

### 6. Key Learnings

- Reuse existing solutions instead of reinventing the merging logic.
- Divide and Conquer reduces repeated processing.
- Recursive calls handle smaller subproblems and combine their results.
- The recursion stack represents pending work until the child calls return.
- Choosing a balanced split is what achieves O(N log K).

**Core takeaway:** Divide and Conquer can significantly improve efficiency by balancing the work across multiple merging stages.


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
