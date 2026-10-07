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
 */

class Solution {
    public boolean isBalanced(TreeNode root) {
        return calculateHeight(root)!= -2;
    }
    private int calculateHeight(TreeNode root) {
    if (root == null) {
        return -1;
    }

    int leftHeight = calculateHeight(root.left);
    if (leftHeight == -2) {
        return -2;
    }

    int rightHeight = calculateHeight(root.right);
    if (rightHeight == -2) {
        return -2;
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -2;
    }

    return Math.max(leftHeight, rightHeight) + 1;
}
}
