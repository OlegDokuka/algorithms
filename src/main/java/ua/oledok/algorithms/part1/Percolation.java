package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//TODO: how to fit in 11n^2+128n+1024 memory size?
public class Percolation {
    private final WeightedQuickUnionUF wqu;
    private final WeightedQuickUnionUF wquInner;
    private final boolean[] states;
    private final int sideSize;
    private final int size;
    private int openSites;

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

        sideSize = n;
        wqu = new WeightedQuickUnionUF(size);
        wquInner = new WeightedQuickUnionUF(size);
        states = new boolean[size - 2];
    }

    /**
     * open site (row, col) if it is not open already
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        int index = index(row, col);

        if (states[index]) {
            return;
        }

        states[index] = true;

        if (row == 1) {
            wqu.union(0, index + 1);
            wquInner.union(0, index + 1);
        } else {
            int indexTop = index(row - 1, col);

            if (states[indexTop]) {
                wqu.union(index + 1, indexTop + 1);
                wquInner.union(index + 1, indexTop + 1);
            }
        }

        if (row == sideSize) {
            wqu.union(size - 1, index + 1);
        } else {
            int indexBottom = index(row + 1, col);

            if (states[indexBottom]) {
                wqu.union(index + 1, indexBottom + 1);
                wquInner.union(index + 1, indexBottom + 1);
            }
        }

        if (col > 1) {
            int indexLeft = index(row, col - 1);

            if (states[indexLeft]) {
                wqu.union(index + 1, indexLeft + 1);
                wquInner.union(index + 1, indexLeft + 1);
            }
        }

        if (col < sideSize) {
            int indexRight = index(row, col + 1);

            if (states[indexRight]) {
                wqu.union(index + 1, indexRight + 1);
                wquInner.union(index + 1, indexRight + 1);
            }
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

        return states[index];
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

        return wquInner.connected(0, index + 1);
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
