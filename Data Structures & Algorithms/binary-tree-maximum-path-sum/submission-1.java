/*
# 124. Binary Tree Maximum Path Sum

**LeetCode:** 124 — Binary Tree Maximum Path Sum  
**Difficulty:** Hard  
**Pattern:** Binary Tree DFS + Postorder Traversal + Global Maximum  
**Time Complexity:** `O(n)`  
**Auxiliary Space Complexity:** `O(h)` for the recursion stack, where `h` is the tree height. Worst case: `O(n)`; balanced tree: `O(log n)`.

## 1. Problem Understanding

Given the root of a binary tree, find the maximum possible sum of any **non-empty path**.

A path:
- Can start and end at any nodes.
- Must follow parent-child connections.
- Cannot visit the same node more than once.
- Can move through both the left and right child of a node.

**Important:** The path does not have to start at the root or end at a leaf. Node values may also be negative.

## 2. Core Intuition

The key is to distinguish between two different values:

1. **Global maximum path sum (`ans`):** The best complete path found anywhere in the tree. It may use both the left and right subtrees of a node.
2. **Maximum downward path sum (`calculateSum` return value):** The best path starting at the current node and extending towards at most one child. This value is returned to the parent.

Why can the global answer use both children, but the returned value cannot?

A valid path can turn at the current node, connecting the left subtree to the right subtree. However, when returning a path to the parent, we cannot include both children because that would create a branching path rather than a single path.

## 3. Java Solution

```java
*/
class Solution {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateSum(root);
        return ans;
    }

    public int calculateSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = Math.max(0, calculateSum(root.left));
        int rightSum = Math.max(0, calculateSum(root.right));

        // Best complete path passing through the current node
        ans = Math.max(ans, root.val + leftSum + rightSum);

        // Return only one branch to the parent
        return root.val + Math.max(leftSum, rightSum);
    }
}
/*
```

## 4. Step-by-Step Walkthrough

Consider this tree:

```text
        10
       /  \
      6    8
     / \
    4   5
```

The maximum path is `4 → 6 → 10 → 8`, whose sum is **28**.

### Step 1: Process leaf node 4

Both children are null, so their contributions are zero.

- `leftSum = 0`
- `rightSum = 0`
- Candidate path sum = `4 + 0 + 0 = 4`
- `ans = max(Integer.MIN_VALUE, 4) = 4`
- Return `4 + max(0, 0) = 4`

Node `4` returns `4` to node `6`.

### Step 2: Process leaf node 5

Similarly:

- Candidate path sum = `5`
- `ans = max(4, 5) = 5`
- Return `5`

Node `5` returns `5` to node `6`.

### Step 3: Process node 6

The left and right contributions are `4` and `5`.

- Candidate path through node `6` = `4 + 6 + 5 = 15`
- `ans = max(5, 15) = 15`
- Return `6 + max(4, 5) = 11`

The global answer remembers the complete path `4 → 6 → 5`, but node `6` returns only `6 → 5` (sum `11`) to node `10`.

Why? Because node `10` cannot extend a path that already includes both children of node `6`.

### Step 4: Process node 8

Node `8` is a leaf.

- Candidate path sum = `8`
- `ans = max(15, 8) = 15`
- Return `8`

### Step 5: Process root node 10

The left contribution is `11`, and the right contribution is `8`.

The best complete path through node `10` is:

`4 → 6 → 10 → 8`

Its sum is:

\[
4+6+10+8=28
\]

The code updates:

`ans = max(15, 28) = 28`

The helper then returns:

`10 + max(11, 8) = 21`

There is no parent above the root, so this returned value is unused. The final answer comes from `ans`, which is **28**.

## 5. Why `Math.max(0, childSum)` Matters

```java
int leftSum = Math.max(0, calculateSum(root.left));
int rightSum = Math.max(0, calculateSum(root.right));
```

If a child contributes a negative sum, including it would reduce the total. We therefore treat that contribution as zero, effectively excluding that child from the current path.

For example:

```text
       -10
       /  \
     -2   -3
```

At node `-10`, both child contributions become zero. Its candidate is `-10`.

However, the global answer already considers the individual child nodes. The maximum path sum is **`-2`**, not `0` or `-10`.

The null helper returns `0` because an absent child contributes nothing. This does not mean an empty path is a valid final answer.

## 6. Why Initialize `ans` to `Integer.MIN_VALUE`?

```java
int ans = Integer.MIN_VALUE;
```

If `ans` started at `0`, an all-negative tree could incorrectly return zero, even though a valid path must contain at least one node.

Starting at the smallest possible integer ensures that the first real node's candidate can become the answer.

## 7. Why Postorder DFS?

The current node needs its left and right contributions before it can calculate its own path sum.

Therefore, the traversal is:

**Left subtree → Right subtree → Current node**

This is postorder DFS. Each node is processed after its children, so its downward path values are already available.

## 8. Complexity Analysis

- **Time: `O(n)`** — Each node is visited once, with constant work performed per node.
- **Auxiliary space: `O(h)`** — Recursive calls use space proportional to the tree height. This is `O(log n)` for a balanced tree and `O(n)` in the worst case for a skewed tree.

## 9. Final Mental Model

At every node, perform two operations:

1. **Update the global answer:** `root.val + leftSum + rightSum` — the best complete path that turns at this node.
2. **Return to the parent:** `root.val + max(leftSum, rightSum)` — the best single branch the parent can extend.

Ignore negative child contributions, but never ignore the current node itself.

**The central insight:** A path may use both children when calculating the global answer, but it can use at most one child when being returned to the parent.
*/