package ua.oledok.algorithms.part1;


public class WeightedQuickUnion implements DynamicConnectivity {
    private final int[] ids;
    private final int[] componentsSizes;
    private int size;

    public WeightedQuickUnion(int n) {
        this.size = n;
        this.ids = DynamicConnectivity.generate(n);
        this.componentsSizes = new int[n];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int root(int value) {
        while (ids[value] != value) {
            value = ids[value];
        }

        return value;
    }

    @Override
    public void union(int q, int p) {
        int qRoot = root(q);
        int pRoot = root(p);

        if (qRoot == pRoot) {
            return;
        }

        if (componentsSizes[qRoot] > componentsSizes[pRoot]) {
            ids[pRoot] = qRoot;
            componentsSizes[qRoot] = componentsSizes[qRoot] + componentsSizes[pRoot] + 1;
        } else {
            ids[qRoot] = pRoot;
            componentsSizes[pRoot] = componentsSizes[pRoot] + componentsSizes[qRoot] + 1;
        }

        size--;
    }
}
