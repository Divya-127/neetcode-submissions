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
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        calculateSum(root);
        return ans;
    }
    public int calculateSum(TreeNode root){
        if(root == null)
        {
            return 0;
        }
        int leftSum = Math.max(0,calculateSum(root.left));
        int rightSum = Math.max(0,calculateSum(root.right));
        ans = Math.max(ans,root.val+leftSum+rightSum);
        return root.val + Math.max(leftSum,rightSum);   
    }
}
