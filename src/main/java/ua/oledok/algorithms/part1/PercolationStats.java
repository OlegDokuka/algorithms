package ua.oledok.algorithms.part1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] thresholds;

    /**
     * perform trials independent experiments on an n-by-n grid
     *
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }


        int generations = n * n;

        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            do {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                percolation.open(row, col);
            } while (!percolation.percolates());

            thresholds[i] = (double) percolation.numberOfOpenSites() / (double) generations;
        }
    }

    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * low  endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    public static void main(String[] args) {
        int[] in = StdIn.readAllInts();
        PercolationStats percolationStats = new PercolationStats(in[0], in[1]);

        StdOut.printf("mean                    = %s", percolationStats.mean());
        StdOut.println();
        StdOut.printf("stddev                  = %s", percolationStats.stddev());
        StdOut.println();
        StdOut.printf("95%% confidence interval = [%s, %s]",
                percolationStats.confidenceLo(), percolationStats.confidenceHi());
        StdOut.println();
    }
}
