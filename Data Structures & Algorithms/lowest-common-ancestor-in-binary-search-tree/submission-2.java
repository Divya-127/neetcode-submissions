/*
## Lowest Common Ancestor of a BST — LeetCode 235

### Key Idea
Walk down from the root. If both p and q are greater than the current node, go
right; if both are smaller, go left. The first node that splits them (or equals
one of them) is the LCA.

### Complexity
* **Time:** `O(h)`
* **Space:** `O(1)` — iterative, no recursion stack

### Gotcha
A node can be its own ancestor, so `root == p` or `root == q` is already the
answer: the loop ends there without a special case.

### Confidence
high
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }
}