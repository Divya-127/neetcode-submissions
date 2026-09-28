/*
Concept:
Evaluate an arithmetic expression written in Reverse Polish Notation (RPN).

Key Insight:
Use a Stack to evaluate the expression from left to right.
- Number → push onto the stack.
- Operator → pop the two most recent operands, apply the operator,
  then push the result back.
- The first popped value is the right operand and the second popped
  value is the left operand, which is important for - and /.

Pattern:
Stack + Expression Evaluation

Complexity:
Time: O(n)
Space: O(n)

Mental Model:
Number → Push
Operator → Pop 2 → Calculate → Push Result
*/
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
