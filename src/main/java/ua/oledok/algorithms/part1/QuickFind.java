package ua.oledok.algorithms.part1;

public class QuickFind implements DynamicConnectivity {
    private final int[] ids;
    private int size;

    public QuickFind(int[] values) {
        size = values.length;
        ids = new int[size];

        System.arraycopy(values, 0, ids, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isConnected(int q, int p) {
        return ids[q] == ids[p];
    }

    @Override
    public int root(int value) {
        return ids[value];
    }

    public void union(int q, int p) {
        if (isConnected(q, p)) {
            return;
        }

        int qid = ids[q];
        int pid = ids[p];

        for (int i = 0; i < ids.length; i++) {
            if (i == p) {
                continue;
            }

            if (ids[i] == qid) {
                ids[i] = pid;
            }
        }
    }
}
