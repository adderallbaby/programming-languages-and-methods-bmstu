package com.company;

public class Sequence implements Comparable<Sequence> {
    private int length;
    private int[] posl = new int[length];
    public Sequence(int length, int[] posl){
        this.length = length;
        this.posl = posl;
    }
    public static int getLength(Sequence sequence){return sequence.length;}
    public static int[] getSequenceNumbers(Sequence sequence){return sequence.posl;}
    public static int getAmountOfPeaks(Sequence sequence){
        int l = getLength(sequence);
        int p[] = getSequenceNumbers(sequence);
        int peaks = 0;
        if(p[0] > p[1]){
            peaks++;
        }
        if(p[l-1] > p[l-2]){
            peaks++;
        }
        for(int i = 1; i < l - 1; i++){
            if(p[i-1] < p[i] && p[i] > p[i+1]){
                peaks++;
            }
        }
        return peaks;
    }

    @Override
    public int compareTo(Sequence o) {
        if(getAmountOfPeaks(o) > getAmountOfPeaks(new Sequence(length, posl))){
            return 1;
        }else if ((getAmountOfPeaks(o) < getAmountOfPeaks(new Sequence(length, posl)))){
            return -1;
        }else return 0;
    }
    public static void printSequence(Sequence sequence){
        int l = Sequence.getLength(sequence);
        System.out.print("{");
        for(int i = 0; i < l; i++){
            if(i != l-1)
                System.out.print(Sequence.getSequenceNumbers(sequence)[i] + " ");
            else
                System.out.print(Sequence.getSequenceNumbers(sequence)[i]);
        }
        if(getAmountOfPeaks(sequence) != 1)
            System.out.print("}, " + getAmountOfPeaks(sequence) + " peaks");
        else
            System.out.print("}, " + getAmountOfPeaks(sequence) + " peak");
    }
}
