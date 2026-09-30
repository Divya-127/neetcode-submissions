/*
Concept:
For each day, find how many days we need to wait until a warmer temperature.

Key Insight:
Use a monotonic decreasing stack of INDICES.
- Stack stores days that are still waiting for a warmer temperature.
- If the current temperature is warmer than the temperature at the stack top,
  the current day resolves that previous day.
- Pop the previous index and calculate:
      waiting days = current index - previous index
- Keep popping while the current temperature is warmer, because one temperature
  can resolve multiple previous days.
- Push the current index after resolving all possible days.
- Store indices instead of temperatures so we can calculate the distance and
  update the correct result position.

Pattern:
Monotonic Decreasing Stack + Next Greater Element

Complexity:
Time: O(n)
Space: O(n)

Mental Model:
Stack = days waiting for a warmer temperature

Current day arrives
      ↓
Warmer than stack top?
      ↓
YES → Pop → Calculate waiting days
      ↓
Keep checking
      ↓
Push current day
*/

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] result = new int[temperatures.length];
        for(int i=0;i<temperatures.length;i++)
        {
            if(stack.isEmpty())
            {
                stack.push(i);
            }
            else{
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i])    {
                    result[stack.peek()] = i - stack.peek(); 
                    stack.pop();
                }
                stack.push(i);
            }   
        }
        return result;
    }
}
