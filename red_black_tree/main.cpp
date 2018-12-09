#include "redBlackTree.h"
using namespace std;
int main(int argc, char** argv) {
    RedBlackTree* t = new RedBlackTree();
    t->insert(41);
    t->show();
    t->insert(38);
    t->show();
    t->insert(31);
    t->show();
    t->insert(12);
    t->show();
    t->insert(19);
    t->show();
    t->insert(8);
    t->show();

    return 0;
}