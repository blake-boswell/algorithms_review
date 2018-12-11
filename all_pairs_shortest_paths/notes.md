# All Pairs Shortest Paths
Run single source algorithm |V| times, one time for each vertex as the source.
Dijkstras
- O(V^3) w/ array implementation of min-priority queue
- O(VE lgV) w/ min-heap for min-priority queue (improvement for sparse graph)
- O(V^2 lgV + VE) w/ Fibonacci heap for min-priority queue

Bellman-Ford
- O(V^2 E) or O(V^4) on a dense graph

## Properties of the following algorithms
- D: nxn matrix output
    - ea. entry D[i][j] holds the weight of the shortest path from i to j
- P: nxn predecessor matrix
    - ea. entry P[i][j] represents the predecessor of j on some shortest path from i
    - P[i][j] is NULL if i == j or there is no path from i to j

## Shortest Paths and matrix multiplication
Dynamic Programming approach to create an algorithm in O(V^4), then improve upon it to O(V^3 lgV)

### Structure of a shortest path (optimal solution)
- All subpaths of a shortest path are shortest paths
- Adj[i][j] : adjacency matrix from vertex i to j
- p : shortest path from vertex i to j w/ at most m edges
- Assume no negative weight cycles
- If i == j -> p has weight 0 and no edges
- Else
    - Decompose path p into i -p'-> k -> j
    - p' : shortest path from i to k (d(i,j) = d(i,k) + Adj[k][j])
    - p' now contains at most m - 1 edges

### A recursive solution to the all pairs shortest paths problem
- L[i][j](m): min weight of any path from i to j that contains at most m edges
    - if m == 0 -> L[i][j](m) = (0 if i == j && infinity else)
    - m >= 1 -> L[i][j](m) = min( L[i][j](m - 1) and min( of any path from i to j consisting of at most m edges, by looking at all possible predessors k of j))
        - L[i][j](m) = min 1<=k<=n { L[i][k](m-1) + Adj[k][j] }

### Computing the shortest-path weights bottom up
- Compute a series of matrices L(m) for m = { 1,2,...,n-1 } 
- L(n-1) contains the shortest path weights
- L(1) = Adj

Given: L(m-1) and Adj, return L(m)

EXTEND-SHORTEST-PATHS(L, Adj):
    n = L.rows
    let L' be a new nxn matrix
    for i = 1 to n:
        for j = 1 to n:
            L'[i][j] = infinity
            for k = 1 to n:
                L'[i][j] = min(L'[i][j], L[i][k] + Adj[k][j])
    return L'

runtime: THETA(n^3)

SLOW-ALL-PAIRS-SHORTEST-PATHS(Adj):
    n = Adj.rows
    L(1) = Adj
    for m = 2 to n - 1:
        let L(m) be a new nxn matrix
        L(m) = EXTEND-SHORTEST-PATHS(L(m-1), Adj)
    return L(n-1)

runtime: THETA(n^4)

### Improve the runtime
Only interested in L(n-1), not all L(m) matrices

Similar to Strassen Matrix Mult we can compute L(n-1) with only ceiling(lg(n - 1)) matrix products

The following uses repeated squaring:

FASTER-ALL-PAIRS-SHORTEST-PATHS(Adj):
    n = Adj.rows
    L(1) = Adj
    for m = 1; m < n-1; m *= 2:
        let L(2m) be a new nxn matrix
        L(2m) = EXTEND-SHORTEST-PATHS(L(m), L(m))
    return L(m)

runtime: THETA(n^3 lgn)

## Floyd-Warshall Algorithm
- Dynamic Programming approach

## Finding the transitive closure of a directed graph
## Johnson's Algorithm