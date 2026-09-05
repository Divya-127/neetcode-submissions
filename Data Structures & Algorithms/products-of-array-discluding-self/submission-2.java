/*
 * Concept:
 * - Build the product of all elements to the left of each index
 *   (prefix product).
 * - Then traverse from right to left while maintaining the
 *   product of all elements to the right (suffix product).
 * - Multiply prefix and suffix products to get the final answer.
 *
 * Key Insight:
 * ans[i] initially stores the product of everything LEFT of i.
 * During the second pass, multiply it by the product of everything
 * RIGHT of i.
 *
 * Pattern:
 * Prefix + Suffix Products
 *
 * Complexity:
 * Time: O(n)
 * Space: O(1) extra space (excluding the output array)
 */
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