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
