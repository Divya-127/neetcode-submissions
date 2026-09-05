/*
 * Concept:
 * - Create a frequency signature for each string.
 * - The signature contains the count of each character (a-z).
 * - Anagrams have identical frequency signatures, so they share
 *   the same HashMap key.
 *
 * Key Insight:
 * Instead of sorting every string, count its characters.
 * Two strings are anagrams if all 26 character frequencies match.
 *
 * Pattern:
 * HashMap + Frequency Array + Canonical Key
 *
 * Complexity:
 * Time: O(m * n)
 * Space: O(m * n)
 *   where m = number of strings and n = maximum string length
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] freq = new int[26];

            for (char c : str.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder key = new StringBuilder();

            for (int count : freq) {
                key.append(count).append('#');
            }

            String hashKey = key.toString();

            map.computeIfAbsent(hashKey, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}