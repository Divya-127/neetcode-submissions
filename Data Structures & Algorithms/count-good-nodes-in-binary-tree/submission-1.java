/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 # Count Good Nodes in Binary Tree (LeetCode 1448)

## Problem
Count the number of nodes in a binary tree that are **good**. A node is good if its value is greater than or equal to every node value along the path from the root to that node.

## Approach: DFS with Path Maximum

Use Depth-First Search (DFS) and carry `maxSoFar`, the maximum value encountered on the current root-to-node path.

### Key Insight
A node is good if:

`node.val >= maxSoFar`

For every node:
1. If the node is `null`, return.
2. If `node.val >= maxSoFar`, increment the good-node count.
3. Update the maximum for this path using `Math.max(maxSoFar, node.val)`.
4. Recursively explore the left and right children, passing the updated maximum.

**Important:** Count every good node, including nodes equal to their ancestors. Initialize the count to `0` and start DFS with `Integer.MIN_VALUE` so that the root is counted exactly once.

## Why DFS Works
Each node is evaluated using only the values on its own path from the root. The maximum is passed as an argument to each recursive call, so the left subtree's maximum does not incorrectly affect the right subtree.

Java passes primitive `int` arguments by value. Updating `maxSoFar` inside one recursive call does not change the caller's local variable or the value passed to a sibling subtree.

## Complexity
- **Time:** `O(n)` — every node is visited once.
- **Auxiliary space:** `O(h)` — recursion stack, where `h` is the tree height. Worst case `O(n)` for a skewed tree
*/

class Solution {
    int goodNodes;
    public int goodNodes(TreeNode root) {
        int maxSoFar = root.val;
        dfs(root,maxSoFar);
        return goodNodes;
    }
    public void dfs(TreeNode root,int maxSoFar) {
        if(root==null)
        {
            return;
        }
        if(root.val>=maxSoFar)
        {
            maxSoFar = root.val;
            goodNodes++;
        }
        dfs(root.left,maxSoFar);
        dfs(root.right,maxSoFar);
    }
}
