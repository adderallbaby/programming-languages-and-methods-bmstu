package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Performer {

    public static void main(String[] args) {
	int amountOfLines;
    Scanner scanner = new Scanner(System.in);
    amountOfLines = scanner.nextInt();
    Line[] Lines = new Line[amountOfLines];
    for(int i = 0; i < amountOfLines;i++){
        int n;
        n = scanner.nextInt();
        int[] currentLine = new int[n];
        for(int j = 0; j < n; j++){
            currentLine[j] = scanner.nextInt();
        }
        Lines[i] = new Line(n, currentLine);
    }
        Arrays.sort(Lines);
        for(int i = 0; i < amountOfLines; i++){
            Line.printLine(Lines[i]);
            System.out.println();
        }
    }
}
