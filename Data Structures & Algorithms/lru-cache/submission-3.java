/*

# LRU Cache — LeetCode 146

## Approach: HashMap + Doubly Linked List

An LRU (Least Recently Used) Cache removes the least recently accessed key when its capacity is exceeded.

The requirement is to support both `get()` and `put()` in **O(1) average time**.

### 1. Data Structures

We combine two data structures:

- **HashMap<Integer, Node>**: Maps each key to its corresponding linked-list node, allowing O(1) average lookup.
- **Doubly Linked List**: Maintains the order of usage, allowing O(1) insertion, removal, and movement of an existing node.

We use dummy head and tail nodes to simplify pointer manipulation.

```text
HEAD ⇄ [Most Recently Used] ⇄ ... ⇄ [Least Recently Used] ⇄ TAIL
```

- Node immediately after `head` is the most recently used.
- Node immediately before `tail` is the least recently used.

Each node stores:
- `key`
- `value`
- `next`
- `prev`

The key is stored in the node so that when evicting the least recently used node, we can also remove its corresponding entry from the HashMap.

### 2. Operations

**get(key)**

1. Retrieve the node from the HashMap.
2. If the node doesn't exist, return `-1`.
3. Remove the node from its current position.
4. Move it to the head, marking it as most recently used.
5. Return its value.

**put(key, value)**

If the key already exists:
1. Retrieve the existing node.
2. Update its value.
3. Detach it from its current position.
4. Move it to the head.

If the key is new:
1. Create a new node.
2. Insert it immediately after the dummy head.
3. Add the key-node mapping to the HashMap.
4. Increment the current cache size.

If the cache exceeds capacity:
1. Identify the node immediately before the dummy tail.
2. Remove it from the doubly linked list.
3. Remove its key from the HashMap.
4. Decrement the current cache size.

### 3. Pointer Manipulation

**Removing an existing node:**

```java
node.prev.next = node.next;
node.next.prev = node.prev;
```

This reconnects the node's previous and next neighbours.

**Adding a node immediately after the dummy head:**

```java
node.prev = head;
node.next = head.next;
head.next.prev = node;
head.next = node;
```

These operations maintain both forward and backward references.

### 4. Complexity Analysis

| Operation | Time Complexity |
|---|---|
| HashMap lookup | O(1) average |
| Remove node | O(1) |
| Insert at head | O(1) |
| Evict LRU node | O(1) |
| `get()` | O(1) average |
| `put()` | O(1) average |

**Space Complexity:** O(capacity), for the HashMap and doubly linked list.

### 5. Key Learnings

- A HashMap provides fast access, while a doubly linked list maintains usage order.
- Storing node references in the HashMap avoids traversing the linked list.
- A doubly linked list allows removal of any known node in O(1).
- Dummy head and tail nodes eliminate special cases for empty lists and boundary nodes.
- Both data structures must remain synchronized during insertion, updates, and eviction.
- Existing nodes must be detached before being moved to the head to avoid corrupting neighbouring pointers.

**Core takeaway:** Combining complementary data structures can achieve time complexity that neither structure could provide independently.


*/

class LRUCache {

    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key,int value,Node next,Node prev){
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    Node head;
    Node tail;

    int currCapacity;
    int capacity;
    HashMap<Integer,Node> map;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        currCapacity = 0;
        this.capacity = capacity;
        head = new Node(-1,-1,null,null);
        tail = new Node(-1,-1,null,null);
        head.next = tail;
        tail.prev = head; 
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        removeNode(node);
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node nodeToAdd;
    //    int isExists = 0;
        if(map.containsKey(key))
        {
  //          isExists = 1;
            nodeToAdd = map.get(key);
            nodeToAdd.value = value;
                    removeNode(nodeToAdd);

        }else{
//            isExists = 0;
            nodeToAdd = new Node(key,value,null,null);
            currCapacity++;
        }
        map.put(key,nodeToAdd);
        if(currCapacity>capacity)
        {
            Node node = tail.prev;
            removeNode(node);
            node.next = null;
            node.prev = null; 
            currCapacity--;
            map.remove(node.key);
        }
        moveToHead(nodeToAdd);
    }

    public void moveToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
