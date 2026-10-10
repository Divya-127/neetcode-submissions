/*
# Binary Tree Level Order Traversal (LeetCode 102)

## Approach: Breadth-First Search (BFS) using a Queue

### Core Idea
Level Order Traversal visits a binary tree one level at a time, from left to right. This is naturally implemented using **BFS with a FIFO queue**.

### Algorithm
1. Initialize an empty result list `ans` to store the values of each level.
2. Create a queue and add the root only if it is not `null`.
3. While the queue is not empty:
   - Capture the current queue size. This represents the number of nodes at the current level.
   - Create a new list `arr` for that level.
   - Process exactly `size` nodes:
     - Remove the front node using `poll()`.
     - Add its value to `arr`.
     - Enqueue its left child if it exists.
     - Enqueue its right child if it exists.
     - Decrement `size`.
   - Add `arr` to `ans`.
4. Return `ans`.

### Key Insight: Fix the Level Size
The most important step is capturing `queue.size()` **before** processing the current level.

While processing nodes, their children are added to the queue. These children belong to the next level, so we must process only the number of nodes that were originally in the queue for the current level.

This is why the inner loop uses a fixed `size` count rather than continuing until the queue becomes empty.

### Java Implementation

```java
*/
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> arr = new ArrayList<>();

            while (size > 0) {
                TreeNode node = queue.poll();
                arr.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                size--;
            }

            ans.add(arr);
        }

        return ans;
    }
}
/*
```

### Complexity Analysis
- **Time Complexity:** `O(n)` — each node is enqueued and processed once.
- **Auxiliary Space Complexity:** `O(w)` for the queue, where `w` is the maximum width of the tree.
- **Total Space Complexity:** `O(n)` when including the result list containing all node values.

### Mental Model
Think of the queue as a waiting line:

- Process every node currently waiting at the start of a level.
- Collect their values into one list.
- Add their existing children to the end of the queue.
- Once the original level's nodes are processed, the queue contains the next level.

**Pattern to remember:** BFS + queue + fixed level size = level-by-level tree traversal.

This pattern is also useful for problems involving minimum depth, level averages, zigzag traversal, and other tree problems that operate level by level.
*/