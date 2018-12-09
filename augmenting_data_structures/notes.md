# CH. 14 Augmenting Data Structures
## 14.1 Dynamic order statistics
### Order Statistics:
EX: the ith order statistic of a set of n elements (i is in {1,2,...,n}) is the element with the ith smallest key
using a modified red-black tree we can find any order stat in O(lg n) time and rank in O(lg n) time
- [+] x.size: the num of internal nodes in the subtree rooted at x (including x)
    x.size = x.left.size + x.right.size + 1
#### Retrieving an element with a given rank
- OS-SELECT(x,i): returns a pointer to the node with the ith smallest key in the subtree rooted at x.
- For ith smallest key in an OS tree T: OS-SELECT(T.root, i)

OS-SELECT(x,i):
    r = x.left.size + 1
    if i == r:
        return x
    elseif i < r:
        return OS-SELECT(x.left, i)
    else:
        return OS-SELECT(x.right, i-r)

Idea:
- x.left.size: number of nodes that come before x in a n inorder tree walk of the subtree rooted at x
- r = x.left.size + 1: rank of x in the subtree rooted at x
- if the rank of x is the ith smallest key -> return x
- if the rank of x is greater than the ith smallest key, we know we must go down the left path
- if the rank of x is less than the ith smallest key, we must search the right path for the greater key.

Time is proportional to height of tree. Red-Black tree's height is O(lg n) where n is number of nodes.
Runtime: O(lg n) for n elements

#### Determining the rank of an element
- Given a pointer to a node x, OS-RANK returns the position of x determined by an inorder tree walk.

OS-RANK(T,x):
    r = x.left.size + 1
    y = x
    while y != T.root:
        if y == y.p.right:
            r = r + y.p.left.size + 1
        y = y.p
    return r

Idea:
- x.left.size + 1: rank of x in the subtree rooted at x
- fix up rank if x is down any right path
- y: walks up the tree, if y is a right child -> add the size of the parents left subtree and the parent (gets parent's rank) to rank

Time is proportional to height of tree.
Runtime: O(lg n) for n elements

#### Maintaining subtree sizes
Insertion:
- Going down
    - increment x.size for ea. node traversing down for the insertion
    - new node gets size 1
- Returning up (changes on rotations)
    - Left rotate x
        - let y be x.left, y.size = x.size (b/c it is now in x's position)
        - x.size = x.left.size + x.right.size + 1 (standard for setting size on the tree)
    - Right rotate x
        - let y be x.right, y.size = x.size (same reason)
        - x.size = x.left.size + x.right.size + 1 (same reason)

Deletion:
- Returning up
    - decrement x.size on ea. node traversing back up

