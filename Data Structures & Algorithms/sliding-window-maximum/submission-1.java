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
