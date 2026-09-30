/*
Concept:
Find the number of car fleets that reach the target without cars passing
each other.

Key Insight:
Calculate each car's arrival time:
    time = (target - position) / speed

Sort cars by position in descending order so we process cars from closest
to the target to farthest behind.

For each car:
- If its arrival time > the fleet ahead's arrival time, it cannot catch
  that fleet, so it forms a new fleet.
- If its arrival time <= the fleet ahead's arrival time, it catches that
  fleet before or exactly at the destination, so it becomes part of the
  same fleet.
- Therefore, only new fleet arrival times need to be stored in the stack.

Pattern:
Sorting + Monotonic Stack

Complexity:
Time: O(n log n)
Space: O(n)

Mental Model:
Sort ahead → behind
        ↓
Calculate arrival time
        ↓
Slower than fleet ahead → New fleet
Faster/equal → Join fleet

Stack size = number of car fleets
*/
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        Stack<Double> stack = new Stack<Double>();
        int[][] cars = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));
        for(int i = 0;i<position.length;i++)
        {
            if(stack.isEmpty())
            {
                stack.push((double)(target-cars[i][0])/cars[i][1]);
            }else{
                if((double)(target-cars[i][0])/cars[i][1]>stack.peek())
                {
                    stack.push((double)(target-cars[i][0])/cars[i][1]);
                }
            }
        }
        return stack.size();
    }
}
