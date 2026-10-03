/*
Concept:
Find the minimum element in a sorted array that has been rotated.

Key Insight:
A rotated sorted array consists of two sorted portions. The minimum is the
point where the sorted order breaks.

Use Binary Search to find which side contains the rotation point by comparing
nums[mid] with nums[end].

- If nums[mid] > nums[end]:
      mid is in the left/high portion.
      The rotation point must be to the RIGHT of mid.
      Therefore:
          start = mid + 1

- If nums[mid] < nums[end]:
      mid is in the right/low portion.
      The minimum could be at mid itself or somewhere to its LEFT.
      Therefore:
          end = mid

Important:
Use end = mid, not end = mid - 1, because mid itself can be the minimum.

Optimization:
If nums[start] < nums[end], the current search range is already sorted,
so nums[start] is immediately the minimum.

When:
    start == end

only one candidate remains, so that element is the minimum.

Pattern:
Binary Search on a Rotated / Partially Sorted Array

Complexity:
Time: O(log n)
Space: O(1)

Mental Model:
Compare MID with END.

mid > end
    ↓
Rotation is to the RIGHT
    ↓
start = mid + 1

mid < end
    ↓
Rotation is at MID or to
*/

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
