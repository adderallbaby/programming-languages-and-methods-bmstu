package com.task;
import java.util.AbstractMap;
class Pair<A,B>{ // также можно использовать org.apache.commons.lang3.tuple Pair
    private final AbstractMap.SimpleEntry<A, B> entry;
    Pair(A a, B b){
        this.entry = new AbstractMap.SimpleEntry<>(a,b);
    }
    public A getA(){
        return entry.getKey();
    }
    public B getB(){
        return entry.getValue();
    }
}

