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
