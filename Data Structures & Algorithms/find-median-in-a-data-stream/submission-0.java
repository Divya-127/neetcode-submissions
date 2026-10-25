class MedianFinder {
    PriorityQueue<Integer> lower;
    PriorityQueue<Integer> upper;

    public MedianFinder() {
        lower = new PriorityQueue<>(Collections.reverseOrder());
        upper = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        lower.add(num);
        upper.offer(lower.poll());
        if(upper.size()>lower.size())
        {
            lower.offer(upper.poll());
        }
    }
    
    public double findMedian() {
        if(lower.size()>upper.size())
        {
            return lower.peek();
        }
        return ((double) lower.peek() + upper.peek())/2.0;
    }
}
