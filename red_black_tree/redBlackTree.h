#define BLACK true
#define RED false
#include <iostream>

struct Node {
    int data;
    bool color;
    Node* left;
    Node* right;
    Node* parent;
    Node() {
        data = -1;
        color = RED;
        left = NULL;
        right = NULL;
        parent = NULL;
    }
};

class RedBlackTree {
    private:
        Node* root;
        void rightRotate(Node* node);
        void leftRotate(Node* node);
        void transplant(Node* original, Node* donor);
        void insertFixUp(Node* z);
        void removeFixUp();
        bool isLeftChild(Node* node);
        bool isRightChild(Node* node);
        Node* getUncle(Node* node);
        void showInorder(Node* node);
        void showPostorder(Node* node);
    public:
        RedBlackTree();
        RedBlackTree(RedBlackTree *rbt);
        ~RedBlackTree();
        bool search(int key);
        bool insert(int key);
        bool remove(int key);
        void show();
};