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
