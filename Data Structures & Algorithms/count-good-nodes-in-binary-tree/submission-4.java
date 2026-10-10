/*
The key mental model
- Left and right don't need separate logic for deciding whether a node is good.
- Both children inherit the maximum from their parent’s path.
- Each child gets its own queue entry, so its path maximum travels with it.
- >= counts nodes equal to their ancestors, and starting at Integer.MIN_VALUE ensures the root is counted exactly once.
Complexity: \(O(n)\) time and \(O(w)\) auxiliary space, where \(w\) is the maximum width of the tree.
*/

class Solution {
    static class NodeInfo {
        TreeNode node;
        int maxSoFar;

        NodeInfo(TreeNode node, int maxSoFar) {
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }

    public int goodNodes(TreeNode root) {
        if (root == null) return 0;

        int goodNodes = 0;
        Queue<NodeInfo> queue = new LinkedList<>();

        queue.offer(new NodeInfo(root, Integer.MIN_VALUE));

        while (!queue.isEmpty()) {
            NodeInfo current = queue.poll();

            TreeNode node = current.node;
            int maxSoFar = current.maxSoFar;

            if (node.val >= maxSoFar) {
                goodNodes++;
            }

            int newMax = Math.max(maxSoFar, node.val);

            if (node.left != null) {
                queue.offer(new NodeInfo(node.left, newMax));
            }

            if (node.right != null) {
                queue.offer(new NodeInfo(node.right, newMax));
            }
        }

        return goodNodes;
    }
}