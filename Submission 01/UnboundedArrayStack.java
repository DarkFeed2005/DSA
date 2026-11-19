/**
 * Array based stack whose capacity changes dynamically.
 * push : doubles the array when it is full.
 * pop  : halves the array when only a quarter of it is in use
 *        (quarter, not half, avoids thrashing between grow/shrink).
 * Both resize operations are amortized O(1).
 */
public class UnboundedArrayStack {
    private static final int MIN_CAPACITY = 2;
    private int[] data = new int[MIN_CAPACITY];
    private int size = 0;

    public void push(int x) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = x;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        int value = data[--size];
        if (size > 0 && size == data.length / 4 && data.length / 2 >= MIN_CAPACITY) {
            resize(data.length / 2);
        }
        return value;
    }

    public int top() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return data[size - 1];
    }

    private void resize(int newCapacity) {
        int[] newData = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        System.out.println("  [resize] capacity -> " + newCapacity);
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
    public int capacity() { return data.length; }

    public static void main(String[] args) {
        UnboundedArrayStack s = new UnboundedArrayStack();
        for (int i = 1; i <= 9; i++) {
            s.push(i);
            System.out.println("push " + i + " size=" + s.size() + " capacity=" + s.capacity());
        }
        while (!s.isEmpty()) {
            int v = s.pop();
            System.out.println("pop " + v + " size=" + s.size() + " capacity=" + s.capacity());
        }
    }
}
