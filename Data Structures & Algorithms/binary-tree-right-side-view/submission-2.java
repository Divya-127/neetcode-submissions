/*# Binary Tree Right Side View (LeetCode 199)

## Approach: Breadth-First Search (BFS) using a Queue

### Core Idea
The right-side view contains the rightmost visible node at every level of the binary tree.

We can reuse the standard **Level Order Traversal (BFS)** pattern. Instead of storing every node's value at each level, we store only the value of the last node processed at that level.

### Algorithm
1. Initialize an empty result list `ans` and a queue.
2. Add the root to the queue only if it is not `null`.
3. While the queue is not empty:
   - Capture `size = queue.size()`, representing the number of nodes at the current level.
   - Process exactly `size` nodes:
     - Remove the front node using `poll()`.
     - Enqueue its left child if it exists.
     - Enqueue its right child if it exists.
     - If `size == 1`, add the current node's value to `ans`, because it is the last node processed at this level.
     - Decrement `size`.
4. Return `ans`.

### Key Insight: Last Node at Each Level
The important pattern is **BFS + fixed level size + recording the last processed node**.

Since BFS processes nodes from left to right when we enqueue the left child before the right child, the last node processed at each level is the rightmost node at that level.

For example, if the nodes at a level are processed in the order `[2, 3]`, we record `3`. If a level contains only node `5`, we record `5`, even if it is a left child.

### Java Implementation

```java
*/
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (size == 1) {
                    ans.add(node.val);
                }

                size--;
            }
        }

        return ans;
    }
}
/*
```

### Complexity Analysis
- **Time Complexity:** `O(n)` — every node is enqueued and processed once.
- **Auxiliary Space Complexity:** `O(w)` — the queue holds nodes from the current and next levels, where `w` is the maximum width of the tree.
- **Total Space Complexity:** `O(n)` when including the result list.

### Mental Model
Think of each level as a separate batch:

- Process all nodes in that batch from left to right.
- Add their children to the queue for the next level.
- Record the last node in the current batch.
- Repeat until the queue is empty.

**Reusable pattern:** Standard level-order BFS can solve right-side view by recording only the last node of each level. If we instead traverse each level from right to left, we can record the first node of each level.
*/