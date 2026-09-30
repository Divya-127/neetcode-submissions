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
