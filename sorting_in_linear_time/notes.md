# Sorting in Linear Time
## Counting Sort
- Stable sort
- Assumes each of the n input elements is an integer in the range 0 to k, for some k
- When k = O(n) the sort runs in THETA(n) time
- For ea. input element x how many elements are less than x
- Use this to place element x directly into its position in the output array
- Use 2 extra arrays:
    - B[1..n]: sorted output
    - C[0..k]: temp storage
        - Phase 1: number of elements equal to index i
        - Phase 2: number of elements less than or equal to index i
- Finally, go in reverse from A.len downto 1
    - Find the value in A[j] and set it to B[C[A[j]]], reducing the count for it in C[A[j]]

COUNTING-SORT(A,B,k):
    C[0..k]: new array
    for i = 0 to k:             // init to zeros for counts
        C[i] = 0
    for i = 1 to n:             // C now holds the counts of ea. element from A
        C[A[i]]++
    for i = 1 to k:             // C now holds the counts of the number of elements <= i
        C[i] += C[i-1]
    for i = A.len downto 1:     // Find where to place A[i] in B
        B[C[A[i]]] = A[i]
        C[A[i]]--

runtime: THETA(n + k)
if k = O(n) -> runtime: THETA(n)

## Radix Sort
- Assume ea. element in array A has d digits where digit 1 is the lowest order digit and digit d is the highest order

RADIX-SORT(A,d):
    for i = 1 to d:
        stable sort to sort A on digit i

runtime: THETA(d(n + k))
- ea. digit is in the range 0 to k-1 -> counting sort

### Breaking each key into digits
- Given n b-bit numbers and any positive int r <= b

## Bucket Sort
- Assumes input is drawn from a uniform distribution
- Input is uniformly and independently distributed across the interval [0,1)

BUCKET-SORT(A):
    let B[0..n-1] be a new array
    n = A.len
    for i = 0 to n:
        B[i] = new empty list
    for i = 1 to n:
        B[FLOOR(n*A[i])].add(A[i])
    for i = 0 to n -1:
        sort B[i] with insertion sort
    concatenate B[0], B[1],.., B[n-1] together

runtime: THETA(n)