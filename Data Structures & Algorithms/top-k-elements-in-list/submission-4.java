/*
## Top K Frequent Elements — LeetCode 347

### Key Idea
Count frequencies, then bucket values by frequency (index = count, max count = n).
Walk the buckets from the highest frequency down and stop as soon as k values
have been collected.

### Complexity
* **Time:** `O(n)`
* **Space:** `O(n)`

### Gotcha
Stop the moment `idx == k`. A bucket can hold more values than are still needed
(ties at the cutoff), and writing past `ans[k - 1]` throws
ArrayIndexOutOfBoundsException. Many tests hide this because the answer is
guaranteed unique.

### Confidence
medium
*/
class Solution {
    @SuppressWarnings("unchecked")
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.merge(x, 1, Integer::sum);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int f = e.getValue();
            if (buckets[f] == null) {
                buckets[f] = new ArrayList<>();
            }
            buckets[f].add(e.getKey());
        }

        int[] ans = new int[k];
        int idx = 0;
        for (int f = buckets.length - 1; f >= 1; f--) {
            if (buckets[f] == null) {
                continue;
            }
            for (int x : buckets[f]) {
                ans[idx++] = x;
                if (idx == k) {
                    return ans;
                }
            }
        }
        return ans;
    }
}