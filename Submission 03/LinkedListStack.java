/**
 * Stack implemented with a singly linked list.
 * The top of the stack is the head of the list, so every operation is O(1)
 * and the stack never overflows (it grows one node at a time).
 */
public class LinkedListStack {
    private static class Node {
        int data;
        Node next;
        Node(int data, Node next) { this.data = data; this.next = next; }
    }

    private Node top;
    private int size;

    public void push(int x) {
        top = new Node(x, top);
        size++;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        int value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() { return top == null; }
    public int size() { return size; }

    public void display() {
        StringBuilder sb = new StringBuilder("top -> ");
        for (Node cur = top; cur != null; cur = cur.next) {
            sb.append(cur.data).append(cur.next != null ? " -> " : "");
        }
        System.out.println(isEmpty() ? "(empty)" : sb.toString());
    }

    public static void main(String[] args) {
        LinkedListStack s = new LinkedListStack();
        s.push(10); s.push(20); s.push(30);
        s.display();                                     // top -> 30 -> 20 -> 10
        System.out.println("Peek: " + s.peek());         // 30
        System.out.println("Pop: " + s.pop());           // 30
        System.out.println("Pop: " + s.pop());           // 20
        s.push(40);
        s.display();                                     // top -> 40 -> 10
        System.out.println("Size: " + s.size());         // 2
        s.pop(); s.pop();
        System.out.println("Empty? " + s.isEmpty());     // true
    }
}
