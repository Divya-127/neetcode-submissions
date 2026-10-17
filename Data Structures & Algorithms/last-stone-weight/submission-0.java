class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int stone: stones)
        {
            heap.offer(stone);
        }
        while(heap.size()>1)
        {
            int stone1 = heap.poll();
            int stone2 = heap.poll();
            int stoneValue = Math.abs(stone1 - stone2);
            if(stoneValue>0)
            heap.offer(stoneValue);
        }
        if(heap.size()==0)
        return 0;
        else
        return heap.poll();
    }
}
