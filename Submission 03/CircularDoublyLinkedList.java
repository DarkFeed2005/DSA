/**
 * Circular doubly linked list of ints.
 * The last node's next points to the head, and the head's prev points to the last node,
 * so the tail is always head.prev (no separate tail reference needed).
 * addFirst, addLast, removeFirst, removeLast : O(1)
 * get, add(index), remove(index), indexOf : O(n), index operations start from the nearer end.
 */
public class CircularDoublyLinkedList {
    private static class Node {
        int data;
        Node prev, next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private int size;

    public void addFirst(int x) {
        addLast(x);
        head = head.prev; // the new last node becomes the head
    }

    public void addLast(int x) {
        Node node = new Node(x);
        if (head == null) {
            node.next = node;
            node.prev = node;
            head = node;
        } else {
            Node tail = head.prev;
            node.next = head;
            node.prev = tail;
            tail.next = node;
            head.prev = node;
        }
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index);
        if (index == 0) { addFirst(x); return; }
        if (index == size) { addLast(x); return; }
        Node current = getNode(index);
        Node node = new Node(x);
        node.prev = current.prev;
        node.next = current;
        current.prev.next = node;
        current.prev = node;
        size++;
    }

    public int removeFirst() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        return unlink(head);
    }

    public int removeLast() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        return unlink(head.prev);
    }

    public int remove(int index) {
        checkIndex(index);
        return unlink(getNode(index));
    }

    private int unlink(Node node) {
        int value = node.data;
        if (size == 1) {
            head = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            if (node == head) head = node.next;
        }
        size--;
        return value;
    }

    public int get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    public int indexOf(int x) {
        Node cur = head;
        for (int i = 0; i < size; i++, cur = cur.next) {
            if (cur.data == x) return i;
        }
        return -1;
    }

    public boolean contains(int x) { return indexOf(x) != -1; }

    /** Moves the head k steps forward around the circle (negative k rotates backward). */
    public void rotate(int k) {
        if (size <= 1) return;
        int steps = ((k % size) + size) % size;
        head = getNode(steps);
    }

    public void reverse() {
        if (size <= 1) return;
        Node oldHead = head;
        Node cur = head;
        for (int i = 0; i < size; i++) {
            Node next = cur.next;
            cur.next = cur.prev;
            cur.prev = next;
            cur = next;
        }
        head = oldHead.next; // old head's next is now the old tail
    }

    private Node getNode(int index) {
        Node cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = head.prev;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void displayForward() {
        if (isEmpty()) { System.out.println("(empty)"); return; }
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        for (int i = 0; i < size; i++, cur = cur.next) {
            sb.append(cur.data).append(" <-> ");
        }
        System.out.println(sb.append("(back to head)"));
    }

    public void displayBackward() {
        if (isEmpty()) { System.out.println("(empty)"); return; }
        StringBuilder sb = new StringBuilder();
        Node cur = head.prev;
        for (int i = 0; i < size; i++, cur = cur.prev) {
            sb.append(cur.data).append(" <-> ");
        }
        System.out.println(sb.append("(back to tail)"));
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.addLast(20); list.addLast(30); list.addFirst(10);
        list.displayForward();                           // 10 <-> 20 <-> 30 <-> (back to head)
        list.add(2, 25);
        list.displayForward();                           // 10 <-> 20 <-> 25 <-> 30
        list.displayBackward();                          // 30 <-> 25 <-> 20 <-> 10
        System.out.println("get(2): " + list.get(2));    // 25
        list.rotate(1);
        list.displayForward();                           // 20 <-> 25 <-> 30 <-> 10
        list.reverse();
        list.displayForward();                           // 10 <-> 30 <-> 25 <-> 20
        System.out.println("removeLast: " + list.removeLast());   // 20
        System.out.println("removeFirst: " + list.removeFirst()); // 10
        list.displayForward();                           // 30 <-> 25
    }
}
