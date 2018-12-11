#Single Source Shortest Path
##Properties
- Shortest path weight d(u, v)

d(u, v):
    if there is a path from u to v:
        return min{w(p): u -p-> v}
    else return infinity

Varaints of single-sourc shortest path:
- Single-Destination Shortest Paths
    - reverse the direction of ea. edge in the graph reduces the problem to a single-source problem
- Single-Pair Shortest Paths
    - shortest path from u to v
    - Solving for single-source w/ source vertex u solves this problem
- All-Pairs Shortest Paths
    - find shortest path from u to v for ea pair of vertices u and v
    - Can be solved by running single-source from ea. vertex

###Optimal Structure of a Shortest Path
- Subpaths of shortest paths are shortest paths

###Negative-weight Edges
- Negative weight cycles in G make the graph not well-defined

###Cycles
- Shortest paths cannot contain a cycle
- Removing cycle produces a shorter path between 2 vertices

###Representing Shortest Paths
- Maintain a predecessor for each vertex v (v.p)
- Predecessors may not actually indicate shortest paths
- Interested in the predecessor subgraph induced by the v.p values

Shortest Path tree rooted at s is a directed subgraph G' = (V', E')
1. V' is the set of vertices reachable from s in G.
2. G' forms a rooted tree with root s
3. For ea. v in V', the simple path from s to v in G' is a shortest path from s to v in G

###Relaxation
v.d is an estimate of the shortest path from source s to v

INIT-SINGLE-SRC(G,s):
    for ea. vertex v in G.V:
        v.d = infinity
        v.p = NULL
    s.d = 0

- Relaxing an edge (u,v) consists of:
    1. Testing whether we can improve the shortest path to v found so far by going through u
    2. If so, update v.d and v.p

RELAX(u,v,w):
    if v.d > u.d + w(u,v):
        v.d = u.d + w(u,v)
        v.p = u

runtime: O(1) for relax on one edge

##Bellman-Ford
- Relax ea. edge |V| - 1 times
- Edges may be negative
- Returns whether or not there is a negative-weight cycle that is reachable from the source
    - If so, this indicates that no solution exists
    - Else, produces the shortest paths and their weights

Bellman-Ford(G,w,s):
    INIT-SINGLE-SRC(G,s)
    for i = 1 to |G.V| - 1:
        for ea. edge (u,v) in G.E:
            RELAX(u,v,w)                // RELAX all edges |V| - 1 times
    for ea. edge (u,v) in G.E:
        if v.d > u.d + w(u,v):
            return FALSE                // Check for negative weight cycle
    return TRUE

runtime: O(VE)

##Single-src shortest paths in a DAG
- Can compute shortest paths from a single source in THETA(V+E) after a topological sort

DAG-SHORTEST-PATHS(G,w,s):
    Topologically sort the vertices of G
    INIT-SINGLE-SRC(G,s)
    for ea. vertex u in topologically sorted order:
        for ea. vertex v in G.Adj[u]:
            RELAX(u,v,w)

runtime: THETA(V+E)

##Dijkstra's
- Greedy algorithm
- All edge weights are nonegative
- Relax ea. edge exactly one time

- Maintain set S of vertices whose final shortest-path weights from the source s have already been determined
- Repeatedly:
    1. select vertex u in V-S w/ the minimum shortest-path estimate
    2. add u to S
    3. relax all edges leaving u

DIJKSTRA(G,w,s):
    INIT-SINGLE-SRC(G,s)
    S : empty set
    Q : min-priority queue keyed by d values
    Q = G.V
    while Q is not empty:
        u = Q.EXTRACT-MIN
        S = S U {u}
        for ea. vertex v in G.Adj[u]:
            RELAX(u,v,w)

runtime: O(V^2)
- With an array of vertices numbered 1 - |V| we can use an array @ O(1) time for INSERT and DECREASE-KEY in the min-priority queue
- EXTRACT-MIN takes O(V) time for search    <- O(V) V times





