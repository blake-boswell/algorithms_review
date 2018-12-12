# Elementary Graph Algorithms
## Breadth-first Search
- Given a graph G and a source vertex s:
- explore the edges to discover every vertex reachable from s
- For any vertex v reachable from s, the simple path in the bfs tree from s to v is a "shortest path" from s to v in G
- Breaking ea. vertice into color groups is helpful for representing:
    - undiscovered (white)
    - discovered and in queue (grey)
    - finished (black)

BFS(G,s):
    for ea. vertex u in G.V - {s}:          /**
        u.color = WHITE
        u.d = infinity
        u.p = NULL
    s.color = GREY
    s.d = 0                                     INIT
    s.p = NULL
    Q = empty queue
    Q.enqueue(s)                            */
    while Q not empty:
        u = Q.dequeue()                     // Take next in queue out
        for ea. v in G.Adj[u]:              // Go through all adjacent vertices
            if v.color == WHITE:            // If the adjacent vertice is undiscovered
                v.color = GREY                  // Set adjacent vertice to discovered && in queue
                v.d = u.d + 1                   // The adjacent vertice is one unit further than it's predecessor
                v.p = u                         // Set the predecessor to u (it's the vertex it came from)
                Q.enqueue(v)                    // Put v in the queue (this lets us do all the adjacent vertices to u before moving down tree)
        u.color = BLACK                     // We've gone through all the adjacent undiscovered vertices, this vertex is done, move to the next

runtime: O(V + E)

- Useful for finding shortest paths

## Depth-first Search
- Search all edges coming from the most recently explored vertex
- Backtrack
- Use a stack or recursion stack
- Repeat until all vertices are discovered
- Predecessor subgraph forms a depth-first forest w/ many depth first trees
- Same rule with colors:
    - undiscovered (white)
    - discovered and in the stack (grey)
    - finished (black)
- Can keep "timestamps" of order that it was first discovered (v.start, set v to grey) and when the search on v is finished (v.fin, set v to black)

### Properties of DFS
- Discovery and finish times yeild a parenthesis structure
    - Discovery of u => (u; Finish u => u);

### Classification of Edges
- Type of edge provides info
    - EX: Directed graph is acyclic if there is NO back edges
    - EX: A back edge denotes a cycle
- Types:
    - Tree edges
        - Edge (u, v) is a tree edge if v was discovered by exploring edge (u, v) (v.p == u)
    - Back edge
        - Edge (u, v) connects vertex u to an ancestor v. Self loops are also back edges
    - Forward edge
        - nontree edge (u, v) connecting u to a descendant v in a df tree
    - Cross edge
        - All other edges

- Colors-to-types on vertex v from edge (u, v):
    - WHITE => tree edge
    - GREY => back edge
    - BLACK => forward or cross edge

## Topological Sort
A linear ordering of all vertices in a DAG such that if graph G contains an edge (u,v), then u appears before v in the ordering

TOPOLOGICAL-SORT(G):
    DFS(G) to compute finishing times v.fin for ea. vertex v
    as ea. vertex finishes insert it into the front of a linked list
    return the linked list of vertices

runtime: O(V + E)