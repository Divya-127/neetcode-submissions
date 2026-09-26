/*
Concept:
Track the cheapest buying price seen so far while scanning prices once.

Key Insight:
For each day as the selling day:
profit = current price - minimum price seen before today.

Maintain:
- minPrice = cheapest price seen so far
- maxProfit = maximum profit found so far

This avoids storing a suffix maximum array.

Pattern:
Greedy + Running Minimum

Complexity:
Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        return maxProfit;
    }
}
