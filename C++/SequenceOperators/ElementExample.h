#ifndef ELEMENTEXAMPLE_CPP
#define ELEMENTEXAMPLE_CPP

#include <string>

using namespace std;

template <typename T> int abs (T val) {
    return (val >= 0) ? val : -val;
}

template <typename T> int sgn (T val) {
    return (T(0) < val) - (val < T(0));
}

template <typename T> int sgn0 (T val) {
    return sgn(val) == 0 ? 1 : sgn(val);
}

/**
 * Пример элемента дробь a/b для проверки (с операциями проверки на равенство `==` и переворачивания `!`)
 */
class ElementExample {
private:
    const int dataA;
    const int dataB;
public:

    ElementExample (int a, int b): dataA(sgn0(b)*a), dataB(abs(b)) {
    }

    ElementExample (const ElementExample& other): dataA(other.dataA), dataB(other.dataB) {
    }

    inline const int getA() const {
        return dataA;
    }

    inline const int getB() const {
        return dataB;
    }

    ElementExample operator* (const ElementExample& y) const {
        ElementExample result(this->dataA*y.dataA, this->dataB*y.dataB);
        return result;
    }

    ElementExample operator+ (const ElementExample& y) const {
        ElementExample result(this->dataA*y.dataB + this->dataB*y.dataA, this->dataB*y.dataB);
        return result;
    }

    int getNOD () const {
        if (dataA == 0 || dataB == 0) {
            return 0; // не по алгориму NOD, но без ошибок
        }
        if (abs(dataA) % abs(dataB) == 0) {
            return abs(dataB);
        }
        if (abs(dataB) % abs(dataA) == 0) {
            return abs(dataA);
        }
        ElementExample *preResult;
        if (abs(dataA) > abs(dataB)) {
            preResult = new ElementExample(abs(dataA%dataB), abs(dataB));
        } else {
            preResult = new ElementExample(abs(dataA), abs(dataB%dataA));
        }
        int result = preResult->getNOD();
        delete preResult;
        return result;
    }

    inline string toString() const {
        int a = dataA;
        int b = dataB;
        if (a == 0) {
            if (b != 0) {
                if (b > 0) {  // 0/1
                    b = 1;
                } else {
                    b = -1;
                }
            } else {
                // 0/0
            }
        } else {
            if (b == 0) {
                if (a > 0) {  // 1/0
                    a = 1;
                } else {
                    a = -1;
                }
            } else {
                // a/b
            }
        }
        return string("")+to_string(a)+"/"+to_string(b);
    }

    ElementExample simpleCopy () const {
        int d = getNOD();
        if (d == 0) {
            return *this; //
        } else {
            return ElementExample(dataA/d, dataB/d);
        }
    }

    bool operator== (const ElementExample& other) const {
        if (this == &other) {
            return true;
        }
        int d1 = (*this).getNOD();
        int d2 = (other).getNOD();
        if (d1 == 0 || d2 == 0) {
            if (d1 != 0 || d2 != 0) {
                return false;
            } else {
                if ((*this).dataA != 0 && other.dataA != 0) { // x/0 == y/0, когда x и y !=0 (бесконечности)
                    return true;
                } else if ((*this).dataA == 0 && other.dataA == 0) { // (нули или неопределенности)
                    if ((*this).dataB != 0 && other.dataB != 0) { // 0/x == 0/y, когда x и y !=0
                        return true;
                    } else if ((*this).dataB != 0 && other.dataB != 0) { // 0/x == 0/y, когда x и y =0
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false; // x/0 != y/0, когда один из x и y !=0
                }
            }
        } else {
            return (*this).dataA * other.dataB == (*this).dataB * other.dataA; // это проверка корректна только когда нет нулей в числителях и знаменателях
        }
    }

    ElementExample operator! () const {
        ElementExample result(this->dataB, this->dataA);
        return result;
    }

};

#endif
