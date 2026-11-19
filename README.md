# DSA - Data Structures & Algorithms in Java

A collection of data structure and algorithm implementations written in Java for the **CA3 GitHub Submissions**. Every file is self-contained, has a `main` method with a small demo, and documents the time complexity of its operations. Each submission lives in its own folder.

> **Author:** J.M Kalana Yasassri &nbsp;|&nbsp; **Student ID:** UGC0625011 &nbsp;|&nbsp; **Module:** DSA

---

## Table of Contents

- [Getting Started](#getting-started)
- [Submission 1 - Stacks & Queues](#submission-1---stacks--queues)
- [Submission 2 - Queues & Linked Lists](#submission-2---queues--linked-lists)
- [Complexity Summary](#complexity-summary)
- [Roadmap](#roadmap)
- [Project Structure](#project-structure)
- [License](#license)

---

## Getting Started

**Requirements:** JDK 11 or newer.

```bash
git clone https://github.com/DarkFeed2005/DSA.git
cd DSA

# go into a submission folder, then compile and run any file
cd "Submission 01"
javac QueueStackPushFriendly.java
java QueueStackPushFriendly
```

Each file runs independently, so there is no build tool or setup.

---

## Submission 1 - Stacks & Queues

Folder: [`Submission 01`](Submission%2001)

| File | Structure | Built from | Friendly operation |
|------|-----------|------------|--------------------|
| [`QueueStackPushFriendly.java`](Submission%2001/QueueStackPushFriendly.java) | Stack | Two queues | Push is O(1) |
| [`QueueStackPopFriendly.java`](Submission%2001/QueueStackPopFriendly.java) | Stack | Two queues | Pop is O(1) |
| [`UnboundedArrayStack.java`](Submission%2001/UnboundedArrayStack.java) | Stack | Resizable array | Push and pop are amortized O(1) |
| [`StackQueueEnqueueFriendly.java`](Submission%2001/StackQueueEnqueueFriendly.java) | Queue | Two stacks | Enqueue is O(1) |

### How each one works

**QueueStack - Push friendly**
Push enqueues straight into `q1`. Pop moves `n-1` items into `q2`, removes the last item from `q1` (the stack top), then swaps the two queues.

**QueueStack - Pop friendly**
Push enqueues the new item into the empty `q2`, moves everything from `q1` behind it, then swaps the queues. This keeps the newest item at the front, so pop is a single dequeue.

**Unbounded Array Stack**
The backing array changes size on both operations:
- **Push** doubles the capacity when the array is full.
- **Pop** halves the capacity when only a quarter of it is used.

Shrinking at a quarter rather than a half prevents repeated grow/shrink cycles when the size hovers around a boundary.

**StackQueue - Enqueue friendly**
Enqueue pushes onto `s1`. Dequeue moves everything to `s2` (reversing the order), pops the front item, then moves the rest back to `s1`.

---

## Submission 2 - Queues & Linked Lists

Folder: [`Submission 02`](Submission%2002)

| File | Structure | Built from | Notes |
|------|-----------|------------|-------|
| [`StackQueueDequeueFriendly.java`](Submission%2002/StackQueueDequeueFriendly.java) | Queue | Two stacks | Dequeue is O(1) |
| [`ArrayCircularQueue.java`](Submission%2002/ArrayCircularQueue.java) | Queue | Fixed array | Indices wrap around, all operations O(1) |
| [`SinglyLinkedList.java`](Submission%2002/SinglyLinkedList.java) | List | Nodes with `next` | Head and tail references |
| [`DoublyLinkedList.java`](Submission%2002/DoublyLinkedList.java) | List | Nodes with `prev`/`next` | Traversal starts from the nearer end |

### How each one works

**StackQueue - Dequeue friendly**
Enqueue moves everything from `s1` to `s2`, pushes the new item onto `s1`, then moves everything back. The oldest item always ends up on top of `s1`, so dequeue is a single pop.

**ArrayCircularQueue**
Stores `front` and `size`; the rear slot is `(front + size) % capacity`. Dequeue just advances `front`, and the freed slot is reused by later enqueues, so no items are ever shifted.

**SinglyLinkedList**
Supports `addFirst`, `addLast`, `add(index)`, `removeFirst`, `removeLast`, `remove(index)`, `get`, `indexOf`, `contains`, `reverse` and `display`. The tail reference makes `addLast` O(1), while `removeLast` still needs O(n) because there is no `prev` link.

**DoublyLinkedList**
Same operations as above, plus `displayBackward`. With `prev` links, `removeLast` is O(1), and `get`, `add(index)` and `remove(index)` walk from whichever end is closer to the index.

---

## Complexity Summary

| Structure | Push / Enqueue | Pop / Dequeue | Peek / Top | Space |
|-----------|:--------------:|:-------------:|:----------:|:-----:|
| QueueStack (push friendly) | O(1) | O(n) | O(n) | O(n) |
| QueueStack (pop friendly) | O(n) | O(1) | O(1) | O(n) |
| Unbounded Array Stack | O(1) amortized | O(1) amortized | O(1) | O(n) |
| StackQueue (enqueue friendly) | O(1) | O(n) | O(n) | O(n) |
| StackQueue (dequeue friendly) | O(n) | O(1) | O(1) | O(n) |
| ArrayCircularQueue | O(1) | O(1) | O(1) | O(capacity) |

### Linked list operations

| Operation | SinglyLinkedList | DoublyLinkedList |
|-----------|:----------------:|:----------------:|
| addFirst / addLast | O(1) | O(1) |
| removeFirst | O(1) | O(1) |
| removeLast | O(n) | O(1) |
| get / add(index) / remove(index) | O(n) | O(n) |
| indexOf / contains | O(n) | O(n) |
| reverse | O(n) | O(n) |

---

## Roadmap

Progress through the CA3 submissions:

- [x] **Submission 1** - QueueStack (push/pop friendly), Unbounded Array Stack, StackQueue (enqueue friendly)
- [x] **Submission 2** - StackQueue (dequeue friendly), ArrayCircularQueue, SinglyLinkedList, DoublyLinkedList
- [ ] **Submission 3** - CircularDoublyLinkedList, LinkedListStack, LinkedListFIFOQueue, Rabbit Problem
- [ ] **Submission 4** - Hanoi Tower, Bubble Sort, Optimized Bubble Sort, Insertion Sort
- [ ] **Submission 5** - Optimized Insertion Sort, Selection Sort, Merge Sort, Quick Sort
- [ ] **Submission 6** - Optimized Quick Sort, Binary Search Tree, AVL Tree

---

## Project Structure

```
DSA/
├── Submission 01/
│   ├── QueueStackPushFriendly.java
│   ├── QueueStackPopFriendly.java
│   ├── UnboundedArrayStack.java
│   └── StackQueueEnqueueFriendly.java
├── Submission 02/
│   ├── StackQueueDequeueFriendly.java
│   ├── ArrayCircularQueue.java
│   ├── SinglyLinkedList.java
│   └── DoublyLinkedList.java
├── .gitignore
├── LICENSE
└── README.md
```

---

## Notes

- Files are named after the structure they implement, as required by the submission guidelines.
- Empty-structure operations (pop, dequeue, peek) throw a `RuntimeException` with a clear message.
- Demos in each `main` method print results so the behavior can be checked at a glance.

---

## License

This project is licensed under the [MIT License](LICENSE).
