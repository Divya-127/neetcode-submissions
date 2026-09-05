/*
 * Concept:
 * - Count the frequency of each character in both strings.
 * - Compare the frequency maps.
 *
 * Key Insight:
 * Two strings are anagrams if and only if every character
 * appears the same number of times in both strings.
 *
 * Pattern:
 * HashMap + Frequency Counting
 *
 * Complexity:
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
        return false;
        HashMap<Character,Integer> hm1 = new HashMap<Character,Integer>();
        HashMap<Character,Integer> hm2 = new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++)
        {
            hm1.merge(s.charAt(i),1,Integer::sum);
        }
        for(int i=0;i<t.length();i++)
        {
            hm2.merge(t.charAt(i),1,Integer::sum);
        }
        for (Character key : hm2.keySet()) {  
            if(!Objects.equals(hm1.get(key), hm2.get(key)))
            {
                return false;
            }
        }
        return true;
    }
}
