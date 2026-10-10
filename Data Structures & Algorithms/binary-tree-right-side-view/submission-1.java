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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root!=null)
        queue.offer(root);
        while(!(queue.isEmpty()))
        {
            int size = queue.size();
            while(size>0)
            {
                TreeNode node = queue.poll();
                if(node.left!=null)
                {
                    queue.offer(node.left);
                }
                    if(node.right!=null)
                    {
                        queue.offer(node.right);
                    }
                if(size==1)
                {
                    ans.add(node.val);
                }
                size--;
            }
        }
        return ans;       
    }
}
