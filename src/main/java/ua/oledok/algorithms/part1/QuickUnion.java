package ua.oledok.algorithms.part1;


public class QuickUnion {
    private final int[] ids;

    public QuickUnion(int[] values) {
        ids = new int[values.length];
        System.arraycopy(values, 0, ids, 0, values.length);
    }

    public void union(int q, int p) {
        int qRoot = root(q);
        int pRoot = root(p);

        ids[qRoot] = pRoot;
    }

    public boolean isConnected(int q, int p) {
        return root(q) == root(p);
    }

    public int root(int value) {
        while (ids[value] != value) {
            value = ids[value];
        }

        return value;
    }
}
