/**
 * Doubly linked list of ints with head and tail references.
 * addFirst, addLast, removeFirst, removeLast : O(1)
 * get, add(index), remove(index) : O(n), but traversal starts from the nearer end.
 */
public class DoublyLinkedList {
    private static class Node {
        int data;
        Node prev, next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int x) {
        Node node = new Node(x);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void addLast(int x) {
        Node node = new Node(x);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
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
        return unlink(tail);
    }

    public int remove(int index) {
        checkIndex(index);
        return unlink(getNode(index));
    }

    private int unlink(Node node) {
        if (node.prev != null) node.prev.next = node.next; else head = node.next;
        if (node.next != null) node.next.prev = node.prev; else tail = node.prev;
        size--;
        return node.data;
    }

    public int get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    public int indexOf(int x) {
        Node cur = head;
        for (int i = 0; cur != null; i++, cur = cur.next) {
            if (cur.data == x) return i;
        }
        return -1;
    }

    public boolean contains(int x) { return indexOf(x) != -1; }

    public void reverse() {
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = cur.prev;
            cur.prev = next;
            cur = next;
        }
        Node temp = head;
        head = tail;
        tail = temp;
    }

    private Node getNode(int index) {
        Node cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = tail;
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
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(cur.next != null ? " <-> " : "");
        }
        System.out.println(sb.length() == 0 ? "(empty)" : sb.toString());
    }

    public void displayBackward() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = tail; cur != null; cur = cur.prev) {
            sb.append(cur.data).append(cur.prev != null ? " <-> " : "");
        }
        System.out.println(sb.length() == 0 ? "(empty)" : sb.toString());
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addLast(20); list.addLast(30); list.addFirst(10);
        list.displayForward();                            // 10 <-> 20 <-> 30
        list.add(2, 25);
        list.displayForward();                            // 10 <-> 20 <-> 25 <-> 30
        list.displayBackward();                           // 30 <-> 25 <-> 20 <-> 10
        System.out.println("get(2): " + list.get(2));     // 25
        list.remove(1);
        list.displayForward();                            // 10 <-> 25 <-> 30
        list.reverse();
        list.displayForward();                            // 30 <-> 25 <-> 10
        System.out.println("removeLast: " + list.removeLast());   // 10
        System.out.println("removeFirst: " + list.removeFirst()); // 30
        list.displayForward();                            // 25
    }
}
