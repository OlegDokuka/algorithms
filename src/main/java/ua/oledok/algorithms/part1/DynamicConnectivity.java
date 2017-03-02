package ua.oledok.algorithms.part1;

import java.util.stream.IntStream;

public interface DynamicConnectivity {

    static int[] generate(int n) {
        return IntStream.range(0, n).toArray();
    }

    default boolean isConnected(int q, int p) {
        return root(q) == root(p);
    }

    int size();

    int root(int value);

    void union(int q, int p);
}
