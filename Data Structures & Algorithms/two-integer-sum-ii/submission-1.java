/*
Concept:
Use two pointers on the sorted array: one at the beginning and one at the end.

Key Insight:
Because the array is sorted:

* Sum > target → move right pointer left to decrease the sum.
* Sum < target → move left pointer right to increase the sum.
* Sum == target → found the pair.

Pattern:
Two Pointers + Sorted Array

Important:
Use `i < j` because the same element cannot be used twice.
Return `i + 1` and `j + 1` because the problem uses 1-based indexing.

Complexity:
Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length-1;
        int[] ans = new int[2];
        while(j >= i)
        {
            if((numbers[i]+numbers[j])>target)
            {
               j--; 
            }
            else if(numbers[i]+numbers[j]<target)
            {
                i++;
            }
            else
            {
                ans[0] = i+1;
                ans[1] = j+1;
                return ans;
            }
        }
        return ans;
    }
}
