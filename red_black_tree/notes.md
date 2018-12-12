# Insertion
Let z be the node to be inserted.
After insertion, while z.p == red:

*Note: / -> go up to grandparent case, z -> go up to parent case *

Case 1: z's uncle is red
- recolor parent and uncle black, grandparent red
- move up to grandparent

Case 2: z's uncle is black and z is a right child
- move up to the parent
- left rotate (setting up for Case 3)

Case 3: z's uncle is black and z is a left child
- recolor parent black
- recolor grandparent red
- right rotate on grandparent

@ very end set root to black

EX:
- Insert 10, 5, 2: case 3
- Insert 4: case 1
- Insert 3: case 2, then case 3

# Deletion
