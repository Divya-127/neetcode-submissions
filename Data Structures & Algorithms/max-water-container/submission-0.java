class Solution {
    public int maxArea(int[] heights) {
        int i = 0;
        int j = heights.length-1;
        int maxArea = (j-i)*(Math.min(heights[i],heights[j]));
        while(j>i)
        {
            if((heights[i]>heights[j]))
            {
                if(j>0){
                    j--;
                    maxArea = Math.max(maxArea,(j-i)*(Math.min(heights[i],heights[j])));
                }
            }else{
                if(i<heights.length-1){
                i++;
                maxArea = Math.max(maxArea,(j-i)*(Math.min(heights[i],heights[j])));
                }                
            }
        }
        return maxArea;
    }
}
