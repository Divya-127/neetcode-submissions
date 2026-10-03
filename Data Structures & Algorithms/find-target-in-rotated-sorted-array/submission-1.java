class Solution {
    public int search(int[] nums, int target) {
        int minIndex = minimumIndex(nums);
        int arr1Start = 0;
        int arr1End = minIndex>0?minIndex-1:0;
        int arr2Start = minIndex;
        int arr2End = nums.length - 1;
        if(target >= nums[arr1Start] && target <= nums[arr1End])
        {
            return getBinaryIndex(nums,arr1Start,arr1End,target);
        }else if(target >= nums[arr2Start] && target <= nums[arr2End])
        {
            return getBinaryIndex(nums,arr2Start,arr2End,target);
        }else{
            return -1;
        }
    }
    public int minimumIndex(int[] nums)
    {
        int start = 0;
        int end = nums.length - 1;
        while(start<end)
        {
            if(nums[end]>nums[start])
            {
                return start;
            }
            int mid = start + (end-start)/2;
            if(nums[mid]>nums[end])
            {
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return start;
    }
    public int getBinaryIndex(int[] nums,int low,int high,int target)
    {
        while(low<=high)
        {
            int mid = low + (high-low)/2;
            if(nums[mid]==target)
            {
                return mid;
            }else if(nums[mid]>target)
            {
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }
}
