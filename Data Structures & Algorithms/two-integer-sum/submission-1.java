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
