/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

## Copy List with Random Pointer — LeetCode 138

### Approach: Two-Pass Traversal using HashMap

The objective is to create a deep copy of a linked list where each node contains:
- `val`: The node's value.
- `next`: Pointer to the next node.
- `random`: Pointer to any node in the list or `null`.

The copied list must preserve the original list's `next` and `random` relationships, but all pointers must reference newly created nodes rather than original nodes.

### 1. First Pass — Create Copy Nodes

- Traverse the original linked list.
- Create a new node with the same value as each original node.
- Store the mapping between the original node and its corresponding copied node in a HashMap.

`map.put(originalNode, copyNode);`

**Why use the original node as the key instead of its value?**

Different nodes can have identical values. Using the node object as the key allows us to distinguish individual nodes and maintain the correct mapping.

### 2. Second Pass — Connect Next and Random Pointers

- Traverse the original linked list again.
- Retrieve the corresponding copied node using the HashMap.
- Set its `next` pointer using the copied version of the original node's `next` pointer.
- Set its `random` pointer using the copied version of the original node's `random` pointer.

```java
Node copyNode = map.get(originalNode);

copyNode.next = map.get(originalNode.next);
copyNode.random = map.get(originalNode.random);
```

The HashMap ensures that both pointers reference copied nodes rather than original nodes.

Additionally, `map.get(null)` returns `null`, so null pointers are handled naturally without additional conditions.

### 3. Return the Copied Head

Retrieve and return the copied version of the original head:

```java
return map.get(head);
```

### Complexity Analysis

- **Time Complexity: O(n)** — Two traversals of the linked list, each taking O(n).
- **Space Complexity: O(n)** — The HashMap stores one mapping for every original node, and we create n copied nodes.

### Key Takeaways

- A deep copy requires creating entirely new node objects.
- Use object references as HashMap keys rather than node values, since values may be duplicated.
- The first pass creates all copied nodes; the second pass establishes their relationships.
- Mapping original references to copied references makes it easy to reconstruct both `next` and `random` pointers.

*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> map = new HashMap<>();
        Node originalNode = head;
        while(originalNode!=null)
        {
            Node copyNode = new Node(originalNode.val);
            map.put(originalNode,copyNode);
            originalNode = originalNode.next;
        }
        originalNode = head;
        while(originalNode!=null)
        {
            Node copyNode = map.get(originalNode);
            copyNode.next = map.get(originalNode.next);
            copyNode.random = map.get(originalNode.random);
            originalNode = originalNode.next;
        }
        return map.get(head);
    }
}
