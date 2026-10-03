/*
Concept:
Search for a target in a sorted array that has been rotated, without
explicitly finding the rotation point.

Key Insight:
At every iteration, at least ONE half of the current search range is sorted.

First:
    If nums[mid] == target → target found.

Otherwise, determine which half is sorted.

1. Left half is sorted:
       nums[start] <= nums[mid]

   Check whether target lies inside the sorted left half:

       nums[start] <= target < nums[mid]

   If YES:
       target must be in the left half
       → end = mid - 1

   If NO:
       target cannot be in the left half
       → start = mid + 1


2. Right half is sorted:
       nums[start] > nums[mid]

   Check whether target lies inside the sorted right half:

       nums[mid] < target <= nums[end]

   If YES:
       target must be in the right half
       → start = mid + 1

   If NO:
       target cannot be in the right half
       → end = mid - 1

The key is that we don't need to know the pivot explicitly.
We identify the sorted half at every iteration and use its boundaries
to determine whether the target can exist there.

Pattern:
Binary Search on a Rotated / Partially Sorted Array
+ Sorted-Half Identification

Complexity:
Time: O(log n)
Space: O(1)

Mental Model:

        Find MID
           ↓
    Is MID the target?
       /         \
     YES          NO
      ↓            ↓
   return     Which half is sorted?
              /              \
       LEFT sorted       RIGHT sorted
           ↓                  ↓
     Target in left?    Target in right?
       /      \           /       \
     YES      NO        YES       NO
      ↓        ↓         ↓         ↓
   end--     start++   start++    end--

Core Idea:
Every iteration → identify a sorted half → check if target belongs there
→ eliminate the other half.
*/

class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start<=end)
        {
            int mid = start + (end-start)/2;
            if(nums[mid] == target)
            {
                        return mid;
            }
            if(nums[start]<=nums[mid])
            {
                if(target>=nums[start] && target<=nums[mid])
                {
                    if(nums[mid]>target)
                    {
                        end = mid - 1;
                    }
                }else{
                    start = mid + 1;
                }
            }else{
                if(target>=nums[mid] && target<=nums[end])
                {
                    if(target>nums[mid])
                    {
                        start = mid + 1;
                    }
                }else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
