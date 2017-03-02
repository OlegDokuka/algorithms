package ua.oledok.algorithms.part1;

public class QuickFind implements DynamicConnectivity {
    private final int[] ids;
    private int size;

    public QuickFind(int n) {
        size = n;
        ids = DynamicConnectivity.generate(n);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int root(int value) {
        return ids[value];
    }

    public void union(int q, int p) {
        int qid = ids[q];
        int pid = ids[p];

        if (qid == pid) {
            return;
        }

        for (int i = 0; i < ids.length; i++) {
            if (i == p) {
                continue;
            }

            if (ids[i] == qid) {
                ids[i] = pid;
            }
        }

        size--;
    }
}
