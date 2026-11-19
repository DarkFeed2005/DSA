/**
 * Fixed-capacity circular queue backed by an array.
 * front points at the oldest item; the rear index is (front + size) % capacity.
 * Indices wrap around, so freed slots at the start of the array are reused.
 * enqueue, dequeue, peek : O(1)
 */
public class ArrayCircularQueue {
    private final int[] data;
    private int front = 0;
    private int size = 0;

    public ArrayCircularQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        data = new int[capacity];
    }

    public void enqueue(int x) {
        if (isFull()) throw new RuntimeException("Queue is full");
        int rear = (front + size) % data.length;
        data[rear] = x;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return data[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == data.length; }
    public int size() { return size; }
    public int capacity() { return data.length; }

    public void display() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % data.length]);
            if (i < size - 1) sb.append(", ");
        }
        System.out.println(sb.append("]"));
    }

    public static void main(String[] args) {
        ArrayCircularQueue q = new ArrayCircularQueue(4);
        q.enqueue(1); q.enqueue(2); q.enqueue(3); q.enqueue(4);
        q.display();                                     // [1, 2, 3, 4]
        System.out.println("Full? " + q.isFull());       // true
        System.out.println("Dequeue: " + q.dequeue());   // 1
        System.out.println("Dequeue: " + q.dequeue());   // 2
        q.enqueue(5); q.enqueue(6);                      // wraps around
        q.display();                                     // [3, 4, 5, 6]
        System.out.println("Peek: " + q.peek());         // 3
    }
}
