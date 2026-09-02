class Solution {
    public int[] productExceptSelf(int[] nums) {

        int[] ans = new int[nums.length];

        // Prefix product
        ans[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        // Suffix product
        int suffix = 1;

        for (int j = nums.length - 1; j >= 0; j--) {
            ans[j] = ans[j] * suffix;
            suffix = suffix * nums[j];
        }

        return ans;
    }
}