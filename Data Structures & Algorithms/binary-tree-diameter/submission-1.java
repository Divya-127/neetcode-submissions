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
# Diameter of Binary Tree — LeetCode 543

## Problem

Given the root of a binary tree, return the **diameter of the tree**.

The diameter is the length of the **longest path between any two nodes**, measured in **number of edges**.

Example:

```text
        1
       / \
      2   3
     / \
    4   5
```

The longest path is:

```text
4 → 2 → 1 → 3
```

Therefore:

```text
Diameter = 3 edges
```

---

# Key Observation

For any node, the longest path that **passes through that node** consists of:

```text
longest path from left subtree
        +
edge to current node
        +
edge to right subtree
```

If `leftHeight` and `rightHeight` represent the height of the left and right subtrees in terms of **edges**, then:

```text
diameter through current node
    = leftHeight + rightHeight + 2
```

The `+2` represents:

```text
left child ← current node → right child
```

However, the overall diameter does **not necessarily pass through the root**.

It could exist entirely inside the left or right subtree.

Therefore, while traversing the tree, we need to keep track of the **maximum diameter encountered anywhere**.

---

# Step 1: Use DFS

This problem can be solved using **Depth-First Search (DFS)**.

The recursive function goes as deep as possible into the left subtree, then the right subtree, before calculating the result for the current node.

The traversal is therefore:

```text
Left subtree
     ↓
Right subtree
     ↓
Current node
```

This is specifically **postorder DFS**.

Why postorder?

Because we need the heights of both children **before** we can calculate the diameter passing through the current node.

---

# Step 2: Decide What the Recursive Function Should Return

This is the important part.

The parent node needs to know:

> "How tall is your subtree?"

So the recursive helper should return the **height of the current subtree**.

It does NOT return the diameter.

Instead:

```text
Helper function → returns height
Global variable → stores maximum diameter
```

This gives the recursion two separate responsibilities:

```text
             Node
              |
       ┌──────┴──────┐
       ↓             ↓
   return height   update ans
   to parent       with diameter
```

---

# Step 3: Define Height in Terms of Edges

Normally, Maximum Depth is often calculated in terms of nodes.

For Diameter, however, the answer is required in **edges**.

So we define:

```text
null node → height = -1
leaf node → height = 0
```

This makes the calculation very clean.

### Base Case

```java
if (root == null) {
    return -1;
}
```

Why `-1`?

Consider a leaf node:

```text
        4
       / \
    null null
```

Both children return:

```text
-1
```

Therefore:

```text
height(4)
    = max(-1, -1) + 1
    = 0
```

So a leaf has height `0` edges, which is exactly correct.

---

# Step 4: Recursively Find Left and Right Heights

```java
int leftHeight = calculateHeight(root.left);
int rightHeight = calculateHeight(root.right);
```

These two recursive calls perform the DFS.

We first go deep into the left subtree and obtain its height.

Then we go deep into the right subtree and obtain its height.

Only after both values are available can we calculate the diameter passing through the current node.

---

# Step 5: Calculate Diameter Through the Current Node

```java
ans = Math.max(ans, leftHeight + rightHeight + 2);
```

Suppose:

```text
leftHeight = 2
rightHeight = 1
```

Then:

```text
diameter through current node
    = 2 + 1 + 2
    = 5
```

The `+2` accounts for the two edges connecting the current node to the two sides.

Visually:

```text
left subtree          right subtree
     ↓                     ↓
     A                     B
     |                     |
     L → ... → root → ... → R
```

There is one edge from the current node toward the left side and one toward the right side.

---

# Step 6: Why Use `Math.max`?

The diameter through the current node is only **one candidate**.

The actual maximum diameter may have already been found deeper in the tree.

For example:

```text
        1
       /
      2
     / \
    4   5
   /     \
  6       7
```

The longest path may be entirely inside the subtree rooted at `2`.

Therefore:

```java
ans = Math.max(ans, currentDiameter);
```

means:

> Keep whichever diameter is larger — the one we've already found or the one passing through this node.

---

# Step 7: Return Height to the Parent

After calculating the diameter, the current node still needs to tell its parent how tall its subtree is.

```java
return Math.max(leftHeight, rightHeight) + 1;
```

We choose the taller of the two subtrees and add one edge from the current node to that subtree.

This returned value is used by the parent as either its `leftHeight` or `rightHeight`.

---

# Complete Recursion Example

Consider:

```text
        1
       / \
      2   3
     / \
    4   5
```

Start:

```text
calculateHeight(1)
```

The recursion goes deep first:

```text
1
↓
2
↓
4
```

At node `4`:

```text
leftHeight  = -1
rightHeight = -1

diameter = -1 + -1 + 2
         = 0

height = max(-1, -1) + 1
       = 0
```

Node `4` returns:

```text
0
```

---

Now node `5` is processed similarly:

```text
height(5) = 0
diameter through 5 = 0
```

Back at node `2`:

```text
leftHeight  = 0
rightHeight = 0
```

Therefore:

```text
diameter through 2
    = 0 + 0 + 2
    = 2
```

And:

```text
height(2)
    = max(0, 0) + 1
    = 1
```

So `2` returns height `1` to node `1`.

---

Node `3` is a leaf:

```text
height(3) = 0
```

---

Finally, at node `1`:

```text
leftHeight  = 1
rightHeight = 0
```

Therefore:

```text
diameter through 1
    = 1 + 0 + 2
    = 3
```

So:

```text
ans = 3
```

The path is:

```text
4 → 2 → 1 → 3
```

---

# Why We Need a Helper Function

Initially, the natural implementation might try:

```java
public int diameterOfBinaryTree(TreeNode root)
```

and use the return value for both:

* subtree height
* final diameter

But that doesn't work because the parent needs the **height**, while the final answer needs the **diameter**.

Therefore we separate the responsibilities:

```java
public int diameterOfBinaryTree(TreeNode root) {
    calculateHeight(root);
    return ans;
}
```

The public method returns the final answer.

The helper performs the DFS and returns subtree height.

```java
private int calculateHeight(TreeNode root)
```

This is a very useful pattern when a recursive function needs to return one value upward while simultaneously maintaining another piece of information.

---

# Why `ans` Is Global

`ans` represents:

```text
maximum diameter found anywhere in the tree
```

It is updated at every node:

```java
ans = Math.max(ans, leftHeight + rightHeight + 2);
```

The helper continues returning heights normally.

After the entire DFS finishes:

```java
return ans;
```

gives the maximum diameter found during the traversal.

---

# Important Mental Model

This problem has **two different values** moving through the recursion.

### Height

Moves **upward** from child → parent:

```text
child
  ↓
height
  ↓
parent
```

### Diameter

Is maintained as the **best answer seen so far**:

```text
node
 ↓
calculate diameter
 ↓
compare with ans
 ↓
keep maximum
```

So:

```text
             Recursive DFS
                  |
       ┌──────────┴──────────┐
       ↓                     ↓
   Return height        Update global ans
   to parent            with best diameter
```

This distinction is the most important concept in this problem.

---

# General Tree Recursion Pattern Learned

This problem demonstrates a very useful pattern:

```java
if (root == null) {
    return baseValue;
}

int left = recursiveCall(root.left);
int right = recursiveCall(root.right);

// Calculate something using left and right

// Update global/best answer if required

// Return information needed by parent
```

For Diameter of Binary Tree:

```java
if (root == null) {
    return -1;
}

int leftHeight = calculateHeight(root.left);
int rightHeight = calculateHeight(root.right);

ans = Math.max(ans, leftHeight + rightHeight + 2);

return Math.max(leftHeight, rightHeight) + 1;
```

---

# Complexity

### Time: `O(n)`

Every node is visited exactly once.

At each node we perform constant-time work:

* compare two heights
* calculate diameter
* update `ans`

Therefore:

```text
O(n)
```

### Space: `O(h)`

The recursion stack can contain at most `h` nodes, where `h` is the height of the tree.

Balanced tree:

```text
O(log n)
```

Skewed tree:

```text
O(n)
```

Therefore:

```text
Space = O(h)
```

---

# Final Code

```java
class Solution {

    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return ans;
    }

    private int calculateHeight(TreeNode root) {

        // Null node has height -1 because
        // height is being measured in edges.
        if (root == null) {
            return -1;
        }

        // Postorder DFS:
        // first calculate heights of both subtrees.
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        // Longest path passing through the current node.
        ans = Math.max(ans, leftHeight + rightHeight + 2);

        // Return height of current subtree to its parent.
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

# Key Takeaways

1. **Diameter is measured in edges**, while Maximum Depth is often measured in nodes.
2. Using `null → -1` makes a leaf's height naturally become `0`.
3. **DFS** is used because we need child information before processing the parent.
4. This is specifically **postorder DFS**: left → right → root.
5. The recursive function returns **height** to the parent.
6. `ans` separately stores the **maximum diameter found anywhere**.
7. Diameter through a node is:

```text
leftHeight + rightHeight + 2
```

8. The overall diameter does **not have to pass through the root**.
9. The core pattern is:

```text
Get information from children
        ↓
Calculate current node's result
        ↓
Update global/best answer
        ↓
Return information needed by parent
```

This is an important step up from **Maximum Depth**: instead of recursion simply returning the final answer, recursion now **returns information to the parent while simultaneously contributing to a separate global answer**.
*/

class Solution {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return ans;
    }
    private int calculateHeight(TreeNode root) {
    if (root == null) {
        return -1;
    }
    int leftHeight = calculateHeight(root.left);
    int rightHeight = calculateHeight(root.right);
    ans = Math.max(ans, leftHeight + rightHeight + 2);
    return Math.max(leftHeight, rightHeight) + 1;
    }
}
