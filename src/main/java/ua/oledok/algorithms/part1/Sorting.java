package ua.oledok.algorithms.part1;


public class Sorting {
    public static void selection(int[] elements) {
        int length = elements.length;

        for (int i = 0; i < length; i++) {
            int currentValue = elements[i];
            int exchangeIndex = i;

            for (int j = i + 1; j < length; j++) {
                int nextValue = elements[j];

                if (less(nextValue, currentValue)) {
                    exchangeIndex = j;
                    currentValue = nextValue;
                }
            }

            if (i != exchangeIndex) {
                exchange(elements, i, exchangeIndex);
            }
        }
    }

    public static void insertion(int[] elements) {
        insertion(elements, 1);
    }

    public static void shell(int[] elements) {
        int length = elements.length;
        int h = 1;

        while (h < length / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            insertion(elements, h);
            h /= 3;
        }
    }

    private static void insertion(int[] elements, int h) {
        int length = elements.length;

        for (int i = h; i < length; i += h) {
            for (int j = i; j > 0 && less(elements[j], elements[j - h]); j -= h) {
                exchange(elements, j - h, j);
            }
        }
    }

    private static <T extends Comparable<T>> boolean less(T left, T right) {
        return left.compareTo(right) < 0;
    }

    private static void exchange(int[] elements, int i, int j) {
        int iElement = elements[i];

        elements[i] = elements[j];
        elements[j] = iElement;
    }
}
