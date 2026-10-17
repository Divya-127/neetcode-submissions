class KthLargest {
    int k;
    PriorityQueue<Integer> heap;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>();
        for(int i=0;i<nums.length;i++){
            addToHeap(nums[i]);
        }
    }
    
    public int add(int val) {
        addToHeap(val);
        return heap.peek();
    }

    public void addToHeap(int val){
        heap.offer(val);
        if(heap.size()>k)
        {
            heap.poll();
        }
    }
}
