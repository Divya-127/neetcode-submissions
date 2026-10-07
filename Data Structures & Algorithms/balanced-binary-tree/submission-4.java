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
## Balanced Binary Tree — Optimized Early-Termination Approach

### Problem

Determine whether a binary tree is height-balanced.

For every node:

```text
|leftHeight - rightHeight| <= 1
```

---

### Key Idea

Instead of maintaining a separate global boolean, make the recursive function communicate **two possible types of information**:

```text
valid height → subtree is balanced
-2            → subtree is unbalanced
```

So the helper effectively returns:

```text
height OR "unbalanced"
```

This allows the recursion to **stop immediately** once an unbalanced subtree is found.

---

### Why use `-2`?

With edge-based height:

```text
null → -1
leaf → 0
```

Therefore `-2` is not a valid height.

We can safely use it as a special sentinel value meaning:

```text
"This subtree is already known to be unbalanced."
```

---

### Step-by-Step

First process the left subtree:

```java
int leftHeight = calculateHeight(root.left);
```

If it is already unbalanced:

```java
if (leftHeight == -2) {
    return -2;
}
```

There is no reason to process the current node or its right subtree.

Then do the same for the right subtree:

```java
int rightHeight = calculateHeight(root.right);

if (rightHeight == -2) {
    return -2;
}
```

Now both subtrees are balanced, so we can check the current node:

```java
if (Math.abs(leftHeight - rightHeight) > 1) {
    return -2;
}
```

Otherwise, return the normal height:

```java
return Math.max(leftHeight, rightHeight) + 1;
```

---

### Important Difference From the First Solution

#### Global boolean approach

```text
Find imbalance
      ↓
ans = false
      ↓
recursion can still continue
```

#### Sentinel approach

```text
Find imbalance
      ↓
return -2
      ↓
parent immediately returns -2
      ↓
entire unnecessary subtree is skipped
```

This is the main optimization.

---

### Complexity

* **Worst-case time:** `O(n)`
* **Best/early-termination case:** can visit significantly fewer than `n` nodes when an imbalance is found early.
* **Space:** `O(h)` for the recursion stack.

The asymptotic worst-case complexity does **not** improve from `O(n)` to something smaller. The optimization is avoiding unnecessary traversal.

---

### Mental Model

Think of the helper as returning a message to its parent:

```text
"Here is my height."
```

or:

```text
"My subtree is broken — stop."
```

So:

```text
             Node
            /    \
        height   height
           ↓       ↓
        compare heights
             ↓
       balanced?
       /        \
     yes         no
      ↓           ↓
 return height   return -2
```

---

### Code

```java
class Solution {

    public boolean isBalanced(TreeNode root) {
        return calculateHeight(root) != -2;
    }

    private int calculateHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = calculateHeight(root.left);

        if (leftHeight == -2) {
            return -2;
        }

        int rightHeight = calculateHeight(root.right);

        if (rightHeight == -2) {
            return -2;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -2;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

### Takeaway

The important recursion pattern is:

> **Return useful information upward, but use a special value to propagate failure immediately.**

This removes the need for a global variable and allows **early termination**.

The first solution is arguably easier to understand when learning trees. The sentinel solution is the cleaner production-style implementation once the recursive pattern is understood.

*/


class Solution {
    public boolean isBalanced(TreeNode root) {
        return calculateHeight(root)!= -2;
    }
    private int calculateHeight(TreeNode root) {
    if (root == null) {
        return -1;
    }

    int leftHeight = calculateHeight(root.left);
    if (leftHeight == -2) {
        return -2;
    }

    int rightHeight = calculateHeight(root.right);
    if (rightHeight == -2) {
        return -2;
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -2;
    }

    return Math.max(leftHeight, rightHeight) + 1;
}
}
