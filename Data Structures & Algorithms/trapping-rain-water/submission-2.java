/*
## Trapping Rain Water — LeetCode 42

### Key Idea
Water above bar i = min(maxLeft, maxRight) - height[i]. Instead of storing both
prefix arrays, use two pointers and always advance the side with the smaller
running max: that side's water level is fully determined by its own max,
because the other side is guaranteed to hold something at least as tall.

### Complexity
* **Time:** `O(n)`
* **Space:** `O(1)`

### Gotcha
Update the running max **before** adding water, so a bar never contributes
negative water. Move only the pointer on the shorter side.

### Confidence
low
*/
class Solution {
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                leftMax = Math.max(leftMax, height[l]);
                water += leftMax - height[l];
                l++;
            } else {
                rightMax = Math.max(rightMax, height[r]);
                water += rightMax - height[r];
                r--;
            }
        }
        return water;
    }
}