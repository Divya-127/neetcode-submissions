class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] prefixMul = new int[nums.length];
        int[] suffixMul = new int[nums.length];
        Arrays.fill(prefixMul,1);
        Arrays.fill(suffixMul,1);

        for(int i=0;i<nums.length-1;i++)
        {
            prefixMul[i+1]=prefixMul[i]*nums[i];
        }
        for(int j=nums.length-1;j>0;j--)
        {
            suffixMul[j-1]=suffixMul[j]*nums[j];
        }
        int[] ans = new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            ans[i] = prefixMul[i]*suffixMul[i];
        }
        return ans;
    }
}  
