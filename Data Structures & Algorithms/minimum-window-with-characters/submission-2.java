class Solution {
    public String minWindow(String s, String t) {
        int start = 0;
        int end = 0;
        int[] freq1 = new int[58];
        int[] freq2 = new int[58];
        int minLength = Integer.MAX_VALUE;
        String ans = "";
        if (t.length() > s.length()) { return ""; }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            freq1[ch - 'A']++;
        }
        while(end<s.length())
        {
            freq2[s.charAt(end)-'A']++;
            while(contains(freq1,freq2))
            {
                int currentLength = end - start + 1;
                if(currentLength < minLength)
                {
                    minLength = currentLength;
                    ans = s.substring(start,end+1);
                }
                freq2[s.charAt(start)-'A']--;
                start++;
            }
            end++;
        }
        return ans;
    }
    public boolean contains(int[] freq1, int[] freq2) 
    { 
        for (int i = 0; i < 58; i++) 
        { 
            if (freq2[i] < freq1[i]) 
            { 
                return false; 
            } 
        } 
        return true; 
    }
}
