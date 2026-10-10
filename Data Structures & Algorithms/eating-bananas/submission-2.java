/*
## Koko Eating Bananas — LeetCode 875

### Key Idea
Binary search on the answer k in [1, max(piles)]. For a candidate k, hours needed
is the sum of ceil(pile / k). Feasibility is monotonic in k, so find the smallest
k that finishes within h hours.

### Complexity
* **Time:** `O(n log M)` where M = max(piles)
* **Space:** `O(1)`

### Gotcha
Accumulate hours in a `long` and exit early once it exceeds h. Compute the
ceiling as `(pile + k - 1) / k` in `long` so the sum cannot overflow.
The answer always exists at k = max(piles) (h >= piles.length), so no `-1` case.

### Confidence
medium
*/
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = 0;
        for (int p : piles) {
            hi = Math.max(hi, p);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFinish(piles, mid, h)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        long hours = 0;
        for (int p : piles) {
            hours += ((long) p + k - 1) / k;
            if (hours > h) {
                return false;
            }
        }
        return true;
    }
}