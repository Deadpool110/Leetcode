package leetcode;

import java.util.HashMap;

public class _138_copyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node h = new Node(0);
        Node p = head, q = h;
        while (p != null) {
            q.next = new Node(p.val);
            q = q.next;
            p = p.next;
        }
        p = head;
        q = h.next;
        while (p != null) {
            if (p.random != null) {
                Node p1 = head;
                int tmp = 0;
                while (p1 != p.random) {
                    p1 = p1.next;
                    tmp++;
                }
                p1 = h.next;
                while (tmp > 0) {
                    p1 = p1.next;
                    tmp--;
                }
                q.random = p1;
            }
            p = p.next;
            q = q.next;
        }
        return h.next;
    }

//    回溯法
    // HashMap which holds old nodes as keys and new nodes as its values.
    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
    public Node copyRandomList1(Node head) {

        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        Node node = new Node(head.val);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList1(head.next);
        node.random = this.copyRandomList1(head.random);

        return node;
    }

//    迭代O(N)
    // Visited dictionary to hold old node reference as "key" and new node reference as the "value"
    HashMap<Node, Node> visited = new HashMap<Node, Node>();
    public Node getClonedNode(Node node) {
        // If the node exists then
        if (node != null) {
            // Check if the node is in the visited dictionary
            if (!this.visited.containsKey(node)) {
                // Otherwise create a new node, add to the dictionary and return it
                this.visited.put(node, new Node(node.val));
            }
            // If its in the visited dictionary then return the new node reference from the dictionary
            return this.visited.get(node);
        }
        return null;
    }
    public Node copyRandomList2(Node head) {

        if (head == null) {
            return null;
        }

        Node oldNode = head;

        // Creating the new head node.
        Node newNode = new Node(oldNode.val);
        this.visited.put(oldNode, newNode);

        // Iterate on the linked list until all nodes are cloned.
        while (oldNode != null) {
            // Get the clones of the nodes referenced by random and next pointers.
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            // Move one step ahead in the linked list.
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }

//    迭代O(1)
    public Node copyRandomList3(Node head) {

        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }
}
