class Solution {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while(j>i)
        {
            if(!(Character.isLetterOrDigit(s.charAt(i))))
            {
                i++;
            }
            if(!(Character.isLetterOrDigit(s.charAt(j))))
            {
                j--;
            }
            if (!(Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))))
            {
                return false;
            }
            i++;
            j--;
        }
    return true;
    }
}
