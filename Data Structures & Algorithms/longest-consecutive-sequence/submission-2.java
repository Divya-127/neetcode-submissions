/*
 * Concept:
 * - Put all numbers into a HashSet for O(1) average lookup.
 * - A number is the START of a sequence only if (num - 1)
 *   does not exist in the Set.
 * - From each sequence start, keep checking num + 1 and count
 *   the consecutive elements.
 *
 * Key Insight:
 * Only expand sequences from their smallest element.
 * This prevents repeatedly traversing the same sequence.
 *
 * Pattern:
 * HashSet + Sequence Start Detection
 *
 * Complexity:
 * Time: O(n) average
 * Space: O(n)
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int num: nums)
        {
            set.add(num);
        }
        int ans = 0;
        int len = 0;
        for(int i=0;i<nums.length;i++)
        {
            if(!(set.contains(nums[i]-1)))
            {
                int current = nums[i];
                len = 1;
                while(set.contains(current+1))
                {
                    len++;
                    current = current + 1;
                }
                if(len>ans)
                {
                    ans = len;
                }
            }
        }
        return ans;
    }
}
