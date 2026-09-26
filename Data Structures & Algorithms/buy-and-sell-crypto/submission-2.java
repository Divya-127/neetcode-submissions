/*
Concept:
For each day, calculate the best selling price available after that day.

Key Insight:
If buying on day i, the maximum profit is:
best future selling price - prices[i]

Build a suffix maximum array where:
maxRight[i] = maximum price from i+1 to the end.

Then scan each possible buying day and keep the maximum profit.

Pattern:
Suffix Maximum + One-Pass Profit Calculation

Complexity:
Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int[] maxRight = new int[prices.length];
        maxRight[prices.length-1] = 0;
        for(int j = prices.length-2;j>=0;j--)
        {
            maxRight[j] = prices[j+1]>maxRight[j+1]?prices[j+1]:maxRight[j+1];
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
