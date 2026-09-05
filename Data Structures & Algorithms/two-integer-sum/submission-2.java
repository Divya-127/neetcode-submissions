/*
 * Concept:
 * - Use a HashMap to store each number and its index.
 * - For every current number, calculate the complement:
 *   target - current number.
 * - If the complement is already in the map, we found the pair.
 *
 * Key Insight:
 * Instead of checking every pair (O(n²)), store previously
 * seen numbers so the complement can be found in O(1) average time.
 *
 * Pattern:
 * HashMap + Complement Lookup
 *
 * Complexity:
 * Time: O(n) average
 * Space: O(n)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++)
        {
            int a = nums[i];
            int sum = target - a;
            if(hm.containsKey(sum))
            {
                return new int[]{hm.get(sum),i};
            }
            hm.put(a,i);
        }
        return new int[]{};
    }
}
