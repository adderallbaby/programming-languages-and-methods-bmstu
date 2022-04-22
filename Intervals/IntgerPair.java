package com.task;

public class IntegerPair extends Pair<Integer, Integer> {
    IntegerPair (Integer a, Integer b) {
        super(a, b);
        if (!(a <= b)) {
            throw new RuntimeException("For IntegerPair must be a <= b");
        }
    }

    public Integer getLength() {
        Integer result = getB() - getA();
        if (result < 0) {
            result = -result;
        }
        return result;
    }

    /**
     * Текущий интервал слева касается другого.
     * @param o другой интервал
     * @return касается ли слева?
     */
    public boolean isTheAConnectedOtherB (Object o) {
        if (o == null || !(o instanceof IntegerPair)) {
            return false;
        }
        IntegerPair other = (IntegerPair)o;
        return other.getB().equals(this.getA());
    }

    /**
     * Текущий интервал справа касается другого.
     * @param o другой интервал
     * @return касается ли справа?
     */
    public boolean isTheBConnectedOtherA (Object o) {
        if (o == null || !(o instanceof IntegerPair)) {
            return false;
        }
        return ((IntegerPair)o).isTheAConnectedOtherB(this);
    }

    /**
     * Сравнение интервалов i1 и i2 на возможность касания [a, b] и [b, c]
     * @param i1
     * @param i2
     * @return
     */
    public static int compareSpecial (IntegerPair i1, IntegerPair i2) {
        if (i2.isTheAConnectedOtherB(i1)) {
            return 1; //  i1, i2
        } else if (i1.isTheAConnectedOtherB(i2)) {
            return -1; //  i2, i1
        } else {
            return 0; // zero does not means equals here
        }
    }

    @Override
    public String toString() {
        return "["+getA()+","+getB()+"]";
    }
}
