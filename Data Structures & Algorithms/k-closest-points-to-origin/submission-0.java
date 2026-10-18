class Solution {
    class PointInfo {
        double distance;
        int[] point;

        PointInfo(double distance, int[] point) {
            this.distance = distance;
            this.point = point;
        }
    }
    public int[][] kClosest(int[][] points, int k) {
      PriorityQueue<PointInfo> maxHeap =new PriorityQueue<>((a, b) -> Double.compare(b.distance, a.distance));
      for(int[] point: points)
      {
        PointInfo pointInfo = new PointInfo(getDistance(point),point);
        maxHeap.offer(pointInfo);
        if(maxHeap.size()>k)
        {
            maxHeap.poll();
        }
      }
      int[][] ans = new int[k][2];
      int i = 0;
      while(maxHeap.size()!=0)
      {
        ans[i++] = maxHeap.poll().point;
      }
      return ans;
    }
    public double getDistance(int[] point)
    {
        return Math.sqrt((point[0]*point[0])+(point[1]*point[1]));
    }
}
