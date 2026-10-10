/*
## Permutation in String — LeetCode 567

### Key Idea
A permutation of s1 is any window of s2 of length s1.length() with the same
letter counts. Keep one fixed-size window over s2 and track `matches`: how many
of the 26 letters currently have equal counts in the window and in s1.
Sliding the window changes only two counts, so `matches` updates in O(1).

### Complexity
* **Time:** `O(n)` where n = s2.length()
* **Space:** `O(1)` (two arrays of size 26)

### Gotcha
When a count changes, check both directions: it can become equal (matches++)
or stop being equal (matches--). Check for `matches == 26` before sliding
so the first window is not skipped.

### Confidence
medium
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }

        int[] need = new int[26];
        int[] win = new int[26];
        for (int i = 0; i < n; i++) {
            need[s1.charAt(i) - 'a']++;
            win[s2.charAt(i) - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (need[i] == win[i]) {
                matches++;
            }
        }

        for (int r = n; r < m; r++) {
            if (matches == 26) {
                return true;
            }

            int in = s2.charAt(r) - 'a';
            win[in]++;
            if (win[in] == need[in]) {
                matches++;
            } else if (win[in] == need[in] + 1) {
                matches--;
            }

            int out = s2.charAt(r - n) - 'a';
            win[out]--;
            if (win[out] == need[out]) {
                matches++;
            } else if (win[out] == need[out] - 1) {
                matches--;
            }
        }
        return matches == 26;
    }
}