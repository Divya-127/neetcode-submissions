/*
Concept:
Check whether every opening bracket is closed by the correct closing
bracket in the correct order.

Key Insight:
Use a Stack because brackets must be matched in LIFO order.
- Opening bracket → push onto stack.
- Closing bracket → stack must not be empty and its top must be the
  corresponding opening bracket.
- Pop when a valid pair is matched.
- After processing the entire string, the stack must be empty.

Pattern:
Stack + LIFO Matching

Complexity:
Time: O(n)
Space: O(n)

Mental Model:
Opening → Push
Closing → Match Top → Pop
End → Stack must be empty
*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray())
        {
            if( c == '[' || c == '(' || c == '{' )
            {
                stack.push(c);
            }
            if( c == ']' || c == ')' || c == '}' )
            {
                char a;
                if(!stack.isEmpty())
                a = stack.peek();
                else
                return false;
                if ( c == ']' && a == '[')
                {
                    stack.pop();
                }else if( c == ')' && a == '(')
                {
                    stack.pop();
                }else if( c == '}' && a == '{')
                {
                    stack.pop();
                }
                else{
                    return false;
                }
            }
        }
         if(stack.isEmpty())
            {
                return true;
            }
            return false;
    }
}
