/*
 * Concept:
 * - HashSet stores only unique elements.
 * - If the Set size is smaller than the array length,
 *   at least one duplicate exists.
 *
 * Key Insight:
 * n elements in the array but fewer than n unique elements
 * means there must be a duplicate.
 *
 * Pattern:
 * HashSet / Duplicate Detection
 *
 * Complexity:
 * Time: O(n) average
 * Space: O(n)
 */
class Solution {
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i =0;i<nums.length;i++)
        {
            set.add(nums[i]);
        }
        if(set.size()==nums.length){
            return false;
        }
        return true;
    }
}