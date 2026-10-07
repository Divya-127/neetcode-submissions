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
    boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        calculateHeight(root);
        return ans;
    }
    private int calculateHeight(TreeNode root) {
    if (root == null) {
        return -1;
    }
    int leftHeight = calculateHeight(root.left);
    int rightHeight = calculateHeight(root.right);
    if(ans){
        ans = Math.abs(leftHeight-rightHeight)>1?false:true;
    }
    return Math.max(leftHeight, rightHeight) + 1;
    }
}
