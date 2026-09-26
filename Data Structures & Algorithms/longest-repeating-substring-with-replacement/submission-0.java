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