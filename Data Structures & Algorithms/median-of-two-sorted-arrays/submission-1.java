/*

* Problem: Median of Two Sorted Arrays
* LeetCode: 4
*
* Core idea:
* ---
* Instead of merging the two sorted arrays (O(m+n)), we binary-search
* for the correct partition between the two arrays.
*
* Suppose the total number of elements is N.
* We want the left side of the partition to contain:
*
* ```
   half = (m + n + 1) / 2
  ```
*
* elements.
*
* Let:
*
* ```
   i = number of elements taken from nums1
  ```
* ```
   j = number of elements taken from nums2
  ```
*
* Since the left side must contain exactly `half` elements:
*
* ```
   j = half - i
  ```
*
* Therefore, once we choose `i`, `j` is automatically determined.
*
*
* Example:
* ---
*
* nums1 = [1, 3, 8, 10]
* nums2 = [2, 4, 5, 9]
*
* Total = 8, so half = 4.
*
* Suppose:
*
* ```
   i = 2
  ```
* ```
   j = 4 - 2 = 2
  ```
*
* Partition:
*
* ```
   nums1 = [1, 3 | 8, 10]
  ```
* ```
   nums2 = [2, 4 | 5, 9]
  ```
*
* The four important boundary values are:
*
* ```
   nums1LeftMax  = 3
  ```
* ```
   nums1RightMin = 8
  ```
* ```
   nums2LeftMax  = 4
  ```
* ```
   nums2RightMin = 5
  ```
*
*
* Valid partition:
* ---
* Because both arrays are individually sorted, we only need to ensure
* that every element on the left is <= every element on the right.
*
* We only need to check the two cross-array comparisons:
*
* ```
   nums1LeftMax <= nums2RightMin
  ```
* ```
   nums2LeftMax <= nums1RightMin
  ```
*
* If both are true, the partition is correct.
*
*
* How binary search moves:
* ---
*
* Case 1:
*
* ```
   nums1LeftMax > nums2RightMin
  ```
*
* We have taken too many elements from nums1.
* Therefore, move the partition in nums1 to the LEFT:
*
* ```
   high = i - 1
  ```
*
*
* Case 2:
*
* ```
   nums2LeftMax > nums1RightMin
  ```
*
* We have taken too few elements from nums1.
* Therefore, move the partition in nums1 to the RIGHT:
*
* ```
   low = i + 1
  ```
*
*
* Once the partition is valid:
*
* ```
   leftMax  = max(nums1LeftMax, nums2LeftMax)
  ```
* ```
   rightMin = min(nums1RightMin, nums2RightMin)
  ```
*
*
* Finding the median:
* ---
*
* If total number of elements is ODD:
*
* ```
   median = leftMax
  ```
*
* We use `(m + n + 1) / 2` so that the extra element for an odd-sized
* array belongs to the left partition.
*
*
* If total number of elements is EVEN:
*
* ```
   median = (leftMax + rightMin) / 2.0
  ```
*
*
* Boundary handling:
* ---
*
* The partition can occur at either end of an array.
*
* If there is no element on the left:
*
* ```
   leftMax = Integer.MIN_VALUE
  ```
*
* If there is no element on the right:
*
* ```
   rightMin = Integer.MAX_VALUE
  ```
*
* This lets the same comparison logic work without special cases.
*
*
* Why search the smaller array?
* ---
*
* Binary search is performed on nums1.
* To guarantee the smallest possible search space, ensure:
*
* ```
   nums1.length <= nums2.length
  ```
*
* If not, swap the arrays.
*
* Therefore the complexity is:
*
* ```
   Time  : O(log(min(m, n)))
  ```
* ```
   Space : O(1)
  ```
*
*
* Mental model:
* ---
*
* Don't think:
*
* ```
   "Find the median element."
  ```
*
* Think:
*
* ```
   "Find the partition where exactly half the elements are on the
  ```
* ```
    left, and nothing on the left is greater than anything on the
  ```
* ```
    right."
  ```
*
* Once that partition is found, the median is determined entirely by
* the largest value on the left and the smallest value on the right.
*
*
* Important reusable pattern:
* ---
*
* This is Binary Search on a PARTITION rather than binary search on
* an element/value.
*
* We are searching for the correct position `i`, while `j` is derived
* from it:
*
* ```
   j = half - i
  ```
*
* The four boundary values tell us whether the partition is too far
* left or too far right.
  */

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
