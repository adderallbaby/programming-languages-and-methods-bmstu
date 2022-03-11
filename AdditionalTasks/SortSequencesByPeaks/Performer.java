package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Performer {

    public static void main(String[] args) {
	int amountOfSequences;
    Scanner scanner = new Scanner(System.in);
    amountOfSequences = scanner.nextInt();
    Sequence[] Sequences = new Sequence[amountOfSequences];
    for(int i = 0; i < amountOfSequences;i++){
        int n;
        n = scanner.nextInt();
        int[] currentSequence = new int[n];
        for(int j = 0; j < n; j++){
            currentSequence[j] = scanner.nextInt();
        }
        Sequences[i] = new Sequence(n, currentSequence);
    }
        Arrays.sort(Sequences);
        for(int i = 0; i < amountOfSequences; i++){
            Sequence.printSequence(Sequences[i]);
            System.out.println();
        }
    }
}
