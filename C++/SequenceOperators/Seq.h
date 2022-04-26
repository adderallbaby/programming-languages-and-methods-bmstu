//
// Created by Куйвашев Дмитрий on 26.04.2022.
//

#ifndef UNTITLED13_SEQ_H
#define UNTITLED13_SEQ_H




#include "ElementExample.h"

#include <list>
#include <iostream>
#include <string>

using namespace std;

/**
 * Последовательность Seq элементов (Element с операциями проверки на равенство `==` и переворачивания `!`)
 */
template<typename Element>
class Seq {
protected:
    list<Element> a;

    /**
     * Редукция последовательности (все соседние взаимнообратные элементы уничтожаются до тех пор, пока таких не останется).
     * Возвращает false, если были произведены изменения, иначе true (состояние чистое, значит true).
     */
    bool reduct () {
        bool state = true;
        typename list<Element>::iterator i = a.begin(); // текущий
        typename list<Element>::iterator h = i; // предыдущий
        while ((++i) != a.end()) {
            Element* x = new Element(*h);
            Element* y = new Element(*i);

            while (i != a.end() && (*x) == !(*y)) {
                h = a.erase(h, ++i);
                state = false;
                if (h == a.end()) {
                    break;
                }
                i = next(h);
                delete x;
                delete y;
                x = new Element(*h);
                y = new Element(*i);
            }
            delete x;
            delete y;
            h = i;
        }
        if (!state) {
            if (true != reduct()) {
                cerr << "Error: state is broken" << endl;
            }
            return false;
        } else {
            return true;
        }
    }
public:
    /**
     * Конструктор пустой последовательности
     */
    Seq (): a() {
    }

    Seq (const list<Element> &b): a() {
        a = b;
        reduct();
    }

    Seq (const Seq<Element> &other) {
        a = other.a;
        ///reduct(); // т.к. мы присваиваем из класса Seq, то редукция в нем уже была выполнена
    }

    /**
     * Оператор присваивания (дополнительно)
     */
    Seq<Element>& operator= (const Element& other) {
        if (this == &other || this->a == other.a) {
            return *this;
        }
        this->a = other.a;
        return *this;
    }

    /**
     * Оператор конкатенации
     */
    Seq<Element> operator* (const Seq<Element>& other) const {
        Seq<Element> result();
        result.a.insert(result.a.end(), this->a.begin(), this->a.end());
        result.a.insert(result.a.end(), other.a.begin(), other.a.end());
        result.reduct();
        return result;
    }

    /**
     * Оператор переворачивания (очевидным образом, редукция не требуется после этой операции).
     */
    Seq<Element> operator! () const {
        Seq<Element> result(*this);
        typename list<Element>::iterator i = a.begin();
        while (i != a.end()) {
            Element x = (*i);
            Element xReverted = !x;
            replace(i, i++, x, xReverted);
        }
        return result;
    }

    /**
     * Добавление элемента в конец последовательности
     */
    Seq<Element>& operator<< (const Element& e) {
        typename list<Element>::iterator i = --(a.end());
        if (i != a.end() && (*i) == !e) { // потребуется редукция всей последовательности
            a.push_back(e);
            reduct();
        } else {
            a.push_back(e);
        }
        return *this;
    }

    /**
     * Добавление элемента в начало последовательности
     */
    Seq<Element>& operator>> (const Element& e) {
        typename list<Element>::iterator i = (a.begin());
        if (i != a.end() && (*i) == !e) { // потребуется редукция всей последовательности
            a.push_front(e);
            reduct();
        } else {
            a.push_front(e);
        }
        return *this;
    }

    list<Element> asList () const { // копия элементов в list
        list<Element> result(a);
        return result;
    }

    string toString() const {
        string result = "[";
        for (typename list<Element>::const_iterator it=a.begin(); it!=a.end(); ++it) {
            if (result == "[" || result == "") {
                result += " ";
            } else {
                result += " << ";
            }
            result += ((*it).toString());
        }
        result += "]";
        return result;
    }

    bool operator== (const Seq<Element>& other) const {
        if (this == &other) {
            return true;
        }
        return this->a == other.a;
    }

    bool operator!= (const Seq<Element>& other) const {
        return !(this->operator==(other));
    }


};





#endif //UNTITLED13_SEQ_H
