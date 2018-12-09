#define BLACK true
#define RED false
#include <iostream>

class Node {
    public:
        int data;
        bool color;
        Node* left;
        Node* right;
        Node* parent;
        Node();
        Node(int data);
        Node(Node* node);
        ~Node();
};