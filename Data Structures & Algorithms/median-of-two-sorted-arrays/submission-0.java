class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int half = (m + n + 1)/2;
        int low = 0;
        int high = m;
        while(low<=high)
        {
            int i = low + (high-low)/2;
            int j = half - i;
            int nums1LeftMax = i>0?nums1[i-1]:Integer.MIN_VALUE;
            int nums1RightMin = i < m ? nums1[i] : Integer.MAX_VALUE;
            int nums2LeftMax = j>0?nums2[j-1]:Integer.MIN_VALUE;
            int nums2RightMin = j<n?nums2[j]:Integer.MAX_VALUE;
            if(nums1LeftMax > nums2RightMin)
            {
                high = i-1;
            }else if(nums2LeftMax > nums1RightMin)
            {
                low = i+1;
            }else if(nums2RightMin>=nums1LeftMax && nums1RightMin>=nums2LeftMax)
            {
                int leftMax = Math.max(nums2LeftMax,nums1LeftMax);
                int rightMin = Math.min(nums1RightMin,nums2RightMin);    
                if((m+n)%2==0)
                {
                    return (leftMax + rightMin)/2.0;
                }else{
                    return leftMax;
                }
            }
        }
    return 0.0;
    }
}
