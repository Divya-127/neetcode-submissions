/*
## Subtree of Another Tree — LeetCode 572

### Problem

Given the roots of two binary trees `root` and `subRoot`, determine whether `subRoot` is a subtree of `root`.

A subtree must match both:

* **Structure**
* **Node values**

---

### Key Idea

This problem is an extension of **Same Tree**.

We can break it into two separate tasks:

1. **Search** the main tree using DFS to find possible starting nodes.
2. When a node has the same value as `subRoot`, use `isSameTree()` to check whether the entire subtree matches.

So the solution combines:

```text
DFS Search + Same Tree Verification
```

---

### Step 1 — DFS Through the Main Tree

The `isSubtree()` method traverses the main tree recursively.

At every node:

```java id="3c7s1n"
if (root.val == subRoot.val)
```

we have found a **possible candidate** for the root of `subRoot`.

However, matching the value alone is not enough.

For example:

```text id="1q9s6z"
root:              subRoot:

    4                  4
   / \                / \
  2   5              2   6
```

Both roots have value `4`, but the trees are not the same.

Therefore we call:

```java id="1a6k2c"
isSameTree(root, subRoot)
```

---

### Step 2 — Reuse `Same Tree`

`isSameTree()` checks whether the two trees are completely identical.

It handles three cases:

#### One node is null

```java id="m5f8d3"
if ((p == null && q != null) || (p != null && q == null)) {
    return false;
}
```

The structures are different.

#### Both nodes are null

```java id="v7z2x4"
if (p == null && q == null) {
    return true;
}
```

This portion of both trees is identical.

#### Values are different

```java id="c1k8r5"
if (p.val != q.val) {
    return false;
}
```

The trees cannot be the same.

Otherwise, recursively compare both children.

---

### Overall Recursive Structure

The `isSubtree()` function essentially asks:

```text id="9q3m5t"
Can subRoot be rooted at this no
```
*/

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
