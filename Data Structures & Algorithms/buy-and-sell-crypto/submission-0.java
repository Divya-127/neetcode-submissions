class Solution {
    public int maxProfit(int[] prices) {
        int[] maxRight = new int[prices.length];
        maxRight[prices.length-1] = 0;
        for(int j = prices.length-2;j>=0;j--)
        {
            maxRight[j] = prices[j]>maxRight[j+1]?prices[j]:maxRight[j+1];
        }
        int ans = 0;
        for(int i = 0;i<prices.length-1;i++)
        {
            if((maxRight[i]-prices[i])>ans)
            {
                ans = maxRight[i]-prices[i];
            }
        }
        return ans;
    }
}
