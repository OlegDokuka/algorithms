package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Shuffling {

    public static void shuffle(int[] array) {
        int length = array.length;

        for (int i = 1; i < length; i++) {
            exchange(array, i, StdRandom.uniform(0, i + 1));
        }
    }

    private static void exchange(int[] elements, int i, int j) {
        int iElement = elements[i];

        elements[i] = elements[j];
        elements[j] = iElement;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}