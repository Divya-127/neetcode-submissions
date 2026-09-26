/*
Concept:
Find the longest contiguous substring containing only unique characters.

Key Insight:
Maintain a variable-size sliding window [start, end].
- Expand end while the new character is unique.
- When a duplicate appears, move start forward and remove characters
  from the HashSet until the duplicate is removed.
- Track the maximum valid window length.

Invariant:
HashSet contains exactly the characters currently inside the window.

Pattern:
Variable-Size Sliding Window + HashSet

Complexity:
Time: O(n)
Space: O(min(n, character-set-size))
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hash = new HashSet<Character>();
        char[] str = s.toCharArray();
        int start = 0;
        int end = 0;
        int ans = 0;
        while(end<s.length())
        {
            if(!(hash.contains(str[end])))
            {
                ans = Math.max(ans,end-start+1);
                hash.add(str[end]);
                end++;
            }else{
                while((start<end)&&hash.contains(str[end]))
                {
                    hash.remove(str[start++]);
                }
            }
        }
        return ans;
    }
}
