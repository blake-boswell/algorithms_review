#include "node.h"

Node::Node() {
    this->data = -1;
    this->color = RED;
    this->left = NULL;
    this->right = NULL;
    this->parent = NULL;
}
Node::Node(int data) {
    this->data = data;
    this->color = RED;
    this->left = NULL;
    this->right = NULL;
    this->parent = NULL;
}

Node::Node(Node* node) {
    std::cout << "Copy\n";
}
Node::~Node() {
    delete this->right;
    delete this->left;
    delete this->parent;
}