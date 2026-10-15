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

public class Codec {
    StringBuilder sb;
    TreeNode ans;
    int i = 0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        createString(root);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        i = 0;
        String[] nodes = data.split(",");
        return createTree(nodes);
    }

    public TreeNode createTree(String[] nodes)
    {
        if(nodes[i].equals("N"))
        {
            i++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[i++]));
        root.left = createTree(nodes);
        root.right = createTree(nodes);
        return root;
    }

    public void createString(TreeNode root)
    {
        if(root==null)
        {
            sb.append("N");
            sb.append(",");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        createString(root.left);
        createString(root.right);
    }
}
