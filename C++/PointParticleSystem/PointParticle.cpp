#ifndef POINTPARTICLE_CPP
#define POINTPARTICLE_CPP

#include <string>

using namespace std;

/**
 * Материальная точка с координатами (x,y) и массой [m]
 */
class PointParticle {
private: 
    const double x;
    const double y;
    const double m;
public:
    PointParticle (double x, double y, double m): x(x), y(y), m(m) {
    }
    
    inline double getX () const {
        return x;
    }
    
    inline double getY () const {
        return y;
    }
    
    inline double getM () const {
        return m;
    }
    
    string toString() const {
        return string("(x:")+to_string(x)+",y:"+to_string(y)+";m:"+to_string(m)+")";
    }
    
};




#endif
