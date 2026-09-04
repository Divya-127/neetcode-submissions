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