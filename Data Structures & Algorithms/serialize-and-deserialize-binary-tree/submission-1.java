/*
# 297. Serialize and Deserialize Binary Tree

**LeetCode:** 297 — Serialize and Deserialize Binary Tree  
**Difficulty:** Hard  
**Pattern:** Preorder DFS + Recursion + Shared Index  
**Time Complexity:** `O(n)` for serialization and deserialization, assuming average-case string splitting and token processing.  
**Auxiliary Space Complexity:** `O(n)` for the serialized string and tokens, plus `O(h)` recursion stack space, where `h` is the tree height.

## 1. Problem Understanding

Serialization converts a binary tree into a string so it can be stored or transmitted.

Deserialization reconstructs the original binary tree from that string.

The important requirement is that the serialized representation must preserve both:
- Each node's value.
- The exact structure of the tree, including missing left and right children.

Simply recording node values is insufficient because different trees can have the same traversal values.

## 2. Core Intuition

Use **preorder traversal: Root → Left → Right**.

For every node:
1. Record its value.
2. Serialize its left subtree.
3. Serialize its right subtree.

For every null child, record a special marker such as `"N"`.

Use a comma as the delimiter between tokens so that multi-digit and negative node values can be read unambiguously.

For example:

```text
        1
       / \
      2   3
```

Serializes to:

`1,2,N,N,3,N,N,`

The four `N` markers represent the four missing child links.

These null markers are essential because they preserve the original shape of the tree.

## 3. Java Solution

```java
*/
public class Codec {
    StringBuilder sb;
    int i = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        createString(root);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        i = 0;
        String[] nodes = data.split(",");
        return createTree(nodes);
    }

    public TreeNode createTree(String[] nodes) {
        if (nodes[i].equals("N")) {
            i++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(nodes[i++]));
        root.left = createTree(nodes);
        root.right = createTree(nodes);

        return root;
    }

    public void createString(TreeNode root) {
        if (root == null) {
            sb.append("N");
            sb.append(",");
            return;
        }

        sb.append(root.val);
        sb.append(",");
        createString(root.left);
        createString(root.right);
    }
}
/*
```

## 4. Serialization: Tree → String

The `serialize()` method initializes a fresh `StringBuilder` and calls `createString()`.

### Base case

```java
if (root == null) {
    sb.append("N");
    sb.append(",");
    return;
}
```

Whenever the current node is null, append the marker `"N"` and stop processing that branch.

### Recursive case

```java
sb.append(root.val);
sb.append(",");
createString(root.left);
createString(root.right);
```

Append the current node first, then process its left and right children. This is preorder traversal.

### Example

For the tree:

```text
       1
      / \
     2   3
```

The traversal proceeds as follows:

| Step | Token | Reason |
|---|---|---|
| 1 | `1` | Visit root |
| 2 | `2` | Visit left child |
| 3 | `N` | Node 2 has no left child |
| 4 | `N` | Node 2 has no right child |
| 5 | `3` | Visit right child |
| 6 | `N` | Node 3 has no left child |
| 7 | `N` | Node 3 has no right child |

Final serialized string:

`1,2,N,N,3,N,N,`

## 5. Deserialization: String → Tree

The `deserialize()` method splits the string into tokens and resets the shared index:

```java
i = 0;
String[] nodes = data.split(",");
return createTree(nodes);
```

The index `i` identifies the next token to consume. Every recursive call advances this same index, so tokens are processed in their original preorder sequence.

### Base case

```java
if (nodes[i].equals("N")) {
    i++;
    return null;
}
```

If the next token is `"N"`, the corresponding child is missing. Consume that token and return `null`.

### Recursive case

```java
TreeNode root = new TreeNode(Integer.valueOf(nodes[i++]));
root.left = createTree(nodes);
root.right = createTree(nodes);
return root;
```

If the token is a number:
1. Convert it into an integer.
2. Create the current node.
3. Recursively construct its left subtree.
4. Recursively construct its right subtree.
5. Return the completed node.

The recursive calls automatically resume at the correct parent after a subtree has been constructed.

## 6. How Deserialization Rebuilds the Example

Serialized input:

`1,2,N,N,3,N,N,`

The recursive calls consume tokens as follows:

1. Read `1` and create the root.
2. Read `2` and attach it as the root's left child.
3. Read `N` and set `2.left = null`.
4. Read `N` and set `2.right = null`. Node 2 is complete.
5. Resume at node 1 and read `3` for its right child.
6. Read `N` for `3.left`.
7. Read `N` for `3.right`.

The reconstructed tree is:

```text
       1
      / \
     2   3
```

No searching for subtree boundaries is required because preorder ordering and null markers tell us exactly when each subtree ends.

## 7. Why Use a Shared Index?

The field `i` tracks the next unconsumed token.

Consider this serialized sequence:

`1,2,N,N,3,N,N,`

After the root consumes `1`, the recursive call for the left subtree consumes `2`, followed by its two null markers. Once that call returns, the index already points to `3`, so the root's right subtree can be constructed.

We do not need to pass an updated index back from every recursive call. Java object state allows all recursive calls to use the same index.

Resetting `i` at the start of `deserialize()` is important because the same `Codec` instance may deserialize multiple trees.

## 8. Complexity Analysis

Let `n` be the number of nodes and `h` the height of the tree.

**Serialization**
- Time: `O(n)`, because each node and null child is processed once.
- Output space: `O(n)` for the serialized string.
- Recursion stack: `O(h)`.

**Deserialization**
- Time: `O(n)`, because each token is consumed once and each node is created once.
- Token storage: `O(n)` for the split array.
- Recursion stack: `O(h)`.

The worst-case recursion depth is `O(n)` for a highly skewed tree; for a balanced tree it is `O(log n)`.

## 9. Edge Cases to Test

- **Empty tree:** `N
*/