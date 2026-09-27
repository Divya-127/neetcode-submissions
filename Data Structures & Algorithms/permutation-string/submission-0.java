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
