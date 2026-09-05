/*
 * Concept:
 * - Count the frequency of each number using a HashMap.
 * - Use a min-heap storing [number, frequency].
 * - Keep the heap size limited to k.
 *
 * Key Insight:
 * For Top K Frequent, maintain a min-heap of size k.
 * The least frequent element among the current top k stays
 * at the root. When a new element makes the heap size > k,
 * remove the root.
 *
 * Why Min Heap?
 * We want to easily remove the least frequent element,
 * so that only the k most frequent elements remain.
 *
 * Pattern:
 * HashMap + Min Heap (Top K)
 *
 * Complexity:
 * Time: O(n + m log k)
 * Space: O(m + k)
 * where m = number of unique elements.
 */
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