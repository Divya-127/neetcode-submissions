class Solution {
    public String minWindow(String s, String t) {
        int start = 0;
        int end = t.length()-1;
        int[] freq1 = new int[58];
        StringBuilder st = new StringBuilder(s);
        String ans = "";
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            freq1[ch - 'A']++;
        }
        while(end<s.length())
        {
            if(itContains(start,end,s,freq1,t))
            {
                ans = st.substring(start,end+1);
                start++;   
            }else{
                end++;
            }
        }
        return ans;
    }
    public boolean itContains(int start,int end, String s, int[] freq1,String t)
    {
        int[] freq2 = new int[58];
        for(int i=start;i<=end;i++)
        {
            freq2[s.charAt(i)-'A']++;
        }
        for(int i = 0; i < 58; i++)
        {
            if(freq2[i] < freq1[i])
            return false;
        }
return true;
    }
}
