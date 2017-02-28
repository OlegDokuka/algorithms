package ua.oledok.algorithms.part1;

public class QuickFind {
    private final int[] ids;

    public QuickFind(int[] values) {
        ids = new int[values.length];
        System.arraycopy(values, 0, ids, 0, values.length);
    }

    public boolean isConnected(int q, int p) {
        return ids[q] == ids[p];
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
