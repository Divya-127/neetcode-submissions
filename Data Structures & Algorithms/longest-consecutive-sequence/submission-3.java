/*
## Longest Consecutive Sequence — LeetCode 128

### Key Idea
Put every number in a HashSet. A number starts a sequence only if `num - 1` is
not in the set; from each start, walk upward while `num + 1` exists.
Every element is walked at most once, so the total work is O(n).

### Complexity
* **Time:** `O(n)`
* **Space:** `O(n)`

### Gotcha
Loop over the **set**, not the original array. Duplicates in the array would
re-walk the same sequence repeatedly and degrade to O(n²).

### Confidence
medium
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num;
                int len = 1;
                while (set.contains(curr + 1)) {
                    curr++;
                    len++;
                }
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }
}