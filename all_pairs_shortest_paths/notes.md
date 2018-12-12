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
- W[i][j] : edge weight from vertex i to j
- p : shortest path from vertex i to j w/ at most m edges
- Assume no negative weight cycles
- If i == j -> p has weight 0 and no edges
- Else
    - Decompose path p into i -p'-> k -> j
    - p' : shortest path from i to k (d(i,j) = d(i,k) + W[k][j])
    - p' now contains at most m - 1 edges

### A recursive solution to the all pairs shortest paths problem
- L[i][j](m): min weight of any path from i to j that contains at most m edges
    - if m == 0 -> L[i][j](m) = (0 if i == j && infinity else)
    - m >= 1 -> L[i][j](m) = min( L[i][j](m - 1) and min( of any path from i to j consisting of at most m edges, by looking at all possible predessors k of j))
        - L[i][j](m) = min 1<=k<=n { L[i][k](m-1) + W[k][j] }

### Computing the shortest-path weights bottom up
- Compute a series of matrices L(m) for m = { 1,2,...,n-1 } 
- L(n-1) contains the shortest path weights
- L(1) = W

Given: L(m-1) and W, return L(m)

EXTEND-SHORTEST-PATHS(L, W):
    n = L.rows
    let L' be a new nxn matrix
    for i = 1 to n:
        for j = 1 to n:
            L'[i][j] = infinity
            for k = 1 to n:
                L'[i][j] = min(L'[i][j], L[i][k] + W[k][j])
    return L'

runtime: THETA(n^3)

SLOW-ALL-PAIRS-SHORTEST-PATHS(W):
    n = W.rows
    L(1) = W
    for m = 2 to n - 1:
        let L(m) be a new nxn matrix
        L(m) = EXTEND-SHORTEST-PATHS(L(m-1), W)
    return L(n-1)

runtime: THETA(n^4)

### Improve the runtime
Only interested in L(n-1), not all L(m) matrices

Similar to Strassen Matrix Mult we can compute L(n-1) with only ceiling(lg(n - 1)) matrix products

The following uses repeated squaring:

FASTER-ALL-PAIRS-SHORTEST-PATHS(W):
    n = W.rows
    L(1) = W
    for m = 1; m < n-1; m *= 2:
        let L(2m) be a new nxn matrix
        L(2m) = EXTEND-SHORTEST-PATHS(L(m), L(m))
    return L(m)

runtime: THETA(n^3 lgn)

## Floyd-Warshall Algorithm
- Dynamic Programming approach
- Negative weight edges may be present, but no negative weight cycles

### The Structure of a Shortest Path
- Considers intermediate vertices
    - Not the start or end vertices
- W: nxn matrix of weights from i to j
- V: {1,2,3,...,n} vertices in G
- k: max vertex in subset of V {1,2,3,...,k}
- p: min-weight path from i to j whose intermediate vertices are in {1,2,3,...,k}

Floyd-Warshall approach:
- if k is not an intermediate vertex of path p
    -> all intermediate vertices of p are in the set {1,2,...,k-1}
    Therefore: a shortest path from i to j w/ intermediate vertices in {1,2,...,k-1} is also a shortest path w/ intermediate vertices in {1,2,...,k}
- If k is an intermediate vertex of path p
    -> decompose p into i -p1-> k -p2-> j
    - p1: shortest path from i to k w/ all intermediate vertices in {1,2,...,k-1}
    - p2: shortest path from k to j w/ all intermediate vertices in {1,2,...,k-1}

### A recursive solution to the all pairs shortest paths problem
- D[i][j](k): the weight of a shortest path from i to j where all intermediate vertices are in the set {1,2,...,k}
    - if k == 0: D[i][j](0) = W[i][j]
    - if k >= 1: D[i][j](k) = min( D[i][j](k-1), D[i][k](k-1) + D[k][j](k-1) )
- D[i][j](n): the all pairs shortest path final matrix

### Computing the weights bottom up

FLOYD-WARSHALL(W):
    n = W.rows
    D(0) = W
    for k = 1 to n:
        let D(k) be a new nxn matrix
        for i = 1 to n:
            for j = 1 to n:
                D[i][j](k) = min( D[i][j](k-1), D[i][k](k-1) + D[k][j](k-1) )
    return D(n)

runtime: THETA(n^3)

### Constructing a shortest path
- P(k): Predecessor matrix w/ all intermediate values in the set {1,2,...,k}
    - if i == j OR W[i][j] = infinity: P[i][j](0) = NULL
    - else: P[i][j](0) = i
    - For values k >= 1 we use the same predecessor if the shortest path doesn't change (prior from i to j), otherwise the predecessor is the same as the prior from k to j
        - if D[i][j](k-1) <= D[i][k](k-1) + D[k][j](k-1): P[i][j](k) = P[i][j](k-1)
        - else: P[i][j](k) = P[k][j](k-1)
- This can be built as D(k) is being built, or after with the final D(n)
- As:

FLOYD-WARSHALL(W):
    n = W.rows
    D(0) = W
    let P(0) be a new nxn matrix
    for i = 1 to n:
        for j = 1 to n:
            if i == j OR W[i][j] == infinity:
                P[i][j](0) = NULL
            else:
                P[i][j](0) = i
    for k = 1 to n:
        let D(k) be a new nxn matrix
        let P(k) be a new nxn matrix
        for i = 1 to n:
            for j = 1 to n:
                if D[i][j](k-1) <= D[i][k](k-1) + D[k][j](k-1):
                    D[i][j](k) = D[i][j](k-1)                           // No change
                    P[i][j](k) = P[i][j](k-1)
                else:
                    D[i][j](k) = D[i][k](k-1) + D[k][j](k-1)            // Shorter path if intermediate k is taken
                    P[i][j](k) = P[k][j](k-1)                           // Update because the path has been changed to take i to k to j
    return D(n), P(n)

- After:

BUILD-PREDECESSOR(D(n),W):
    n = W.rows
    let P be a new nxn matrix of NULL values
    for i = 1 to n:
        for j = 1 to n:
            for k = 1 to n:
                if D[i][k] + W[k][j] == D[i][j]:        // If the distance was built from a previous intermediate vertex
                    P[i][j] = k
                if i == j:
                    P[i][j] = NULL


## Finding the transitive closure of a directed graph
If graph G contains a path from i to j for all vertex pairs i,j in G.V
    -> add the edge to E*
- Use Floyd-Warshall, and assign the weight of 1 to ea. edge of E
    - W = {1,1,1,1,...}
- If there is a path from i to j D[i][j] < n, else D[i][j] = infinity

## Johnson's Algorithm
- Much better for sparse graphs than Floyd-Warshall
- Returns a matrix of shortest path weights for all pairs of vertices OR reports there is a negative-weight cycle
- Utilizes both Dijkstra's and Bellman-Ford algorithm
- Uses the technique of reweighting
    - If all edges weights are nonnegative:
        - Use Dijkstra's to find shortest paths between all pairs of vertices once from ea. vertex
        - w/ Fib Heap -> runtime: O(V^2 lgV + VE)
    - If G has negative weight edges AND no negative weight cycles:
        - compute a new set of nonnegative edge weights that will allow for the use of the same method
        - New set of weights w' must satisfy:
            1. For all pairs of vertices u,v in V, a path from u to v is a shortest path using w IF AND ONLY IF p is also a shortest path using w'
            2. For all edges (u,v) the new weight w'(u,v) is nonnegative

- Given G and w we make a new Graph G' where V' = V U {s} fo some new vertex s not in V AND E' = E U {(s,v) : v is in V}
    - Adding a new source node
- Make the weight function w consider the new vertex
    - w(s,v) = 0 for all v in V
- G' has no negative-weight cycles IF AND ONLY IF G has no negative weight cycles
- h(v): mapping of vertex to a real number
    - h(v) = d(s,v) for all v in V'
- w'(u,v): new weight function
    - w'(u,v) = w(u,v) + h(u) - h(v) >= 0
    - Gets rid of negative weight edges by reweighting

JOHNSON(G,w):
    compute G' by adding vertex s and an edge from s to all vertices v in V
    if BELLMAN-FORD(G',w,s) == FALSE:
        error(negative-weight-cycle)
    else for ea. vertex v in G'.V:
        set h(v) to the value of d(s,v) returned from BELLMAN-FORD
        for ea. edge (u,v) in G'.E:
            w'(u,v) = w(u,v) + h(u) - h(v)
        let D be a new nxn matrix
        for ea. vertex u in G.V:
            DIJKSTRA(G,w',u) to compute d'(u,v) for all v in G.V
            for ea. vertex v in G.V:
                D[u][v] = d'(u,v) + h(v) - h(v)
        return D
