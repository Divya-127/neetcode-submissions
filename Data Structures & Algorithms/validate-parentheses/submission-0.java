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
