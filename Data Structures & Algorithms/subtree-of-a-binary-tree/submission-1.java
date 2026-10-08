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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null)
        {
            return false;
        }
        if(root.val == subRoot.val)
        {
            if(isSameTree(root,subRoot)){
                return true;
            }
        }
        boolean leftSubTree = isSubtree(root.left,subRoot);
        boolean rightSubTree = isSubtree(root.right,subRoot);
        return leftSubTree || rightSubTree;
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p == null && q!= null)||(p!=null && q == null))
        {
            return false;
        }
        if(p == null && q == null)
        {
            return true;
        }
        if(p!=null && q!=null && p.val != q.val)
        {
            return false;
        }
        boolean leftTree = isSameTree(p.left,q.left);
        boolean righTree = isSameTree(p.right,q.right);
        return leftTree&&righTree;   
    }
}
