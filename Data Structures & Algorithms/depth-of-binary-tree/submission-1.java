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
 # Maximum Depth of Binary Tree — LeetCode 104

## Problem

Given the root of a binary tree, return the **maximum depth** of the tree.

The depth of a tree is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example:

```text
        1
       / \
      2   3
     /
    4
```

The maximum depth is `3`:

```text
1 → 2 → 4
```

---

## Step 1: Think About What the Answer Depends On

For any given node, its maximum depth depends on the depths of its two subtrees.

For:

```text
        1
       / \
      2   3
```

We can ask:

> What is the maximum depth of the left subtree?

and

> What is the maximum depth of the right subtree?

The deeper one determines the answer for the current node.

Therefore:

```text
depth(current)
    = max(depth(left), depth(right)) + 1
```

The `+1` represents the **current node itself**.

---

## Step 2: Identify the Base Case

What should happen when there is no node?

```java
root == null
```

There is no node, so its depth is:

```text
0
```

Therefore:

```java
if (root == null) {
    return 0;
}
```

This is also what eventually stops the recursion when we reach beyond a leaf node.

For example:

```text
    4
   / \
null null
```

When recursion reaches either `null`, it returns `0`.

---

## Step 3: Recursively Find the Left Subtree Depth

```java
int leftHeight = maxDepth(root.left);
```

This asks the same function:

> "Find the maximum depth of the tree rooted at my left child."

We don't need to manually traverse the left subtree.

The recursive function handles it.

---

## Step 4: Recursively Find the Right Subtree Depth

```java
int rightHeight = maxDepth(root.right);
```

Similarly, this asks:

> "Find the maximum depth of the tree rooted at my right child."

At this point we have:

```text
leftHeight
rightHeight
```

---

## Step 5: Choose the Deeper Subtree

The maximum depth must come from whichever subtree is deeper.

Therefore:

```java
Math.max(leftHeight, rightHeight)
```

Then we add `1` for the current node:

```java
return Math.max(leftHeight, rightHeight) + 1;
```

---

# Step-by-Step Recursion Example

Consider:

```text
        1
       / \
      2   3
     /
    4
```

We start with:

```java
maxDepth(1)
```

### At Node 1

We don't know the answer yet, so we ask both children:

```java
maxDepth(2)
maxDepth(3)
```

---

### At Node 2

Again, ask both children:

```java
maxDepth(4)
maxDepth(null)
```

---

### At Node 4

Node `4` has no children.

So we call:

```java
maxDepth(null)
maxDepth(null)
```

Both return:

```text
0
```

Therefore:

```text
depth(4) = max(0, 0) + 1
         = 1
```

So node `4` returns `1` to node `2`.

---

### Back at Node 2

We now know:

```text
leftHeight  = 1
rightHeight = 0
```

Therefore:

```text
depth(2) = max(1, 0) + 1
         = 2
```

Node `2` returns `2` to node `1`.

---

### At Node 3

Node `3` is a leaf.

Therefore:

```text
depth(3) = max(0, 0) + 1
         = 1
```

Node `3` returns `1` to node `1`.

---

### Back at Node 1

Now we have:

```text
leftHeight  = 2
rightHeight = 1
```

Therefore:

```text
depth(1) = max(2, 1) + 1
         = 3
```

Final answer:

```text
3
```

---

# The Important Recursion Pattern

This problem demonstrates a very common **bottom-up tree recursion** pattern.

Instead of modifying the tree like in Invert Binary Tree, we are asking each subtree to **return information to its parent**.

The pattern is:

```java
if (root == null) {
    return baseValue;
}

int leftAnswer = recursiveFunction(root.left);
int rightAnswer = recursiveFunction(root.right);

return combine(leftAnswer, rightAnswer, root);
```

For this problem:

```java
if (root == null) {
    return 0;
}

int leftHeight = maxDepth(root.left);
int rightHeight = maxDepth(root.right);

return Math.max(leftHeight, rightHeight) + 1;
```

---

# Why `+1`?

This is an important detail.

Suppose both children return `0`:

```text
        4
       / \
      null null
```

The subtree rooted at `4` has depth `1`, not `0`.

So:

```text
max(0, 0) + 1 = 1
```

The `+1` accounts for the current node.

---

# Mental Model

Think of every node asking its children:

> **"How tall are you?"**

The children return their heights.

The current node then says:

> **"I'll take the taller one and add myself."**

So:

```text
             Node
            /    \
       ask left  ask right
           ↓        ↓
        height    height
            \      /
             max
              +
            current
             node
```

---

# Top-Down vs Bottom-Up Thinking

This problem is a good introduction to **bottom-up recursion**.

### Top-down

You pass information from the parent toward the children.

Example:

```text
current depth → child
```

### Bottom-up

You ask the children for information and use their answers to calculate the parent's answer.

This problem is:

```text
leaf
 ↓
parent
 ↓
grandparent
 ↓
root
```

The final answer is therefore calculated while the recursion **unwinds**.

---

# Complexity

Let `n` be the number of nodes.

### Time: `O(n)`

Every node is visited exactly once.

```text
Node 1 → visited once
Node 2 → visited once
Node 3 → visited once
...
```

Therefore:

```text
O(n)
```

### Space: `O(h)`

The recursive calls use the call stack.

`h` is the height of the tree.

For a balanced tree:

```text
h = O(log n)
```

For a completely skewed tree:

```text
h = O(n)
```

Therefore the auxiliary space is:

```text
O(h)
```

---

# Final Code

```java
class Solution {
    public int maxDepth(TreeNode root) {

        // Base case:
        // An empty tree has depth 0.
        if (root == null) {
            return 0;
        }

        // Recursively find the depth of both subtrees.
        int rightHeight = maxDepth(root.right);
        int leftHeight = maxDepth(root.left);

        // The current node contributes 1 to the depth.
        // Choose the deeper of the two subtrees.
        return Math.max(rightHeight, leftHeight) + 1;
    }
}
```

# Key Takeaway

**Maximum Depth of Binary Tree = ask both children for their height, take the larger height, and add 1 for the current node.**

The bigger lesson is the recursion pattern:

```text
Base case
   ↓
Solve left subtree
   ↓
Solve right subtree
   ↓
Combine their answers
   ↓
Return result to parent
```

This **"get answers from children → combine → return"** pattern is one of the most important patterns to recognize in Tree problems.
*/

class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null)
        {
            return 0;
        }
        int rightHeight = maxDepth(root.right);
        int leftHeight = maxDepth(root.left);
        return Math.max(rightHeight,leftHeight) + 1;
    }
}
