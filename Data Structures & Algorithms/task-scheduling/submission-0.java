class Solution {
    class Node {
        char val;
        int availableAt;
        int frequency;

        Node(char val, int frequency) {
            this.val = val;
            this.frequency = frequency;
            this.availableAt = 0;
        }
    }

    public int leastInterval(char[] tasks, int n) {

        // 1. Count the frequency of each task
        HashMap<Character, Integer> counts = new HashMap<>();

        for (char task : tasks) {
            counts.put(task, counts.getOrDefault(task, 0) + 1);
        }

        // 2. Max Heap: highest remaining frequency first
        PriorityQueue<Node> maxHeap =
            new PriorityQueue<>((a, b) ->
                Integer.compare(b.frequency, a.frequency));

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            maxHeap.offer(node);
        }

        // 3. Cooldown queue: earliest available time first
        PriorityQueue<Node> coolDownQueue =
            new PriorityQueue<>((a, b) ->
                Integer.compare(a.availableAt, b.availableAt));

        int time = 0;

        // Continue while tasks are ready or cooling down
        while (!maxHeap.isEmpty() || !coolDownQueue.isEmpty()) {

            // 4. Release every task whose cooldown has expired
            while (!coolDownQueue.isEmpty()
                    && coolDownQueue.peek().availableAt <= time) {

                Node ready = coolDownQueue.poll();
                maxHeap.offer(ready);
            }

            // 5. Execute a task if one is available
            if (!maxHeap.isEmpty()) {

                Node current = maxHeap.poll();

                // One execution completed
                current.frequency--;

                // If executions remain, start its cooldown
                if (current.frequency > 0) {
                    current.availableAt = time + n + 1;
                    coolDownQueue.offer(current);
                }
            }

            // 6. One cycle has passed, whether task or idle
            time++;
        }

        return time;
    }
}
