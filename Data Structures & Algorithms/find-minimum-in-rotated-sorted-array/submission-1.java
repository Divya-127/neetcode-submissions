/*
Concept:
Find the minimum element in a sorted array that has been rotated at some
unknown pivot.

Key Insight:
A rotated sorted array consists of two sorted portions. The minimum is the
point where the sorted order breaks.

Use Binary Search to determine which side contains the rotation point.

Compare nums[mid] with nums[end]:

1. nums[mid] > nums[end]
   - mid is in the left/high sorted portion.
   - The rotation point (minimum) must be to the RIGHT of mid.
   - Therefore:
         start = mid + 1

2. nums[mid] < nums[end]
   - mid is in the right/low sorted portion.
   - The minimum could be at mid itself or somewhere to its LEFT.
   - Therefore:
         end = mid
   - We keep mid because it can itself be the minimum.

Optimization:
If nums[start] < nums[end], the current search range is already sorted,
so nums[start] is immediately the minimum.

The loop continues until:
    start == end

At that point, the search space contains exactly one element, which must be
the minimum.

Pattern:
Binary Search on a Rotated / Partially Sorted Array

Complexity:
Time: O(log n)
Space: O(1)

Mental Model:
Compare MID with END

mid > end
    ↓
Rotation is to the RIGHT
    ↓
start = mid + 1

mid < end
    ↓
Rotation is at MID or to the LEFT
    ↓
end = mid

Eventually:
    start == end
        ↓
    Minimum found
*/
class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        while(start<end)
        {
            int mid = start + (end-start)/2;
            if(nums[start]<nums[end])
            {
                return nums[start];
            }

            if(nums[mid]>nums[end])
            {
                start = mid +1;
            }else
            {
                end = mid;
            }
        }
        return nums[start];
    }
}
