class Solution {
    public int trap(int[] height) {
        int[] maxRight = new int[height.length];
        int[] maxLeft = new int[height.length];
        maxLeft[0] = height[0];
        maxRight[height.length-1] = height[height.length-1];
        for(int i = 1;i<=height.length-1;i++)
        {
            if(height[i]>maxLeft[i-1])
            {
                maxLeft[i] = height[i];
            }else{
                maxLeft[i] = maxLeft[i-1];
            }
        }
        for(int j=height.length-2;j>=0;j--)
        {
            if(height[j]>maxRight[j+1]){
                maxRight[j] = height[j];
            }else{
                maxRight[j] = maxRight[j+1];
            }
        }
        int water = 0;
        for(int i = 0;i<height.length;i++)
        {
            water = water + (Math.min(maxLeft[i],maxRight[i]) - height[i]);
        }
        return water;
    }
}
