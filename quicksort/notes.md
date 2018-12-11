# Quicksort
## Description
3 step divide and conquer process for sorting typical subarray A[p..r]
- Divide
    - Partition A into 2 subarrays A[p..q-1] and A[q+1..r] such that ea. element of A[p..q-1] is less than or equal to A[q]
- Conquer
    - Sort the subarrays A[p..q-1] and A[q+1..r] by recursive calls to quicksort
- Combine
    - The subarrays are already sorted, no work is necessary to combine
    - Entire array A[p..r] is sorted

QUICKSORT(A,p,r):
    if p < r:
        q = PARTITION(A,p,r)
        QUICKSORT(A,p,q-1)
        QUICKSORT(A,q+1,r)

initial call is QUICKSORT(A,1,A.length)

Partition the array into potentially 4 regions
- This keeps it to where a pivot point (A[r]) separates the subarray into 3 sections
- left elements are all less than or equal to pivot
- right elements are all greater than the pivot
- the pivot element
- the position of the pivot element is returned

p-i, i-j, j-r, r
- x is chosen as the value that p-i must be less than, and i-j must be greater than
- j-r is the undiscovered section
- after all the iterations of j, swap the first of the greater than section with r

PARTITION(A,p,r):
    x = A[r]
    i = p-1
    for j = p to r-1:
        if A[j] <= x:
            i++
            swap(A[i], A[j])
    swap(A[i+1], A[r])
    return i+1

## Worst case partitioning
T(n) = T(n-1) + THETA(n) = THETA(n^2)
- Split into 2 of size n-1 and 0, returns 1st index location
- sequence 1 + 2 + 3 + .. + n-2 + n-1 = n(n+1)/2? = THETA(n^2)

## Best case partitioning
T(n) = 2T(n/2) + THETA(n)
- Split into 2 of half size
a = 2 b = 2 fn = n
log_2 2 = 1
n^1 = n
Case 2 of master thm
T(n) = THETA(nlg n)