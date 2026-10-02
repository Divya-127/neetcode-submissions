class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        int ans = Integer.MIN_VALUE;
        for(int i = 0;i<heights.length;i++)
        {
            while(!stack.isEmpty() && heights[i]<heights[stack.peek()])
            {
                int popped = stack.pop();
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans,(right-left-1)*(heights[popped]));
            }
            stack.push(i);
        }
        int right = heights.length;
        while(!stack.isEmpty())
        {
            int popped = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int width = right - left - 1;
            ans = Math.max(ans,(right-left-1)*heights[popped]);
        }
        return ans;
    }
}
