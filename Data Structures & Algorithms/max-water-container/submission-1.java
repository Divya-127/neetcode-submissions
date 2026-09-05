/*
Concept:
Use two pointers, one at the beginning and one at the end of the array, to find the maximum area between two heights.

Key Insight:
Area = width × min(leftHeight, rightHeight).

The shorter height is the bottleneck. Since moving either pointer decreases the width, moving the taller pointer cannot improve the area while the shorter height remains the limiting factor.

Therefore, always move the pointer with the smaller height:

* heights[i] < heights[j] → i++
* heights[i] > heights[j] → j--
* Equal heights → either pointer can move

Calculate the current area, update maxArea, then move the shorter pointer.

Pattern:
Two Pointers + Greedy Pointer Movement

Complexity:
Time: O(n)
Space: O(1)
*/

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
