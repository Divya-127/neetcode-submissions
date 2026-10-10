/*
## Product of Array Except Self — LeetCode 238

### Key Idea
Answer[i] = (product of everything left of i) * (product of everything right of i).
Build the left products directly in the output array, then sweep from the right
with a running suffix product and multiply it in.

### Complexity
* **Time:** `O(n)`
* **Space:** `O(1)` extra (the output array does not count)

### Gotcha
Do the right-to-left pass in the right order: multiply `res[i]` by the suffix
**before** folding `nums[i]` into it, otherwise nums[i] ends up in its own answer.

### Confidence
medium
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }
        return res;
    }
}