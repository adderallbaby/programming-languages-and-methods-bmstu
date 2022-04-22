
#include <iostream>
#include "FenwickTree.h"
#include "vector"
using namespace std;
int main() {
    cout << "hello";
    vector<int> treevec;
    treevec.push_back(1);
    treevec.push_back(1);
    treevec.push_back(1);
    treevec.push_back(1);
    treevec.push_back(1);
    FenwickTree<int> fenwickTree = FenwickTree<int>(5,treevec);
    fenwickTree.update(1,1100);
    cout << fenwickTree.rangeXor(0,5);

    return 0;
}

