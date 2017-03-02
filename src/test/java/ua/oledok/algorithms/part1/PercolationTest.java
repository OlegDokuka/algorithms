package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class PercolationTest {

    @Test
    public void shouldPercolate() {
        Percolation percolation = new Percolation(20);
        int iterations = 0;

        do {
            int row = StdRandom.uniform(20) + 1;
            int col = StdRandom.uniform(20) + 1;

            percolation.open(row, col);
            iterations++;
        } while (!percolation.percolates());

        String.valueOf(iterations);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnNegativeN() {
        new Percolation(-1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnZeroN() {
        new Percolation(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenToLowValuesInOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.open(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenNegativeValuesInOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.open(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenToHighValuesInOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.open(3, 3);
    }


    @Test
    public void shouldPassInOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.open(2, 2);
        percolation.open(1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenToLowValuesInIsOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isOpen(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenNegativeValuesInIsOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isOpen(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenToHighValuesInIsOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isOpen(3, 3);
    }


    @Test
    public void shouldPassInIsOpenUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isOpen(2, 2);
        percolation.isOpen(1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenToLowValuesInIsFullUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isFull(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenNegativeValuesInIsFullUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isFull(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenToHighValuesInIsFullUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isFull(3, 3);
    }


    @Test
    public void shouldPassInIsFullUsageMethod() {
        Percolation percolation = new Percolation(2);

        percolation.isFull(2, 2);
        percolation.isFull(1, 1);
    }
}
