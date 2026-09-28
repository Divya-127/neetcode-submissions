/*
Concept:
Find the maximum element in every fixed-size sliding window.

Key Insight:
Maintain a monotonic decreasing deque of INDICES.
- The front always contains the index of the current maximum.
- Remove indices from the front when they expire (leave the window).
- When a new element enters, remove smaller/equal elements from the back
  because they can never become the maximum while the new larger element
  remains in the window.
- Store indices instead of values so we can identify expired elements.

Pattern:
Fixed-Size Sliding Window + Monotonic Deque

Complexity:
Time: O(n)
Space: O(k)

Mental Model:
Front = current maximum
Back = weaker candidates to remove
Index = tells us when an element expires
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        for(int end = 0;end < nums.length;end++)
        {
            //Remove expired items from deque
            if(!deque.isEmpty() && deque.peekFirst() < end-k+1)
            {
                deque.removeFirst();
            }

            //Remove smaller element from back
            while(!deque.isEmpty() && nums[deque.peekLast()]<=nums[end])
            {
                deque.removeLast();
            }
            deque.addLast(end);
            if(end>=k-1)
            {
                ans[index++] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }
}
