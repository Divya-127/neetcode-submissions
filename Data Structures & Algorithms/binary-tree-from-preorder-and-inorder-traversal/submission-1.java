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
## Construct Binary Tree from Preorder and Inorder Traversal — LeetCode 105

### Approach: Recursive DFS + HashMap

**Core idea:** Preorder tells us which node is the root, while inorder tells us how to divide the nodes into left and right subtrees.

- **Preorder:** Root → Left → Right
- **Inorder:** Left → Root → Right

Since preorder visits the root before its children, we maintain a shared `preIndex` to select the next root. A HashMap stores each value's index in inorder, allowing us to find the root's position efficiently.

### Algorithm

1. Initialize `preIndex = 0`.
2. Build a HashMap mapping each inorder value to its index.
3. Call the recursive helper with the complete inorder range: `build(preorder, 0, inorder.length - 1)`.
4. **Base case:** If `inStart > inEnd`, the current subtree is empty, so return `null`.
5. Select the root using `preorder[preIndex++]` and create a `TreeNode`.
6. Find the root's position in inorder using `inorderMap.get(rootVal)`.
7. Recursively construct the left subtree using the inorder range before the root.
8. Recursively construct the right subtree using the inorder range after the root.
9. Return the root after both children have been attached.

### Key Insight: Why We Need Only Inorder Boundaries

We do not need to pass preorder boundaries into the helper. Preorder already provides the root sequence in the correct order, and the shared `preIndex` advances whenever a root is selected.

The inorder boundaries identify the current subtree. Finding the root's inorder index splits that subtree into its left and right portions.

**Important:** Always build the left subtree before the right subtree. Preorder visits the root, then the entire left subtree, and finally the right subtree. This ensures `preIndex` selects the correct root for every recursive call.

### Example

Input:

- `preorder = [1, 2, 3, 4]`
- `inorder = [2, 1, 3, 4]`

Construction:

1. Root is `1`. In inorder, `2` is on the left and `3, 4` are on the right.
2. The left subtree constructs node `2`.
3. The right subtree constructs node `3`, with `4` as its right child.

Result:

```text
      1
     / \
    2   3
         \
          4
```

### Complexity Analysis

- **Time:** \(O(n)\) average — building the map takes \(O(n)\), and each node is processed once with average \(O(1)\) map lookups.
- **Space:** \(O(n)\) for the HashMap and \(
*/

class Solution {
    int preIndex;
    Map<Integer,Integer> inorderMap = new HashMap<Integer,Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        inorderMap.clear();
        for(int i=0;i<inorder.length;i++)
        {
            inorderMap.put(inorder[i],i);
        }
        return build(preorder,0,inorder.length-1);
    }
    public TreeNode build(int[] preorder, int inStart, int inEnd)
    {
        if(inStart>inEnd)
        {
            return null;
        }   
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inorderMap.get(rootVal);
        root.left = build(preorder,inStart,rootIndex-1);
        root.right = build(preorder,rootIndex+1,inEnd);
        return root;
    }
}
