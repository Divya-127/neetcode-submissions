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
 # Validate Binary Search Tree (LeetCode 98)

## Problem
Determine whether a binary tree is a valid Binary Search Tree (BST).

A valid BST requires every node to satisfy:
- Every value in its left subtree is strictly smaller than the node's value.
- Every value in its right subtree is strictly greater than the node's value.

**Important:** Checking only the immediate children is insufficient. Every descendant must satisfy the constraints imposed by all its ancestors.

## Approach: DFS with Lower and Upper Bounds

Use Depth-First Search (DFS) and pass a valid range, `lowerBound` and `upperBound`, to each recursive call.

Initially, the root has no ancestor-imposed constraints, so start with `Integer.MIN_VALUE` and `Integer.MAX_VALUE`. These are safe sentinels for the problem's constraint `-1,000,000,000 <= Node.val <= 1,000,000,000`.

### Algorithm
1. **Base case:** If `node == null`, return `true`. An empty subtree is valid.
2. **Validate the current node:** If `node.val <= lowerBound` or `node.val >= upperBound`, return `false`. Strict comparisons ensure duplicate values are rejected.
3. **Validate the left subtree:** Keep the same lower bound and set the upper bound to `node.val`.
4. **Validate the right subtree:** Set the lower bound to `node.val` and keep the same upper bound.
5. Return `true` only if both subtrees are valid.

## Key Insight: Bounds Carry Ancestor Constraints

Consider this tree:

```text
    10
      \
       15
      /
     6
```

Node `6` is smaller than its parent `15`, but it violates the constraint imposed by ancestor `10`: every node in the right subtree of `10` must be greater than `10`.

The valid range tightens as we move down the tree:
- Moving left: `upperBound = node.val`
- Moving right: `lowerBound = node.val`

Every node must satisfy `lowerBound < node.val < upperBound`.

This differs from **Count Good Nodes (LeetCode 1448)**: that problem tracks the maximum value along a path, whereas Validate BST tracks the entire valid interval imposed by ancestors.

## Complexity
- **Time:** `O(n)` — every node is visited at most once.
- **Auxiliary space:** `O(h)` — recursion stack, where `h` is the tree height. Worst case `O(n)` for a skewed tree and `O(log n)` for a balanced tree.

## Pattern to Remember
**Tree DFS + inherited constraints:** When a node must satisfy rules imposed by multiple ancestors, carry those constraints through recursive arguments rather than checking only the parent.

For a general implementation supporting the entire Java `int` range, use `long` bounds initialized to `Long.MIN_VALUE` and `Long.MAX_VALUE` to avoid sentinel-value edge cases.
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
