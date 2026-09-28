class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int val) {
        stack.push(val);
        if(!minStack.isEmpty())
        {
            if(minStack.peek()>=val)
            {
                minStack.push(val);
            }
        }else{
            minStack.push(val);
        }
    }
    
    public void pop() {
        if(!stack.isEmpty())
        {
            int val = stack.pop();
            if(!(minStack.isEmpty()) && (val == minStack.peek()))
            {
                minStack.pop();
            }
        }
    }
    
    public int top() {
       
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
