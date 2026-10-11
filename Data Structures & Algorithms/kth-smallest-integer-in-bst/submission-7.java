/**
## Kth Smallest Element in a BST — LeetCode 230

### Approach: Inorder Traversal (DFS)

**Core idea:** In a Binary Search Tree (BST), inorder traversal visits nodes in ascending order because every node in the left subtree is smaller than the current node, and every node in the right subtree is larger.

Therefore, the **kth node visited during inorder traversal is the kth smallest element**.

### Algorithm
1. Initialize `count = 0` and traverse the tree using DFS.
2. Recursively visit the left subtree first.
3. After returning from the left subtree, check whether the kth element has already been found. If so, stop the current recursive call.
4. Increment `count` to account for the current node.
5. If `count == k`, store the current node's value in `ans` and return.
6. Otherwise, recursively visit the right subtree.

### Why the stopping condition matters

A `return` exits only the current recursive call; it does not automatically stop all its parent calls. The shared `count` variable allows ancestor calls to recognize that the kth element has already been found and stop processing unnecessary nodes.

### Complexity Analysis
- **Time:** \(O(h+k)\) in the usual analysis, where \(h\) is the tree height; worst case \(O(n)\).
- **Auxiliary space:** \(O(h)\) for the recursion stack.
- Here, \(n\) is the number of nodes and \(h\) is the tree height.

### Mental Model

**BST → Inorder traversal → Sorted sequence → Count the visits → Return the kth value.**

For example, if inorder traversal produces `[2, 3, 4, 5]` and `k = 4`, the answer is `5`.
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
class Solution {
    public int kthSmallest(TreeNode root, int k) {
of a binary tree node.
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
 */
class Solution {
    int count;
    int ans;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        kSmallest(root,k);
        return ans;
    }
    public void kSmallest(TreeNode root,int k){
        if(root == null){
            return;
        }
        kSmallest(root.left,k);
        if (count >= k) {
        return;
    }
        count++;
        if(count == k){
            ans = root.val;
            return;
        }
        if(count<=k)
        kSmallest(root.right,k);
    }
}

