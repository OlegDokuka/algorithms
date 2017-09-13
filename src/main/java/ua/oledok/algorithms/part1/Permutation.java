package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        if (k > 0) {
            RandomizedQueue<String> queue = new RandomizedQueue<>();

            for (int i = 0; i < k; i++) {
                queue.enqueue(StdIn.readString());
            }

            while (!StdIn.isEmpty()) {
                queue.enqueue(StdIn.readString());
                queue.dequeue();
            }

            for (String val : queue) {
                StdOut.println(val);
            }
        }
    }
}
