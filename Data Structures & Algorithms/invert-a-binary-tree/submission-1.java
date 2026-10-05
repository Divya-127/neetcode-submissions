/*
# Invert Binary Tree — LeetCode 226

## Step-by-Step Approach

### 1. Understand what inversion means

For every node, its **left subtree and right subtree need to be exchanged**.

Example:

```text
Before:             After:

    4                   4
   / \                 / \
  2   7               7   2
```

We are swapping the **child references**, not the values.

---

### 2. Handle the base case

Ask: **What happens if there is no node?**

If:

```java
root == null
```

there is nothing to invert.

So:

```java
if (root == null) {
    return null;
}
```

This also tells the recursion when to stop.

---

### 3. Swap the left and right children

For the current node:

```java
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;
```

For example:

```text
        4
       / \
      2   7
```

becomes:

```text
        4
       / \
      7   2
```

---

### 4. Invert the left subtree

After swapping, the node currently on the left needs to be inverted as well.

```java
root.left = invertTree(root.left);
```

We don't manually process every node below it.

Instead, we trust `invertTree()` to solve the entire subtree.

---

### 5. Invert the right subtree

Same thing for the right side:

```java
root.right = invertTree(root.right);
```

So the complete recursive structure becomes:

```java
swap(root);

root.left = invertTree(root.left);
root.right = invertTree(root.right);
```

---

### 6. Return the current root

Once the current node and both of its subtrees are inverted, return it:

```java
return root;
```

This allows the parent call to receive the root of the completed inverted subtree.

---

## Recursion Example

Consider:

```text
        4
       / \
      2   7
     / \
    1   3
```

### Call 1

```text
invertTree(4)
```

Swap children:

```text
        4
       / \
      7   2
     / \
    1   3
```

Now recursively process `7` and `2`.

### Call 2

```text
invertTree(7)
```

7 has no children.

It eventually calls:

```text
invertTree(null)
```

Base case:

```java
return null;
```

So the recursion stops there.

### Call 3

```text
invertTree(2)
```

Swap its children:

```text
      2
     / \
    3   1
```

Then recursively process `3` and `1`.

Both are leaf nodes, so their recursive calls eventually reach:

```java
invertTree(null)
```

and return `null`.

---

## Final Tree

```text
        4
       / \
      7   2
         / \
        3   1
```

## Final Code

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {

        // Base case
        if (root == null) {
            return null;
        }

        // Swap left and right subtrees
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert both subtrees
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        // Return the root of the inverted subtree
        return root;
    }
}
```

## Complexity

* **Time:** `O(n)` — every node is visited once.
* **Space:** `O(h)` — recursion stack, where `h` is the height of the tree.

## Mental Model

> **At every node: swap → solve left subtree → solve right subtree → return node.**

The biggest lesson from this problem is understanding **recursion + base case + returning the processed subtree**. This pattern will appear repeatedly in tree problems.
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
        {
            return null;
        }

        swap(root);

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        return root;
    }
    public void swap(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
