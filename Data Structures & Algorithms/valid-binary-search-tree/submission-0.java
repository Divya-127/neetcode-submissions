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
    public boolean isValidBST(TreeNode root) {
        int lowerBound = Integer.MIN_VALUE;
        int upperBound = Integer.MAX_VALUE;
        return isValidBST(root,lowerBound,upperBound);
    }
    public boolean isValidBST(TreeNode node, int lowerBound, int upperBound)
    {
        if(node == null)
        return true;
        if(node.val<=lowerBound || node.val>=upperBound)
        {
            return false;
        }
        
            boolean isValidBSTLeft =  isValidBST(node.left,lowerBound,node.val);
            boolean isValidBSTRight = isValidBST(node.right,node.val,upperBound);
            return isValidBSTLeft && isValidBSTRight;
    }
}
