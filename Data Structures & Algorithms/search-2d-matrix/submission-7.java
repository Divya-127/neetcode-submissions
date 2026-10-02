/*
Concept:
Search for a target in a matrix where every row and every column is sorted.

Key Insight:
Start from the top-right corner.

At matrix[row][col]:

* target > current → move DOWN because everything to the left is smaller.
* target < current → move LEFT because everything below is larger.
* target == current → target found.

Each move eliminates an entire row or column.

Pattern:
Top-Right Matrix Search

Complexity:
Time: O(m + n)
Space: O(1)

Mental Model:
Start at top-right

Target bigger  → DOWN
Target smaller → LEFT
Target equal   → FOUND

Every move eliminates one row or one column.
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        for (int row = 0; row < m; row++) {

            if (target >= matrix[row][0] &&
                target <= matrix[row][n - 1]) {

                int start = 0;
                int end = n - 1;

                while (start <= end) {

                    int mid = start + (end - start) / 2;

                    if (matrix[row][mid] == target) {
                        return true;
                    } else if (target > matrix[row][mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            }
        }

        return false;
    }
}