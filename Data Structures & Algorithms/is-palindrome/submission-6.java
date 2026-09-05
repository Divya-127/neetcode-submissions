/*
Concept:
Use two pointers to compare characters from both ends while ignoring non-alphanumeric characters.

Key Insight:

* `i` moves from left → right.
* `j` moves from right → left.
* Skip characters that are not letters/digits.
* Compare the remaining characters case-insensitively.
* If any pair differs, it is not a palindrome.

Important:
Keep boundary checks inside the skip loops (`i < s.length()` and `j >= 0`) to avoid going out of bounds when the string contains only non-alphanumeric characters.

Pattern:
Two Pointers + Character Filtering

Complexity:
Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while(j>i)
        {
            while(i < s.length() && !(Character.isLetterOrDigit(s.charAt(i))))
            {
                i++;
            }
            while(j >= 0 && !(Character.isLetterOrDigit(s.charAt(j))))
            {
                j--;
            }
            if(i>j)
                break;
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
