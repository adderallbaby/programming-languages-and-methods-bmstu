#ifndef POINTPARTICLESYSTEM_CPP
#define POINTPARTICLESYSTEM_CPP

#include "PointParticle.cpp"

#include <algorithm>
#include <vector>
#include <set>
#include <iostream>
#include <limits>
#include <math.h>  

using namespace std;

typedef vector<PointParticle*>::const_iterator pcit;
typedef vector<PointParticle*>::iterator pit;

/**
 * Система материальных точек (2D)
 */
class PointParticleSystem {
private:
    vector<PointParticle*> points;
    
    inline double massSumm () const {
        double result = 0;
        for (pcit it=points.begin(); it!=points.end(); ++it) {
            result += (*it)->getM();
        }
        return result;
    }
    
    inline vector<PointParticleSystem*> constructSubsystems(vector<set<size_t> > preResult) {
        vector<PointParticleSystem*> result = vector<PointParticleSystem*>();
        size_t i = 0;
        for (pcit it=points.begin(); it!=points.end(); ++it, ++i) {
            int j=0;
            for (; j<preResult.size(); ++j) {
                if (preResult[j].find(i) != preResult[j].end()) {
                    while (result.size() <= j) {
                        PointParticleSystem* pps = new PointParticleSystem();
                        result.push_back(pps);
                    }
                    
                    const PointParticle &pp = get(i);
                    result[j]->push_back(new PointParticle(pp.getX(), pp.getY(), pp.getM()));                  
                    break;
                }
            }
        }
        return result;
    }
    
public:
    PointParticle* massCenter () const {
        double rx = 0;
        double ry = 0;
        double ms = massSumm();
        for (pcit it=points.begin(); it!=points.end(); ++it) {
            rx += (*it)->getX() * (*it)->getM() / ms;
            ry += (*it)->getY() * (*it)->getM() / ms;
        }
        return new PointParticle(rx, ry, ms);
    }

    PointParticleSystem () {
        points = vector<PointParticle*>();
    }
    
    void push_back (PointParticle* pp) {
        points.push_back(pp);
    }
    
    PointParticle* pop_back () {
        PointParticle* pp = points.back();
        points.pop_back();
        return pp;
    }
    
    string toString() const {
        string result = "[";
        for (pcit it=points.begin(); it!=points.end(); ++it) {
            if (result == "[" || result == "") {
                result += " ";
            } else {
                result += ", ";
            }
            result += ((*it)->toString());
        }
        result += "]";
        return result;
    }
    
    /**
     * Количество материальных точек
     */
    inline size_t count () const {
        return points.size();
    }
    
    /**
     * Получение ссылки на точку номер i
     */
    inline const PointParticle& get (size_t i) {
        return *(points[i]);
    }
    
    /**
     * Расстояние между точками x и y с номерами nx и ny соответственно
     */
    double distance (size_t nx, size_t ny) {
        if (nx < 0 || nx >= count() || ny < 0 || ny >= count()) {
            return -1 * numeric_limits<double>::infinity(); // -DoubleInfinity
        }
        double dx = (get(ny).getX()-get(nx).getX());
        double dy = (get(ny).getY()-get(nx).getY());
        return sqrt(dx*dx + dy*dy);
    }
    
    /**
     * Являются ли в системе эквивалентными НАПРЯМУЮ (один шагом) точки x и y (номера nx и ny) с максимальным шаговым переходом d (maxOneStepDistance).
     */
    inline bool isEquivalent (size_t nx, size_t ny, double maxOneStepDistance) {
        return distance(nx, ny) - maxOneStepDistance <= 0.0001;
    }
    
    /**
     * Разбиение на подсистемы эквивалентных точек по расстоянию d (maxOneStepDistance)
     */
    vector<PointParticleSystem*> findEquivalentSystems (double maxOneStepDistance) {
        vector<set<size_t> > preResult = vector<set<size_t> >(); // номера точек исходной системы
        vector<PointParticleSystem*> result = vector<PointParticleSystem*>();
        size_t i = 0;
        for (pcit it=points.begin(); it!=points.end(); ++it, ++i) {
            if (preResult.size() == 0 || preResult[0].size() == 0) {
                if (preResult.size() == 0) {
                    preResult.push_back(set<size_t>());
                }
                preResult[0].insert(i);
            } else {
                int j=0;
                /*jj:*/ for (; j<preResult.size(); ++j) {
                    for (size_t k=0; k<i; ++k) {
                        if (preResult[j].find(k) != preResult[j].end()) {
                            if (isEquivalent(k, i, maxOneStepDistance)) {
                                preResult[j].insert(i);
                                goto jj2; //break jj;
                            } else {
                                break;
                            }
                        }
                    }
                }
                jj2:
                ///if (j >= preResult.size()) {
                ///    cout << "ERROR!" << endl;
                ///    return result;
                ///} else 
                if (j >= preResult.size()) {
                    preResult.push_back(set<size_t>());
                    preResult[preResult.size()-1].insert(i);
                }
            }
        }
        
        result = constructSubsystems(preResult);
        return result;
    }

    
    
    ~PointParticleSystem () {
        for (pcit it=points.begin(); it!=points.end(); ++it) {
            delete *it;
        }
        points.clear();
    }
    
};



int main () {
    
    PointParticleSystem ppSystem;
    
    ppSystem.push_back(new PointParticle(3, 4, 20));
    ppSystem.push_back(new PointParticle(6, 7, 2));
    ppSystem.push_back(new PointParticle(-3, -3.4, 15));
    ppSystem.push_back(new PointParticle(-2, 8, 40));
    ppSystem.push_back(new PointParticle(10, -8, 2.7));
    ppSystem.push_back(new PointParticle(6, 5, 38.2));
    
    cout << ppSystem.toString() << endl;
    cout << "Count: " << ppSystem.count() << endl;
    
    if (ppSystem.count() >= 2) {
        cout << "Point 0 and Point 1: " << ppSystem.get(0).toString() << " & " << ppSystem.get(1).toString() << endl;
    }
    
    PointParticle* mc = ppSystem.massCenter();
    cout << "Mass center: " << (*mc).toString() << endl;
    delete mc;
    
    cout << ppSystem.distance(0, 3) << endl;
    
    vector<PointParticleSystem*> eSystems = ppSystem.findEquivalentSystems(7.5);
    for (size_t j=0; j<eSystems.size(); ++j) {
        cout << eSystems[j]->toString() << endl;
    }
    
    return 0;
}


#endif


