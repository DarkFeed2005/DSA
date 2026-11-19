/**
 * Singly linked list of ints with head and tail references.
 * addFirst, addLast, removeFirst : O(1)
 * removeLast, get, add(index), remove(index), indexOf : O(n)
 */
public class SinglyLinkedList {
    private static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int x) {
        Node node = new Node(x);
        node.next = head;
        head = node;
        if (tail == null) tail = node;
        size++;
    }

    public void addLast(int x) {
        Node node = new Node(x);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index);
        if (index == 0) { addFirst(x); return; }
        if (index == size) { addLast(x); return; }
        Node prev = getNode(index - 1);
        Node node = new Node(x);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public int removeFirst() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        int value = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return value;
    }

    public int removeLast() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (size == 1) return removeFirst();
        Node prev = getNode(size - 2);
        int value = tail.data;
        prev.next = null;
        tail = prev;
        size--;
        return value;
    }

    public int remove(int index) {
        checkIndex(index);
        if (index == 0) return removeFirst();
        Node prev = getNode(index - 1);
        Node target = prev.next;
        prev.next = target.next;
        if (target == tail) tail = prev;
        size--;
        return target.data;
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
        Node prev = null;
        Node cur = head;
        tail = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    private Node getNode(int index) {
        Node cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void display() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(cur.next != null ? " -> " : "");
        }
        System.out.println(sb.length() == 0 ? "(empty)" : sb.toString());
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(20); list.addLast(30); list.addFirst(10);
        list.display();                                   // 10 -> 20 -> 30
        list.add(2, 25);
        list.display();                                   // 10 -> 20 -> 25 -> 30
        System.out.println("get(2): " + list.get(2));     // 25
        System.out.println("indexOf(30): " + list.indexOf(30)); // 3
        list.remove(1);
        list.display();                                   // 10 -> 25 -> 30
        list.reverse();
        list.display();                                   // 30 -> 25 -> 10
        System.out.println("removeLast: " + list.removeLast()); // 10
        System.out.println("removeFirst: " + list.removeFirst()); // 30
        list.display();                                   // 25
    }
}
