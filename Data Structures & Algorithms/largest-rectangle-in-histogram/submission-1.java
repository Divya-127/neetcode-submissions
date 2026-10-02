/*
Concept:
Find the largest rectangular area that can be formed using consecutive bars
in a histogram.

Key Insight:
For every bar, find how far it can extend left and right while maintaining
that bar's height.

Use a monotonic increasing stack of INDICES:
- The stack stores bars that are still waiting to find their right boundary.
- When the current bar is smaller than the stack top, the current index becomes
  the right boundary of the popped bar.
- After popping, the new stack top is the nearest smaller bar on the left,
  so it becomes the left boundary.
- Calculate:
      width = right - left - 1
      area  = height × width
- Bars remaining in the stack at the end have no smaller bar on the right,
  so the array length acts as their right boundary.

Pattern:
Monotonic Increasing Stack + Nearest Smaller Element

Core Loop Pseudocode:

for each index i:
    while stack is not empty AND current height < stack top height:
        popped = stack.pop()

        right = i
        left = stack is empty ? -1 : stack.peek()

        width = right - left - 1
        area = height[popped] × width

        update maximum area

    push i into stack

After the loop:
    remaining bars have right boundary = n
    pop each bar and calculate its area

Complexity:
Time: O(n)
Space: O(n)

Mental Model:
Stack = bars waiting for their right boundary

Current bar is smaller
        ↓
   Pop previous bar
        ↓
Current index = Right Boundary
New stack top = Left Boundary
        ↓
Calculate height × width

At the end:
Remaining bars → right boundary = n
*/
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
