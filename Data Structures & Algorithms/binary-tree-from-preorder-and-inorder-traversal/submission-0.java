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
    int preIndex;
    Map<Integer,Integer> inorderMap = new HashMap<Integer,Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        inorderMap.clear();
        for(int i=0;i<inorder.length;i++)
        {
            inorderMap.put(inorder[i],i);
        }
        return build(preorder,0,inorder.length-1);
    }
    public TreeNode build(int[] preorder, int inStart, int inEnd)
    {
        if(inStart>inEnd)
        {
            return null;
        }   
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inorderMap.get(rootVal);
        root.left = build(preorder,inStart,rootIndex-1);
        root.right = build(preorder,rootIndex+1,inEnd);
        return root;
    }
}
