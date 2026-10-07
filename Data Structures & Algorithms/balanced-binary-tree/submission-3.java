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
 ## Balanced Binary Tree — LeetCode 110

### Problem

Given the root of a binary tree, determine whether the tree is **height-balanced**.

A binary tree is balanced if, for **every node**, the difference between the heights of its left and right subtrees is at most `1`.

---

### Key Idea

At every node, we need the heights of its left and right subtrees:

```text
|leftHeight - rightHeight| <= 1
```

So the recursive function has an important responsibility:

> **Return the height of the current subtree to its parent.**

At the same time, we maintain a boolean `ans` that records whether the entire tree is still balanced.

This is similar to the pattern used in **Diameter of Binary Tree**:

* Recursion returns **height**
* Current node uses that height to calculate/check something
* Result is passed upward

---

### Why do we return height?

Even though the final answer is only `true` or `false`, the parent needs to know the height of each child subtree.

For example:

```text
        1
       / \
      2   3
     /
    4
```

At node `2`:

```text
leftHeight  = 0   // node 4
rightHeight = -1  // null
```

Difference:

```text
|0 - (-1)| = 1
```

So node `2` is balanced.

Its height is:

```text
max(0, -1) + 1 = 1
```

That `1` is then returned to node `1`.

---

### Why use `-1` for null?

We are measuring height in terms of **edges**.

Therefore:

```text
null → -1
leaf → 0
```

For a leaf:

```text
max(-1, -1) + 1 = 0
```

This makes the height calculation consistent.

---

### Step-by-Step

1. If the node is `null`, return `-1`.
2. Recursively calculate the left subtree height.
3. Recursively calculate the right subtree height.
4. Compare the two heights.
5. If their difference is greater than `1`, mark the tree as unbalanced.
6. Return the current subtree's height to the parent.

```java
int leftHeight = calculateHeight(root.left);
int rightHeight = calculateHeight(root.right);

if (Math.abs(leftHeight - rightHeight) > 1) {
    ans = false;
}

return Math.max(leftHeight, rightHeight) + 1;
```

---

### Why the `ans` variable?

The recursive function needs to return the **height**, so it cannot simultaneously return the overall boolean answer.

Therefore:

```text
calculateHeight() → returns height
ans              → stores whether the tree is balanced
```

The helper communicates height upward, while `ans` remembers the result discovered anywhere in the tree.

---

### Complexity

* **Time:** `O(n)` — every node is visited.
* **Space:** `O(h)` — recursion stack, where `h` is the tree height.

  * Balanced tree: `O(log n)`
  * Completely skewed tree: `O(n)`

---

### Mental Model

Think:

```text
             Current Node
            /            \
      get left height   get right height
            ↓                ↓
          compare the two heights
                    ↓
              check balance
                    ↓
            return height upward
```

The important pattern is:

> **"Return height to the parent, but check balance at the current node."**

---

### Code

```java
class Solution {
    boolean ans = true;

    public boolean isBalanced(TreeNode root) {
        calculateHeight(root);
        return ans;
    }

    private int calculateHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        if (ans) {
            ans = Math.abs(leftHeight - rightHeight) > 1 ? false : true;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

### Takeaway

This is a clean and easy-to-understand solution.

The only inefficiency is that once an imbalance is discovered, recursion can still continue through other parts of the tree because the height function must continue returning heights.

 */

class Solution {
    boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        calculateHeight(root);
        return ans;
    }
    private int calculateHeight(TreeNode root) {
    if (root == null) {
        return -1;
    }
    int leftHeight = calculateHeight(root.left);
    int rightHeight = calculateHeight(root.right);
    if(ans){
        ans = Math.abs(leftHeight-rightHeight)>1?false:true;
    }
    return Math.max(leftHeight, rightHeight) + 1;
    }
}
