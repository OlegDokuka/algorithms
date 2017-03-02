package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF wqu;
    private final byte[] states;
    private final int sideSize;
    private final int size;
    private int openSites;


    // test client (optional)
    public static void main(String[] args) {

    }

    /**
     * create n-by-n grid, with all sites blocked
     *
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("[n] must be positive number greater then 0");
        }

        size = n * n + 2;

        int first = 0;
        int last = size - 1;

        sideSize = n;
        wqu = new WeightedQuickUnionUF(size);
        states = new byte[size];

        for (int i = 1; i <= n; i++) {
            wqu.union(first, i);
            wqu.union(last, last - i);
        }
    }

    /**
     * open site (row, col) if it is not open already
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        int index = index(row, col);

        if (states[index] > 0) {
            return;
        }

        states[index]++;

        if (row > 1) {
            int indexTop = index(row - 1, col);

            if (states[indexTop] > 0) {
                wqu.union(index + 1, indexTop + 1);
            }
        }

        if (row < sideSize) {
            int indexBottom = index(row + 1, col);

            if (states[indexBottom] > 0) {
                wqu.union(index + 1, indexBottom + 1);
            }
        }

        if (col > 1) {
            int indexLeft = index(row, col - 1);

            if (states[indexLeft] > 0) {
                wqu.union(index + 1, indexLeft + 1);
            }
        }

        if (col < sideSize) {
            int indexRight = index(row, col + 1);

            if (states[indexRight] > 0) {
                wqu.union(index + 1, indexRight + 1);
            }
        }

        if (wqu.connected(index + 1, 0)) {
            states[index]++;
        }

        openSites++;
    }

    /**
     * is site (row, col) open?
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        int index = index(row, col);

        return states[index] > 0;
    }

    /**
     * is site (row, col) full?
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        int index = index(row, col);

        return states[index] > 1;
    }

    /**
     * number of open sites
     *
     * @return
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * does the system percolate?
     *
     * @return
     */
    public boolean percolates() {
        return wqu.connected(0, size - 1);
    }

    private int index(int row, int col) {
        validate(row);
        validate(col);

        return (row - 1) * sideSize + col - 1;
    }

    private void validate(int val) {
        if (val < 1 || val > sideSize) {
            throw new IndexOutOfBoundsException("Passed address out of bounds");
        }
    }
}
