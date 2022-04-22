package com.task;

import java.util.*;
import java.util.stream.Collectors;

public class Palindrome {

    private static boolean ijPalindromeCheck (ListIterator<Character> iForward, ListIterator<Character> jBackward, List<String> result, String rootMiddleOptional) {
        int resultRoot = result.size();
        if (rootMiddleOptional == null) {
            rootMiddleOptional = "";
        }

        while (iForward.hasNext() && jBackward.hasPrevious()) {
            Character x = iForward.next();
            Character y = jBackward.previous();
            if (x == y) {
                if (resultRoot >= result.size()) {
                    result.add("" + y + rootMiddleOptional + x);
                } else {
                    result.add("" + y + result.get(result.size()-1) + x);
                }
            } else {
                break;
            }
        }
        return result.size() > resultRoot;
    }

    public static List<String> getAllPalindromes (String bigStr) {
        System.out.println("INPUT: "+bigStr);
        List<String> result = new LinkedList<>();
        List<Character> charsHere = bigStr.chars().mapToObj((e) -> (char)e).collect(Collectors.toList());
        ListIterator<Character> zForward = charsHere.listIterator();
        while (zForward.hasNext()) {
            Character z = zForward.next();
            int i;
            int j;
            if ((j = zForward.previousIndex()) >= 0) {
                i = zForward.nextIndex()-1;
                ListIterator<Character> jBackward = charsHere.listIterator(j);
                ListIterator<Character> iForward = charsHere.listIterator(i);

                if (ijPalindromeCheck (iForward, jBackward, result, "")) {
//                    System.out.println("good 2");
                }

                if (zForward.hasNext()) {
                    jBackward = charsHere.listIterator(j);
                    iForward = charsHere.listIterator(i+1);
                    if (ijPalindromeCheck (iForward, jBackward, result, ""+z)) {
//                        System.out.println("good 1");
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input;
        if (args.length == 0) { // входные данные из строки ниже
            input = "rgreerggrtrsrtrgfv";
        } else { // или входные данные из первого параметра
            input = args[0];
        }
        List<String> palindromes = getAllPalindromes(input);
        System.out.println("OUTPUT: "+palindromes);
    }
}
