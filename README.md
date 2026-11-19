# DSA - Data Structures & Algorithms in Java

A collection of data structure and algorithm implementations written in Java for the **CA3 GitHub Submissions**. Every file is self-contained, has a `main` method with a small demo, and documents the time complexity of its operations.

> **Author:** J.M Kalana Yasassri &nbsp;|&nbsp; **Student ID:** UGC0625011 &nbsp;|&nbsp; **Module:** DSA

---

## Table of Contents

- [Getting Started](#getting-started)
- [Submission 1 - Stacks & Queues](#submission-1---stacks--queues)
- [Complexity Summary](#complexity-summary)
- [Roadmap](#roadmap)
- [Project Structure](#project-structure)

---

## Getting Started

**Requirements:** JDK 11 or newer.

```bash
git clone https://github.com/darkfeed2005/DSA.git
cd DSA

# compile and run any file
javac QueueStackPushFriendly.java
java QueueStackPushFriendly
```

Each file runs independently, so there is no build tool or setup.

---

## Submission 1 - Stacks & Queues

| File | Structure | Built from | Friendly operation |
|------|-----------|------------|--------------------|
| [`QueueStackPushFriendly.java`](QueueStackPushFriendly.java) | Stack | Two queues | Push is O(1) |
| [`QueueStackPopFriendly.java`](QueueStackPopFriendly.java) | Stack | Two queues | Pop is O(1) |
| [`UnboundedArrayStack.java`](UnboundedArrayStack.java) | Stack | Resizable array | Push and pop are amortized O(1) |
| [`StackQueueEnqueueFriendly.java`](StackQueueEnqueueFriendly.java) | Queue | Two stacks | Enqueue is O(1) |

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

## Complexity Summary

| Structure | Push / Enqueue | Pop / Dequeue | Peek / Top | Space |
|-----------|:--------------:|:-------------:|:----------:|:-----:|
| QueueStack (push friendly) | O(1) | O(n) | O(n) | O(n) |
| QueueStack (pop friendly) | O(n) | O(1) | O(1) | O(n) |
| Unbounded Array Stack | O(1) amortized | O(1) amortized | O(1) | O(n) |
| StackQueue (enqueue friendly) | O(1) | O(n) | O(n) | O(n) |

---

## Roadmap

Progress through the CA3 submissions:

- [x] **Submission 1** - QueueStack (push/pop friendly), Unbounded Array Stack, StackQueue (enqueue friendly)
- [ ] **Submission 2** - StackQueue (dequeue friendly), ArrayCircularQueue, SinglyLinkedList, DoublyLinkedList
- [ ] **Submission 3** - CircularDoublyLinkedList, LinkedListStack, LinkedListFIFOQueue, Rabbit Problem
- [ ] **Submission 4** - Hanoi Tower, Bubble Sort, Optimized Bubble Sort, Insertion Sort
- [ ] **Submission 5** - Optimized Insertion Sort, Selection Sort, Merge Sort, Quick Sort
- [ ] **Submission 6** - Optimized Quick Sort, Binary Search Tree, AVL Tree

---

## Project Structure

```
DSA/
├── QueueStackPushFriendly.java
├── QueueStackPopFriendly.java
├── UnboundedArrayStack.java
├── StackQueueEnqueueFriendly.java
└── README.md
```

---

## Notes

- Files are named after the structure they implement, as required by the submission guidelines.
- Empty-structure operations (pop, dequeue, peek) throw a `RuntimeException` with a clear message.
- Demos in each `main` method print results so the behavior can be checked at a glance.
