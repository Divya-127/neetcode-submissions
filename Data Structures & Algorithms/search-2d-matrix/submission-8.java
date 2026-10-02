/*
Concept:
Find the largest rectangular area that can be formed using consecutive bars
in a histogram.

Key Insight:
For every bar, find how far it can extend left and right while maintaining
that bar's height.

Use a monotonic increasing stack of INDICES:

* The stack stores bars that are still waiting to find their right boundary.
* When the current bar is smaller than the stack top, the current index becomes
  the right boundary of the popped bar.
* After popping, the new stack top is the nearest smaller bar on the left.
* Calculate:
  width = right - left - 1
  area  = height × width
* Bars remaining in the stack at the end have no smaller bar on the right,
  so the array length acts as their right boundary.

Pattern:
Monotonic Increasing Stack + Nearest Smaller Element

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
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = n-1;
        while(row<m && col>=0)
        {
            if(target>matrix[row][col])
            {
                row++;
            }else if(target<matrix[row][col])
            {
                col--;
            }else if(target==matrix[row][col])
            {
                return true;
            }
        }
        return false;
    }
}
