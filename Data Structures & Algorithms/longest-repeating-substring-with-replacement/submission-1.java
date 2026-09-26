/*
Concept:
Find the longest substring that can be converted into one repeated
character using at most k replacements.

Key Insight:
For a window, keep the most frequent character unchanged and replace
all other characters.

replacements = windowLength - maxFrequency

If replacements > k, shrink the window from the left.

Maintain:
- freq[] = frequency of each character in the window
- maxFreq = highest frequency seen
- start = left boundary
- end = right boundary

Pattern:
Variable-Size Sliding Window + Frequency Array

Complexity:
Time: O(n)
Space: O(26) = O(1)
*/
class Solution {
    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];

        int start = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {

            int index = s.charAt(end) - 'A';
            freq[index]++;

            maxFreq = Math.max(maxFreq, freq[index]);

            // Characters that need to be replaced
            int replacements = (end - start + 1) - maxFreq;

            if (replacements > k) {
                freq[s.charAt(start) - 'A']--;
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}