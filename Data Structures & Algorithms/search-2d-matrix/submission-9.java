/*
## Search a 2D Matrix — LeetCode 74

### Key Idea
Rows are sorted and each row starts above the previous row's last value, so the
matrix is one sorted array in disguise. Binary search the flattened index range
[0, rows*cols - 1] and map an index back with `row = i / cols`, `col = i % cols`.

### Complexity
* **Time:** `O(log(m·n))`
* **Space:** `O(1)`

### Gotcha
Divide and mod by `cols`, not `rows`. Use `lo + (hi - lo) / 2` to avoid overflow.

### Confidence
medium
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int lo = 0;
        int hi = rows * cols - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = matrix[mid / cols][mid % cols];

            if (val == target) {
                return true;
            } else if (val < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}