#include <iostream>
#include "ElementExample.h"
#include "Seq.h"

int main () {
    Seq<ElementExample> s;

    cout << (ElementExample (13, 73) == (!ElementExample (73, 13))) << (ElementExample (5, 6) == (!ElementExample (6, 5))) << (ElementExample (-7, 8) == !(!ElementExample (7, -8))) << endl; // check ElementExample

    s << ElementExample (1, 3);  cout << s.toString() << endl;
    ///s << ElementExample (2, 3);  cout << s.toString() << endl;
    s << ElementExample (3, 1);  cout << s.toString() << endl;
    s << ElementExample (4, -5);  cout << s.toString() << endl;
    s << ElementExample (4, 9);  cout << s.toString() << endl;
    s << ElementExample (5, 33);  cout << s.toString() << endl;
    s >> ElementExample (-5, 4);  cout << s.toString() << endl;

    cout << s.toString() << endl;

}
