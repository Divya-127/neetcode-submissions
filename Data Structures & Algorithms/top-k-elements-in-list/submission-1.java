class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // Count frequency
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }

        // Min heap: [number, frequency]
        PriorityQueue<int[]> heap =
            new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Keep only k most frequent numbers
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {

            heap.offer(new int[] {
                entry.getKey(),
                entry.getValue()
            });

            if (heap.size() > k) {
                heap.poll();
            }
        }

        // Extract answer
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = heap.poll()[0];
        }

        return ans;
    }
}