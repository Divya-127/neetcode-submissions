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
## Same Binary Tree — LeetCode 100

### Problem

Given the roots of two binary trees `p` and `q`, determine whether the two trees are **the same**.

Two binary trees are considered the same if:

1. They have the same structure.
2. Corresponding nodes have the same values.

---

### Key Idea

Compare the two trees **node by node** using recursive DFS.

At every pair of corresponding nodes, there are three important cases:

1. One node is `null` and the other is not → trees are different.
2. Both nodes are `null` → this part of the trees is identical.
3. Both nodes exist but have different values → trees are different.

If the current nodes match, recursively compare:

* their left subtrees
* their right subtrees

Both subtrees must be identical for the current trees to be identical.

---

### DFS Pattern

The recursion performs **Depth-First Search (DFS)** on both trees simultaneously.

Conceptually:

```text
Compare current nodes
        ↓
   ┌────┴────┐
Compare     Compare
 left        right
 subtree     subtree
   ↓           ↓
   └─────┬─────┘
         ↓
     BOTH same?
```

The traversal goes as deep as possible into one subtree before moving to the other, which is why this is DFS.

---

### Base Cases

#### Case 1: One node is null

```java
if ((p == null && q != null) || (p != null && q == null)) {
    return false;
}
```

The structures are different.

For example:

```text
p:          q:

  1           1
 /             \
2               2
```

The trees cannot be the same because their structures differ.

---

#### Case 2: Both nodes are null

```java
if (p == null && q == null) {
    return true;
}
```

Both subtrees have ended at the same point, so this part of the trees is identical.

This base case is also important because it prevents trying to access:

```java
p.val
q.val
```

when both nodes are `null`.

---

#### Case 3: Values are different

```java
if (p.val != q.val) {
    return false;
}
```

If corresponding nodes contain different values, the trees cannot be the same.

---

### Recursive Step

Once the current nodes are confirmed to exist and have the same value:

```java
boolean leftTree = isSameTree(p.left, q.left);
boolean rightTree = isSameTree(p.right, q.right);
```

We recursively compare the corresponding left and right subtrees.

Finally:

```java
return leftTree && rightTree;
```

Both must be `true`.

If even one subtree differs, the entire tree is different.

---

### Mental Model

Think of the function as asking:

> **"Are these two subtrees the same?"**

For every pair of nodes:

```text
Are both null?
    → Yes → same

Is exactly one null?
    → Yes → different

Do values differ?
    → Yes → different

Otherwise:
    → Are left subtrees the same?
    → Are right subtrees the same?
    → Both must be true
```

This is a very useful general recursive-tree pattern:

> **Solve the same problem on the children, then combine their answers.**

---

### Complexity

Let `n` be the number of nodes examined.

* **Time:** `O(n)` in the worst case — each corresponding node is visited at most once.
* **Space:** `O(h)` — recursion stack, where `h` is the height of the tree.

  * Balanced tree: `O(log n)`
  * Skewed tree: `O(n)`

---

### Code

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // One is null and the other isn't
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        // Both are null
        if (p == null && q == null) {
            return true;
        }

        // Both exist but values differ
        if (p.val != q.val) {
            return false;
        }

        // Compare corresponding subtrees
        boolean leftTree = isSameTree(p.left, q.left);
        boolean rightTree = isSameTree(p.right, q.right);

        // Both subtrees must be identical
        return leftTree && rightTree;
    }
}
```

### Key Takeaway

This problem reinforces the fundamental **recursive DFS pattern for trees**:

```text
Base case
    ↓
Check current node
    ↓
Recursively solve left subtree
    ↓
Recursively solve right subtree
    ↓
Combine the results
```

The important realization is that DFS doesn't always mean explicitly writing a `dfs()` method. **The recursive calls themselves are performing the depth-first traversal.**
*/

class Solution {
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
