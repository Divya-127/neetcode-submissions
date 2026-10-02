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