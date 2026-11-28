# DSA - Data Structures & Algorithms in Java

A collection of data structure and algorithm implementations written in Java for the **CA3 GitHub Submissions**. Every file is self-contained, has a `main` method with a small demo, and documents the time complexity of its operations. Each submission lives in its own folder.

> **Author:** J.M Kalana Yasassri &nbsp;|&nbsp; **Student ID:** UGC0625011 &nbsp;|&nbsp; **Module:** DSA

---

## Table of Contents

- [Getting Started](#getting-started)
- [Submission 1 - Stacks & Queues](#submission-1---stacks--queues)
- [Submission 2 - Queues & Linked Lists](#submission-2---queues--linked-lists)
- [Submission 3 - Circular Lists, Linked Stack & Queue, Rabbit Problem](#submission-3---circular-lists-linked-stack--queue-rabbit-problem)
- [Submission 4 - Hanoi Tower & Basic Sorting](#submission-4---hanoi-tower--basic-sorting)
- [Submission 5 - Optimized Insertion, Selection, Merge & Quick Sort](#submission-5---optimized-insertion-selection-merge--quick-sort)
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

## Submission 3 - Circular Lists, Linked Stack & Queue, Rabbit Problem

Folder: [`Submission 03`](Submission%2003)

| File | Structure | Built from | Notes |
|------|-----------|------------|-------|
| [`CircularDoublyLinkedList.java`](Submission%2003/CircularDoublyLinkedList.java) | List | Nodes with `prev`/`next`, circular | Tail is always `head.prev` |
| [`LinkedListStack.java`](Submission%2003/LinkedListStack.java) | Stack | Singly linked list | Top is the head, never overflows |
| [`LinkedListFIFOQueue.java`](Submission%2003/LinkedListFIFOQueue.java) | Queue | Singly linked list | Enqueue at tail, dequeue at head |
| [`RabbitProblem.java`](Submission%2003/RabbitProblem.java) | Algorithm | Recursion, loop, simulation | Fibonacci's rabbit problem |

### How each one works

**CircularDoublyLinkedList**
The last node links back to the head and the head links back to the last node, so there is no `null` at either end and no separate tail reference. It supports add/remove at both ends and by index, `get`, `indexOf`, `contains`, `reverse`, `rotate(k)` and forward/backward display. Index operations start from whichever end is closer.

**LinkedListStack**
Push and pop both work on the head of a singly linked list, so every operation is O(1) and the stack grows one node at a time.

**LinkedListFIFOQueue**
Keeps `head` and `tail` references. Enqueue appends at the tail and dequeue removes from the head, both in O(1).

**Rabbit Problem**
Start with one newborn pair; every pair breeds once it is a month old and then produces one new pair per month. The number of pairs in month `n` is the Fibonacci number `F(n)`: 1, 1, 2, 3, 5, 8, 13, ... Three solutions are included:
- **Recursive:** `pairs(n) = pairs(n-1) + pairs(n-2)`, O(2^n).
- **Iterative:** keeps only the last two months, O(n) time and O(1) space.
- **Age-group simulation:** tracks how many pairs are each age, so it can also handle a different breeding age, litter size, or rabbits that die after a set lifespan.

---

## Submission 4 - Hanoi Tower & Basic Sorting

Folder: [`Submission 04`](Submission%2004)

| File | Type | Notes |
|------|------|-------|
| [`HanoiTower.java`](Submission%2004/HanoiTower.java) | Recursion | Prints every move, minimum moves = 2^n - 1 |
| [`BubbleSort.java`](Submission%2004/BubbleSort.java) | Sorting | Always runs every pass |
| [`OptimizedBubbleSort.java`](Submission%2004/OptimizedBubbleSort.java) | Sorting | Early exit and shrinking range |
| [`InsertionSort.java`](Submission%2004/InsertionSort.java) | Sorting | Shifts larger items to insert each key |

### How each one works

**Hanoi Tower**
To move `n` disks from peg A to peg C: move the top `n-1` disks to the helper peg B, move the largest disk to C, then move the `n-1` disks from B to C. The program prints each move and a table showing that the move count always equals 2^n - 1.

**Bubble Sort**
Compares neighbouring items and swaps them when they are out of order, so the largest remaining item bubbles to the end on every pass. This version always performs all passes, even on a sorted array.

**Optimized Bubble Sort**
Adds two improvements: it stops as soon as a pass makes no swaps, and it only scans up to the position of the last swap, since everything after that is already in place. On a sorted array it needs just `n-1` comparisons.

**Insertion Sort**
Grows a sorted section at the front of the array. Each new item (the key) is inserted by shifting larger items one place to the right, like sorting playing cards in your hand. It is fast on nearly sorted data.

Each sorting file also counts comparisons and swaps (or shifts), and its demo shows the difference on sorted and nearly sorted input.

---

## Submission 5 - Optimized Insertion, Selection, Merge & Quick Sort

Folder: [`Submission 05`](Submission%2005)

| File | Type | Notes |
|------|------|-------|
| [`OptimizedInsertionSort.java`](Submission%2005/OptimizedInsertionSort.java) | Sorting | Binary search for the insertion position |
| [`SelectionSort.java`](Submission%2005/SelectionSort.java) | Sorting | At most n-1 swaps |
| [`MergeSort.java`](Submission%2005/MergeSort.java) | Sorting | Divide and conquer, always O(n log n) |
| [`QuickSort.java`](Submission%2005/QuickSort.java) | Sorting | Lomuto partition, last element as pivot |

### How each one works

**Optimized Insertion Sort**
Improves insertion sort in two ways. If the next item is not smaller than the last sorted item it is already in place and is skipped, keeping the best case at O(n). Otherwise binary search finds the insertion position in O(log i) comparisons instead of scanning item by item. The shifts still take O(n^2) in the worst case, and ties go to the right of equal items so the sort stays stable.

**Selection Sort**
On each pass it finds the smallest item in the unsorted part and swaps it to the front. It always makes n(n-1)/2 comparisons, but only n-1 swaps at most, which helps when writing to memory is expensive.

**Merge Sort**
Splits the array into halves, sorts each half recursively, then merges the two sorted halves using a helper array. The split is always even, so it runs in O(n log n) no matter how the input is ordered, and ties take the left item so it is stable.

**Quick Sort**
Picks the last item as the pivot and partitions the array so smaller or equal items sit on its left and larger items on its right. The pivot is then in its final position, and both sides are sorted recursively. On an already sorted array this pivot choice gives the O(n^2) worst case, which the demo shows.

All four files verify their result against `Arrays.sort` on random data and print comparison counts.

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
| LinkedListStack | O(1) | O(1) | O(1) | O(n) |
| LinkedListFIFOQueue | O(1) | O(1) | O(1) | O(n) |

### Linked list operations

| Operation | SinglyLinkedList | DoublyLinkedList |
|-----------|:----------------:|:----------------:|
| addFirst / addLast | O(1) | O(1) |
| removeFirst | O(1) | O(1) |
| removeLast | O(n) | O(1) |
| get / add(index) / remove(index) | O(n) | O(n) |
| indexOf / contains | O(n) | O(n) |
| reverse | O(n) | O(n) |

CircularDoublyLinkedList has the same complexities as DoublyLinkedList, plus `rotate(k)` in O(k mod n).

### Sorting algorithms
| Algorithm | Best | Average | Worst | Space | Stable |
|-----------|:----:|:-------:|:-----:|:-----:|:------:|
| Bubble Sort | O(n^2) | O(n^2) | O(n^2) | O(1) | Yes |
| Optimized Bubble Sort | O(n) | O(n^2) | O(n^2) | O(1) | Yes |
| Insertion Sort | O(n) | O(n^2) | O(n^2) | O(1) | Yes |
| Optimized Insertion Sort | O(n) | O(n^2) | O(n^2) | O(1) | Yes |
| Selection Sort | O(n^2) | O(n^2) | O(n^2) | O(1) | No |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n^2) | O(log n) | No |

Tower of Hanoi takes O(2^n) time and O(n) recursion depth.

### Rabbit Problem

| Method | Time | Space |
|--------|:----:|:-----:|
| Recursive | O(2^n) | O(n) |
| Iterative | O(n) | O(1) |
| Age-group simulation | O(n x lifespan) | O(lifespan) |

---

## Roadmap

Progress through the CA3 submissions:

- [x] **Submission 1** - QueueStack (push/pop friendly), Unbounded Array Stack, StackQueue (enqueue friendly)
- [x] **Submission 2** - StackQueue (dequeue friendly), ArrayCircularQueue, SinglyLinkedList, DoublyLinkedList
- [x] **Submission 3** - CircularDoublyLinkedList, LinkedListStack, LinkedListFIFOQueue, Rabbit Problem
- [x] **Submission 4** - Hanoi Tower, Bubble Sort, Optimized Bubble Sort, Insertion Sort
- [x] **Submission 5** - Optimized Insertion Sort, Selection Sort, Merge Sort, Quick Sort
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
├── Submission 03/
│   ├── CircularDoublyLinkedList.java
│   ├── LinkedListStack.java
│   ├── LinkedListFIFOQueue.java
│   └── RabbitProblem.java
├── Submission 04/
│   ├── HanoiTower.java
│   ├── BubbleSort.java
│   ├── OptimizedBubbleSort.java
│   └── InsertionSort.java
├── Submission 05/
│   ├── OptimizedInsertionSort.java
│   ├── SelectionSort.java
│   ├── MergeSort.java
│   └── QuickSort.java
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
