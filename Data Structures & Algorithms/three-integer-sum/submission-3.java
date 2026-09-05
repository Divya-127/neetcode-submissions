/*
Concept:
Sort the array, fix one element, then use two pointers to find the remaining two elements whose sum equals -nums[k].

Key Insight:

* Sorting allows two-pointer movement and makes duplicates adjacent.
* Fix nums[k] and set target = -nums[k].
* i starts at k + 1, j starts at the end.
* Sum > target → j--.
* Sum < target → i++.
* Sum == target → record triplet, move both pointers, then skip duplicates.

Duplicate Handling:

* Skip duplicate k values: nums[k] == nums[k - 1].
* After finding a triplet, skip duplicate i and j values.
* This prevents duplicate triplets without needing a HashSet.

Pattern:
Sorting + Fixed Element + Two Pointers + Duplicate Skipping

Complexity:
Time: O(n²)
Space: O(1) auxiliary space (excluding output; sorting may use implementation-dependent stack/temporary space)
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int k=0;k<nums.length-2;k++)
        {
            if(k>0 && (nums[k]==nums[k-1]))
            {
                continue;
            }
            int target = 0-nums[k];
            int i = k+1;
            int j = nums.length - 1;
            while(j>i)
            {
                if(nums[i]+nums[j]==target)
                {
                    ans.add(Arrays.asList(nums[k],nums[i],nums[j]));
                    i++;
                    j--;
                    while (i < j && nums[i] == nums[i - 1]) {
                        i++;
                    }
                    while( i < j && nums[j] == nums[j+1]){
                        j--;
                    }
                }else if(nums[i]+nums[j]>target)
                {
                    j--;
                }else{
                    i++;
                }
            }
        }
        return ans;
    }
}