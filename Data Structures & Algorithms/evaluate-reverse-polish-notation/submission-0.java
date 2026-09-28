class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> token = new Stack<String>();
        for(String c: tokens)
        {
            if( c.equals("+"))
            {
                String a = token.pop();
                String b = token.pop();
                String d = String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
                token.push(d);
            }
            else if ( c.equals("-"))
            {
                String a = token.pop();
                String b = token.pop();
                String d = String.valueOf(Integer.parseInt(b) - Integer.parseInt(a));
                token.push(d);
            }
            else if( c.equals("*"))
            {
                String a = token.pop();
                String b = token.pop();
                String d = String.valueOf(Integer.parseInt(a) * Integer.parseInt(b));
                token.push(d);
            }else if (c.equals("/"))
            {
                String a = token.pop();
                String b = token.pop();
                String d = String.valueOf(Integer.parseInt(b) / Integer.parseInt(a));
                token.push(d);
            }else{
                token.push(c);
            }
        }
        return Integer.parseInt(token.peek());
    }
}
