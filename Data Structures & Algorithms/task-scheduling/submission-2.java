/*
# Task Scheduler — LeetCode 621

## Problem

Given an array of tasks represented by characters and a cooldown `n`, execute all tasks in the minimum number of CPU cycles.

- Each cycle executes one task or remains idle.
- The same task must have at least `n` cycles between consecutive executions.
- Different tasks can execute in consecutive cycles.

## Approach: Greedy + Max Heap + Cooldown Priority Queue

### 1. Core intuition

The challenge is to decide which task to execute next while ensuring that no task violates its cooldown.

Two decisions must be handled independently:

1. **Task selection:** Which available task should execute next?
2. **Task availability:** When can a previously executed task run again?

We use a Max Heap to select tasks and a Priority Queue to track cooldowns. A HashMap stores the initial frequency of each task.

### 2. Data structures

**HashMap<Character, Integer> — Frequency counting**

Counts the occurrences of every task.

For example:

`tasks = [A, A, A, B, B, B, C]`

Frequencies:

- `A → 3`
- `B → 3`
- `C → 1`

We create one `Node` for each distinct task.

**Max Heap — Available tasks**

The Max Heap prioritizes nodes by their remaining frequency in descending order.

- `poll()` selects the available task with the highest remaining frequency.
- After executing a task, its remaining frequency decreases by one.
- If occurrences remain, the task enters the cooldown queue.

Choosing a task with a high remaining frequency helps avoid leaving too many repetitions for later cycles.

**Cooldown Priority Queue — Tasks waiting to become available**

This queue prioritizes nodes by `availableAt` in ascending order.

Each node stores:

- `val`: Task identifier.
- `frequency`: Remaining executions.
- `availableAt`: Earliest cycle at which the task can execute again.

The queue's earliest available task is always at its root.

### 3. Cooldown calculation

Suppose task `A` executes at time `0` and `n = 2`.

The next two cycles, time `1` and time `2`, cannot execute `A`. Therefore, its next eligible time is `3`.

General formula:

`availableAt = currentTime + n + 1`

After execution:

1. Decrease the task's frequency.
2. If its frequency is greater than zero, calculate `availableAt`.
3. Insert the task into the cooldown queue.

Tasks with zero remaining frequency are discarded because they are complete.

### 4. Algorithm

1. Count the frequency of every task using a HashMap.
2. Create a Max Heap ordered by remaining frequency.
3. Insert one node per distinct task into the Max Heap.
4. Create a cooldown Priority Queue ordered by `availableAt`.
5. Initialize `time = 0`.
6. Continue while either queue contains tasks:
   - Release all tasks whose `availableAt <= time` from the cooldown queue into the Max Heap.
   - If the Max Heap is not empty, execute its highest-frequency task.
   - Decrease the selected task's frequency.
   - If its frequency remains positive, calculate its next eligible time and insert it into the cooldown queue.
   - If the Max Heap is empty, the current cycle is idle.
   - Increment `time` by one.
7. Return `time`.

### 5. Java implementation

```java
import java.util.*;
*/
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
        HashMap<Character, Integer> counts = new HashMap<>();

        for (char task : tasks) {
            counts.put(task, counts.getOrDefault(task, 0) + 1);
        }

        // Highest remaining frequency first
        PriorityQueue<Node> maxHeap =
            new PriorityQueue<>((a, b) ->
                Integer.compare(b.frequency, a.frequency));

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            maxHeap.offer(new Node(entry.getKey(), entry.getValue()));
        }

        // Earliest cooldown completion first
        PriorityQueue<Node> coolDownQueue =
            new PriorityQueue<>((a, b) ->
                Integer.compare(a.availableAt, b.availableAt));

        int time = 0;

        while (!maxHeap.isEmpty() || !coolDownQueue.isEmpty()) {

            // Release all tasks whose cooldown has expired
            while (!coolDownQueue.isEmpty()
                    && coolDownQueue.peek().availableAt <= time) {
                maxHeap.offer(coolDownQueue.poll());
            }

            // Execute a task if one is available
            if (!maxHeap.isEmpty()) {
                Node current = maxHeap.poll();

                current.frequency--;

                // Schedule another execution if occurrences remain
                if (current.frequency > 0) {
                    current.availableAt = time + n + 1;
                    coolDownQueue.offer(current);
                }
            }

            // Advance one cycle, including idle cycles
            time++;
        }

        return time;
    }
}
/*
```

### 6. Walkthrough

Input:

`tasks = [A, A, A, B, B, B]`, `n = 2`

Initial frequencies:

- `A → 3`
- `B → 3`

One valid schedule is:

| Time | Action | Explanation |
|---:|---|---|
| 0 | A | A executes; next eligible at 3 |
| 1 | B | B executes; next eligible at 4 |
| 2 | Idle | Both tasks are cooling down |
| 3 | A | A's cooldown expires |
| 4 | B | B's cooldown expires |
| 5 | Idle | Neither task is available |
| 6 | A | A's final execution |
| 7 | B | B's final execution |

Total cycles = `8`.

When tasks have equal frequencies, either may be selected first. The exact schedule can differ, but the minimum cycle count remains the same.

### 7. Important implementation details

- **Use `while` to release tasks:** Multiple tasks may become available at the same time.
- **Use `<= time`:** A task becomes eligible at its exact `availableAt` cycle.
- **Use `||` in the outer loop:** Work remains while either the heap or cooldown queue is nonempty.
- **Handle idle cycles:** If the Max Heap is empty but the cooldown queue is not, time still advances.
- **Update a node only after polling it:** Do not change a node's frequency while it is still inside the Max Heap, because changing its priority does not automatically restore heap ordering.
- **Use separate comparators:** The Max Heap orders by frequency, while the cooldown queue orders by availability time.

## Complexity analysis

Let:

- `m` = total number of tasks.
- `u` = number of distinct task types.
- `T` = total number of scheduled cycles, including idle cycles.

**Time complexity:** `O(m + T × u log u)`

- Frequency counting takes `O(m)`.
- There are at most `u` tasks in the queues at any point.
- Each scheduled cycle performs queue and heap operations costing up to `O(log u)`.

**Auxiliary space complexity:** `O(u)`

The frequency map, Max Heap, and cooldown queue each store at most `u` distinct task nodes in total.

## Key takeaway

This problem demonstrates how to combine multiple data structures by giving each a distinct responsibility:

- **HashMap:** Count task frequencies.
- **Max Heap:** Choose the most frequent available task.
- **Cooldown Priority Queue:** Track when tasks can execute again.
- **Time variable:** Count execution and idle cycles.

**Pattern:** Greedy scheduling + priority queues + cooldown simulation.
*/