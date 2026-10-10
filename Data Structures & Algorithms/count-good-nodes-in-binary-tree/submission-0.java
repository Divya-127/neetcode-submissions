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
