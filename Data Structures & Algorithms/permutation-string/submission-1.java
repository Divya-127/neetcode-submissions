/*
Concept:
Check whether s2 contains any permutation of s1 by comparing character
frequencies inside fixed-size windows.

Key Insight:
A permutation of s1 must have:
- The same length as s1
- Exactly the same frequency of every character

Maintain a sliding window of size s1.length() over s2.
For each window, build its frequency array and compare it with freq1.
If they match, the window is a permutation of s1.

Pattern:
Fixed-Size Sliding Window + Frequency Array

Complexity:
Time: O(n * m), where n = s2.length() and m = s1.length()
Space: O(26) = O(1)

Optimization:
Instead of rebuilding the window frequency array for every window,
remove the character leaving the window and add the character entering it.
This reduces the time complexity to O(n + m).


The key revision phrase I'd remember is:

> **Permutation/anagram inside a string → fixed-size window + frequency comparison.**

And this fits nicely with your progression:

**Valid Anagram → frequency counting**
**Group Anagrams → frequency as canonical key**
**Character Replacement → variable sliding window + frequency**
**Check Incl**
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] freq1 = new int[26];
        for(char c: s1.toCharArray())
        {
            freq1[c-'a']++;
        }
        int length1 = s1.length();
        int start = 0;
        int end = length1 -1;
        while(end<s2.length())
        {
            if(!(freqMatches(freq1,s2,start,end)))
            {
                start++;
                end++;
            }
            else{
                return true;
            }
        }
        return false;
    }
    public boolean freqMatches(int[] freq1, String s2,int start, int end)
    {
        int[] freq2 = new int[26];
        for(int i=start;i<=end;i++)
        {
            freq2[s2.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++)
        {
            if(freq1[i]!=freq2[i])
            {
                return false;
            }
        }
        return true;
    }
}
