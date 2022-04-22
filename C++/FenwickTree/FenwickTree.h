#include <utility>
#include <vector>
#ifndef UNTITLED9_FENWICKTREE_H
#define UNTITLED9_FENWICKTREE_H


template <typename T> class FenwickTree{
private:
    std::vector<T> tree;
    int n;
public:
    FenwickTree (int _n, std::vector<T> tree) : n(_n), tree(std::move(tree)) {}

    static constexpr int lowbit(int x) {
        return x & (-x);
    }
    void update(int x, T d) {
        while (x <= n) {
            tree[x] += d;
            x += lowbit(x);
        }
    }

    int getXOR( int index)
    {
        int ans = 0;
        index += 1;


        while (index > 0) {


            ans ^= tree[index];


            index -= index & (-index);
        }
        return ans;
    }
    int rangeXor(int l, int r)
    {
        return getXOR(r)
               ^ getXOR(l - 1);
    }
};

#endif //UNTITLED9_FENWICKTREE_H
