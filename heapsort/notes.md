# Heapsort
- Array A represents a heap
    - Nearly complete binary tree. Last level is only non complete. Filled left to right
- A.length: length of the array
- A.heap-size: length of the heap (valid range)

1-base array:

PARENT(i):
    return i/2

LEFT(i):
    return 2i

RIGHT(i):
    return 2i + 1

0-base array:

PARENT(i):
    return i-1/2

LEFT(i):
    return 2i + 1

RIGHT(i):
    return 2i + 2

## Max-heap property
A[PARENT(i)] >= A[i]
- Parent is always greater than or equal to its children
- largest element at root

## Min-heap property
A[PARENT(i)] <= A[i]
- Parent is always less than or equal to its children
- smallest element at root
- good for priority queues

## Maintaining the heap property
- MAX-HEAPIFY assumes LEFT(i) and RIGHT(i) are max heaps already, but A[i] may be smaller than its children
- Lets the value A[i] float down

MAX-HEAPIFY(A,i):
    l = LEFT(i)
    r = RIGHT(i)
    largest = i                                     // Get the largest value
    if l <= A.heap-size AND A[l] > A[largest]:
        largest = l
    if r <= A.heap-size AND A[r] > A[largest]:
        largest = r
    if largest != i:                                // If the largest isn't i, swap to maintain property
        swap(A[i], A[largest])
        MAX-HEAPIFY(A, largest)                     // Follow the value that is being floated down until it is no longer less than its children

runtime: T(n) <= T(2n/3) + THETA(1)
    a = 1 b = 3/2 fn = 1
    log_3/2 1 = 0
    n^0 = 1 = fn
    case 2: T(n) = THETA(lg n)
    THEREFORE RUNTIME: O(lg n)

MIN-HEAPIFY(A,i):
    l = LEFT(i)
    r = RIGHT(i)
    smallest = i
    if l <= A.heap-size AND A[l] < A[smallest]:
        smallest = l
    if r <= A.heap-size AND A[r] < A[smallest]:
        smallest = r
    if i != smallest:
        swap(A[i], A[smallest])
        MIN-HEAPIFY(A, smallest)

## Building a Heap
- Can use MAX-HEAPIFY bottom up on an array to build a max heap
- Only necessary to do ones not in last level (they are already 1 element heaps)
    - Start at FLOOR(A.length/2)

BUILD-MAX-HEAP(A):
    A.heap-size = A.length
    for i = FLOOR(A.length/2) downto 1:
        MAX-HEAPIFY(A,i)

runtime: O(nlg n)

BUILD-MIN-HEAP(A):
    A.heap-size = A.length
    for int i = FLOOR(A.length/2) downto 1:
        MIN-HEAPIFY(A,i)

## Heapsort Algorithm
- Build a Max heap from an array A
- Swap Max with end of array
- Decrement heap-size
- MAX-HEAPIFY from the root to fix whatever the swap caused

HEAPSORT(A):
    BUILD-MAX-HEAP(A)
    for i = A.length downto 2:
        swap(A[1], A[i])
        A.heap-size --
        MAX-HEAPIFY(A,1)

runtime: O(nlg n)

## Priority Queues
- Max priority queue supports the following:
    - INSERT(S, element): inserts the element into the set S, equivalent to S = S U {x}
    - MAXIMUM(S): returns the element of S with the largest key
    - EXTRACT-MAX(S): removes and returns the element of S with the largest key
    - INCREASE-KEY(S, element, newValue): increases the value of element's key to newValue, which is assumed >= element.key

HEAP-MAXIMUM(A):
    return A[1]

HEAP-EXTRACT-MAX(A):
    if A.heap-size < 1:
        error("heap underflow")
    max = A[1]
    A[1] = A[A.heap-size]
    A.heap-size --
    MAX-HEAPIFY(A, 1)
    return max

runtime: O(lg n)

HEAP-INCREASE-KEY(A, i, key):
    if key < A[i]:
        error("Key is smaller than the original")
    A[i] = key
    while i > 1 and A[PARENT(i)] < A[i]:            // Push value up if it's greater than the parent
        swap(A[PARENT(i)], A[i])
        i = PARENT(i)

runtime: O(lg n)

MAX-HEAP-INSERT(A, key):
    A.heap-size ++
    A[A.heap-size] = INTEGER.MIN
    HEAP-INCREASE-KEY(A, A.heap-size, key)          // Add the value in at the last position and push it up as high as it needs to go

runtime: O(lg n)


