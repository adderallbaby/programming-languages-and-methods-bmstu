package com.task;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Работа с последоватлеьностью интервалов вида [a, b], где a и b - целые числа.
 */
public class Intervals {
    private List<IntegerPair> data = new ArrayList<>(); // empty

    public List<IntegerPair> genIntervalSequence () {
        data = new ArrayList<>();

        data.add(new IntegerPair(-10, -7));
        data.add(new IntegerPair(-7, 3));
        data.add(new IntegerPair(3, 4));
        data.add(new IntegerPair(2, 5));
        data.add(new IntegerPair(5, 8));
        data.add(new IntegerPair(10, 12));
        data.add(new IntegerPair(13, 20));
        data.add(new IntegerPair(-10, 200));
        data.add(new IntegerPair(9, 9));
        data.add(new IntegerPair(2, 100));
        data.add(new IntegerPair(0, 15));
        data.add(new IntegerPair(-10, -6));
        return data;
    }

    public List<IntegerPair> getData () {
        return data;
    }

    public void setData (List<IntegerPair> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data.clear();
        }
    }

    /**
     * Сортировка интервалов от меньшего в большего по левой границе (a),
     * а также по длине отменьшей в большей (когда левая совпадает).
     * @return
     */
    public List<IntegerPair> sortLowHigh () {
        return data.stream()
                .sorted(Comparator.comparingInt(IntegerPair::getLength)) /* по длине */
                .sorted(Comparator.comparingInt(IntegerPair::getA))  /* по левой границе */
                .collect(Collectors.toList());
    }

    public List<IntegerPair> operation1SimpleFilterLeftConnected () {
        return data.stream()
                .skip(1)
                .filter((e) -> e.isTheAConnectedOtherB(data.get(data.indexOf(e)-1)))
                .collect(Collectors.toList());
    }

    public static void testOperation1 (List<IntegerPair> testData) {
        Set<IntegerPair> group0 = new LinkedHashSet<>(); // не совпадающие
        Set<IntegerPair> group1 = new LinkedHashSet<>(); // совпадающие, где A < 0
        Set<IntegerPair> group2 = new LinkedHashSet<>(); // совпадающие, где A >= 0

        boolean was = false;
        IntegerPair x = null;
        Iterator<IntegerPair> i = testData.iterator();
        while (i.hasNext()) {
            IntegerPair y = i.next();
            if (x != null) {
                was = true;
                Integer b1 = x.getB();
                Integer a2 = y.getA();
                if (!(b1.equals(a2))) {
                    group0.add(x);
                    group0.add(y);
                } else if (a2 < 0) {
                    group1.add(x);
                    group1.add(y);
                } else {
                    group2.add(x);
                    group2.add(y);
                }
            }
            x = y;
        }
        if (!was) {
            group0.add(x);
        }
        group0 = group0.stream().filter((e) -> !(group1.contains(e) || group2.contains(e))).collect(Collectors.toSet());
        System.out.println("Group 0 (missed): " + group0);
        System.out.println("Group 1 (ok, < 0): " + group1);
        System.out.println("Group 2 (ok, >= 0): " + group2);
    }

    public IntegerPair operation2MaxContainsIfPossible () {
        List<IntegerPair> dataSorted = sortLowHigh();
        if (dataSorted.size() == 0) {
            System.err.println("It is empty");
            return null;
        }
        IntegerPair result = dataSorted.remove(0);
//        System.out.println("Good: "+result); //

        Iterator<IntegerPair> i = dataSorted.iterator();
        while (i.hasNext()) {
            IntegerPair cur = i.next();
            if (cur.getA().equals(result.getA())) {
                if (result.getB() < cur.getB()) {
                    result = cur;
                }
            } else if (cur.getA() < result.getA()) {
                throw new RuntimeException("Sorting was broken");
            } else {
                if (result.getB() < cur.getB()) {
                    System.err.println("Can not find maximum interval, which contains others");
                    System.err.println("Last maximum was (broken): "+result);
                    result = null;
                    break;
                }
            }
//            System.out.println("Good: "+cur+" / maybe max "+result); //
        }
        return result;
    }

    public static void main (String[] args) {
        Intervals integerIntervals = new Intervals();
        integerIntervals.genIntervalSequence();

//        System.out.println(integerIntervals.sortLowHigh());
        System.out.println(integerIntervals.operation1SimpleFilterLeftConnected());
        testOperation1(integerIntervals.getData());

        IntegerPair maxIntervalHere = integerIntervals.operation2MaxContainsIfPossible();
        if (maxIntervalHere != null) {
            System.out.println("Max: "+maxIntervalHere);
        } else {
            System.out.println("No max.");
        }
    }
}
