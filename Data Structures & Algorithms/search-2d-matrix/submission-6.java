/*
Concept:
Search for a target value in a row-wise sorted 2D matrix.

Key Insight:
Each row is sorted, so use binary search within a row.
Before searching, check whether the target can possibly exist in that row:
target >= first element && target <= last element

Only perform binary search on rows whose range contains the target.

Pattern:
Row Filtering + Binary Search

Complexity:
Time: O(m log n)
Space: O(1)

Mental Model:
Check row range
↓
Target can exist?
↓
YES → Binary Search the row
NO  → Skip the row
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        for (int row = 0; row < m; row++) {

            int start = 0;
            int end = n - 1;

            while (start <= end) {

                int mid = start + (end - start) / 2;

                if (matrix[row][mid] == target) {
                    return true;
                } 
                else if (target > matrix[row][mid]) {
                    start = mid + 1;
                } 
                else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }
}