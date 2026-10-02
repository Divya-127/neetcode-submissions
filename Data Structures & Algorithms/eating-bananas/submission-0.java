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
