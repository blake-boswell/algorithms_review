#include "redBlackTree.h"

RedBlackTree::RedBlackTree() {
    this->root = NULL;
    std::cout << "Constructor\n";
}

RedBlackTree::RedBlackTree(RedBlackTree* rbt) {
    std::cout << "COPY\n";
}

RedBlackTree::~RedBlackTree() {
    // Delete all nodes
    delete this->root;
}

void RedBlackTree::rightRotate(Node* node) {
    if (node == this->root) {
        this->root = node->left;
    }
    Node* oldLeft = node->left;
    Node* oldParent = node->parent;
    // std::cout << "Olds set\n";
    node->left = node->left->right;
    // std::cout << "T\n";
    if (node->left != NULL) {
        node->left->parent = node;
    }
    
    // std::cout << "Node->left set\n";
    oldLeft->right = node;
    if (oldLeft->right != NULL) {
        oldLeft->right->parent = oldLeft;
    }
    
    oldLeft->parent = oldParent;
}

void RedBlackTree::leftRotate(Node* node) {
    if (node == this->root) {
        this->root = node->right;
    }
    Node* oldRight = node->right;
    Node* oldParent = node->parent;
    node->right = node->right->left;
    if (node->right != NULL) {
        node->right->parent = node;
    }
    
    oldRight->left = node;
    if (node->left != NULL) {
       oldRight->left->parent = oldRight; 
    }
    
    oldRight->parent = oldParent;
}

void RedBlackTree::transplant(Node* original, Node* donor) {

}

bool RedBlackTree::search(int key) {
    return true;
}

bool RedBlackTree::isLeftChild(Node* node) {
    if (node->parent == NULL) {
        // Root
        return false;
    }
    if (node == node->parent->left) {
        return true;
    }
    return false;
}

bool RedBlackTree::isRightChild(Node* node) {
    if (node->parent == NULL) {
        // Root
        return false;
    }
    if (node == node->parent->right) {
        return true;
    }
    return false;
}

Node* RedBlackTree::getUncle(Node* node) {
    if (node->parent == NULL || node->parent->parent == NULL) {
        return NULL;
    }
    if (isLeftChild(node->parent)) {
        return node->parent->parent->right;
    }
    return node->parent->parent->left;
}

/**
 * Case 1: z's uncle is red
 *  Recolor nodes & move pointer up
 * Case 2: z's uncle is black & z is a right child
 *  Move up & Left Rotation
 * Case 3: z's uncle is black & z is a left child
 *  Recolor & Right Rotation on grandparent
 */
void RedBlackTree::insertFixUp(Node* z) {
    std::cout << "Fixup\n";
    while (z->parent != NULL && z->parent->color == RED) {
        std::cout << "In loop\n";
        if (isLeftChild(z->parent)) {
            std::cout << "Parent is a left child\n";
            Node* uncle = getUncle(z);
            if (uncle != NULL && uncle->color == RED) {
                std::cout << "Case 1\n"; 
                // Case 1
                z->parent->color = BLACK;
                uncle->color = BLACK;
                z->parent->parent->color = RED;
                z = z->parent->parent;
            } else if (isRightChild(z)) {
                std::cout << "Case 2\n";
                // Case 2
                z = z->parent;
                leftRotate(z);
            }
            // Case 3
            std::cout << "Case 3\n";
            if (z->parent != NULL) {
                z->parent->color = BLACK;
                if (z->parent->parent != NULL) {
                    z->parent->parent->color = RED;
                    std::cout << "before right rot\n";
                    rightRotate(z->parent->parent);
                }
            }
            
            // Uncle is already black so no need to recolor
            
            
        } else {
            Node* uncle = getUncle(z);
            if (uncle != NULL && uncle->color == RED) {
                // Case 1
                z->parent->color = BLACK;
                uncle->color = BLACK;
                z->parent->parent->color = RED;
                z = z->parent->parent;
            } else if (isLeftChild(z)) {
                // Case 2
                z = z->parent;
                rightRotate(z);
            }
            // Case 3
            z->parent->color = BLACK;
            z->parent->parent->color = RED;
            leftRotate(z->parent->parent);

            
        }
    }
    

    this->root->color = BLACK;
}

bool RedBlackTree::insert(int key) {
    std::cout << "Inserting " << key << std::endl;
    Node* node = this->root;
    Node* parent = NULL;
    Node* newNode = new Node();
    while (node != NULL) {
        parent = node;
        if (key < node->data) {
            // Go left
            node = node->left;
        } else {
            // Go right
            node = node->right;
        }
    }
    newNode->parent = parent;
    // Empty tree
    if (this->root == NULL) {
        newNode->data = key;
        newNode->color = BLACK;
        newNode->right = NULL;
        newNode->left = NULL;
        this->root = newNode;
    } else if (node == parent->left) {
        // Left child
        newNode->data = key;
        newNode->color = RED;
        newNode->right = NULL;
        newNode->left = NULL;
        parent->left = newNode;
    } else {
        // Right child
        newNode->data = key;
        newNode->color = RED;
        newNode->right = NULL;
        newNode->left = NULL;
        parent->right = newNode;
    }

    insertFixUp(newNode);
    return true;
}

bool RedBlackTree::remove(int key) {
    return true;
}

/**
 * Prints the tree in Inorder
 */
void RedBlackTree::showInorder(Node* node) {
    if(node == NULL) {
        return;
    }
    showInorder(node->left);
    std::cout << "[" << node->data << "] :: " << ((node->color) ? "BLACK" : "RED") << " (P:" << ((node->parent) ? node->parent->data : -1) << "), ";
    showInorder(node->right);

}

/**
 * Prints the tree in Postorder
 */
void RedBlackTree::showPostorder(Node* node) {
    if(node == NULL) {
        return;
    }
    showPostorder(node->left);
    showPostorder(node->right);
    std::cout << "[" << node->data << "] :: " << ((node->color) ? "BLACK" : "RED") << " (P:" << ((node->parent) ? node->parent->data : -1) << "), ";
}

// Purpose: Show the contents of the tree
void RedBlackTree::show() {
    std::cout << "Inorder:\n";
    this->showInorder(this->root);
    std::cout << "\nPostOrder:\n";
    this->showPostorder(this->root);
    std::cout << "\n";
}