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
/*
# Lowest Common Ancestor of a Binary Search Tree — LeetCode 235

## Problem

Given the root of a Binary Search Tree (BST) and two nodes `p` and `q`, find their **Lowest Common Ancestor (LCA)**.

The LCA is the lowest node in the tree that has both `p` and `q` as descendants. A node can be a descendant of itself.

## Key Idea

A Binary Search Tree has a specific ordering property:

* Values in the left subtree are smaller than the current node.
* Values in the right subtree are greater than the current node.

We can use this property to determine where the LCA must be, without searching both subtrees.

At each node, compare `root.val` with `p.val` and `q.val`.

### Case 1: Both target nodes are greater than the current node

```java
if (root.val < p.val && root.val < q.val) {
    return lowestCommonAncestor(root.right, p, q);
}
```

Both targets belong in the right subtree, so move right.

### Case 2: Both target nodes are smaller than the current node

```java
else if (root.val > p.val && root.val > q.val) {
    return lowestCommonAncestor(root.left, p, q);
}
```

Both targets belong in the left subtree, so move left.

### Case 3: The targets are on opposite sides, or one target is the current node

```java
return root;
```

The current node is the LCA.

This happens when:

* `p` is in the left subtree and `q` is in the right subtree, or vice versa.
* The current node is itself `p` or `q`.

## Example

Consider this BST:

```text
        6
       / \
      2   8
     / \ / \
    0  4 7  9
```

If `p = 2` and `q = 4`:

1. Start at node `6`.
2. Both target values are smaller than `6`, so move left.
3. Reach node `2`.
4. Since `2` is one of the targets, return node `2`.

Therefore, the LCA is `2`.

## Algorithm

1. If `root` is `null`, return `null`.
2. If both targets are greater than `root`, recursively search the right subtree.
3. If both targets are smaller than `root`, recursively search the left subtree.
4. Otherwise, return `root`.

## Code

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        return root;
    }
}
```

## Complexity

Let `n` be the number of nodes and `h` be the height of the BST.

* **Time:** `O(h)` — each recursive call moves down by one level.
* **Auxiliary space:** `O(h)` — recursion stack.
* Balanced BST: `O(log n)` time and space.
* Worst-case skewed BST: `O(n)` time and space.

## Mental Model

At every node, ask:

> Are both targets on the same side of me, or have I reached the point where their paths meet?

```text
Both targets smaller → Go left
Both targets greater → Go right
Otherwise            → Return current node
```

## Key Takeaway

This problem demonstrates how choosing the right property can simplify a tree problem.

Unlike general binary-tree problems that may require DFS through multiple branches, a BST lets us **eliminate an entire subtree at every step**.

Recognize the structure first, then use its properties to guide the search.

*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
        {
            return null;
        }
        if(root.val<p.val && root.val<q.val)
        {
            return lowestCommonAncestor(root.right,p,q);
        }else if(root.val>p.val && root.val>q.val)
        {
            return lowestCommonAncestor(root.left,p,q);
        }
        return root;
    }
}
