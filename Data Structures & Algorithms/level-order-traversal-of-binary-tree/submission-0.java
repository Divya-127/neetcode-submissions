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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root!=null)
        queue.offer(root);
        while(!(queue.isEmpty()))
        {
            int size = queue.size();
            List<Integer> arr = new ArrayList<Integer>();
            while(size>0){
                TreeNode node = queue.poll();
                arr.add(node.val);
                if(node.left!=null)
                queue.offer(node.left);
                if(node.right!=null)
                queue.offer(node.right);
                size--;
            }
            ans.add(arr);
        }
        return ans;
    }
}
