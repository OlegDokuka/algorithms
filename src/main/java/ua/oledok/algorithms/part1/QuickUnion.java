package ua.oledok.algorithms.part1;


public class QuickUnion implements DynamicConnectivity {
    private final int[] ids;
    private int size;

    public QuickUnion(int[] values) {
        size = values.length;
        ids = new int[size];

        System.arraycopy(values, 0, ids, 0, size);
    }

    public void union(int q, int p) {
        int qRoot = root(q);
        int pRoot = root(p);

        if (qRoot == pRoot) {
            return;
        }

        ids[qRoot] = pRoot;
        size--;
    }

    @Override
    public int size() {
        return size;
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
