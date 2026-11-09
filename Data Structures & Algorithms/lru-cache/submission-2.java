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
