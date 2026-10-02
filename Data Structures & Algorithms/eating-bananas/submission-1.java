/*
Concept:
Find the minimum eating speed k such that Koko can finish all banana piles
within h hours.

Key Insight:
Binary Search on the Answer.

The possible speeds form a monotonic pattern:
Speed too slow  → cannot finish within h hours
Speed fast enough → can finish within h hours

For a given speed k, calculate the required hours for every pile:
hours += ceil(pile / k)

Using integer arithmetic:
ceil(pile / k) = (pile + k - 1) / k

Search between:
start = 1
end = maximum pile

For each mid speed:

* If required hours > h → speed is too slow → start = mid + 1
* If required hours <= h → speed works → try a smaller speed → end = mid

When start == end, it is the minimum valid speed.

Pattern:
Binary Search on Answer + Feasibility Check

Complexity:
Time: O(n log(maxPile))
Space: O(1)

Mental Model:
Guess a speed
↓
Can Koko finish in h hours?
↓
NO  → Go faster
YES → Try slower
↓
Find the first YES
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for(int i=0;i<piles.length;i++)
        {
            max = Math.max(max,piles[i]);
        }
        int start = 1;
        int end = max;
        if(getHours(max,piles)>h){
            return -1;
        }else{
            while(end>start)
            {
                int mid = start + (end-start)/2;
                int a = getHours(mid,piles);
                if(a>h)
                {
                    start = mid+1;
                }else{
                    end = mid;
                }
            }
        }
        return start;
    }
    int getHours(int k, int[] piles){
        int ans = 0;
        for(int i=0;i<piles.length;i++)
        {
            ans = ans + ((piles[i]+k-1)/k);
        }
        return ans;
    }
}
