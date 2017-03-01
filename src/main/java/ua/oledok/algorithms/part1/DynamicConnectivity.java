package ua.oledok.algorithms.part1;

public interface DynamicConnectivity {

    int size();

    boolean isConnected(int q, int p);

    int root(int value);

    void union(int q, int p);
}
