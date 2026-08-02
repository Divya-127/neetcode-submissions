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