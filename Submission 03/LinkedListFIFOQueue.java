/**
 * FIFO queue implemented with a singly linked list.
 * enqueue adds at the tail, dequeue removes from the head, so both are O(1)
 * and the queue never overflows.
 */
public class LinkedListFIFOQueue {
    private static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private Node tail;
    private int size;

    public void enqueue(int x) {
        Node node = new Node(x);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int value = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return head.data;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    public void display() {
        StringBuilder sb = new StringBuilder("front -> ");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(cur.next != null ? " -> " : "");
        }
        System.out.println(isEmpty() ? "(empty)" : sb.append(" <- rear").toString());
    }

    public static void main(String[] args) {
        LinkedListFIFOQueue q = new LinkedListFIFOQueue();
        q.enqueue(10); q.enqueue(20); q.enqueue(30);
        q.display();                                     // front -> 10 -> 20 -> 30 <- rear
        System.out.println("Peek: " + q.peek());         // 10
        System.out.println("Dequeue: " + q.dequeue());   // 10
        System.out.println("Dequeue: " + q.dequeue());   // 20
        q.enqueue(40);
        q.display();                                     // front -> 30 -> 40 <- rear
        System.out.println("Size: " + q.size());         // 2
        q.dequeue(); q.dequeue();
        System.out.println("Empty? " + q.isEmpty());     // true
    }
}
