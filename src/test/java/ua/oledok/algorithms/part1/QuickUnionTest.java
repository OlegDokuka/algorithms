package ua.oledok.algorithms.part1;


import org.junit.Assert;
import org.junit.Test;

public class QuickUnionTest {

    @Test
    public void twoDetachedElementsShouldNotBeConnected() {
        QuickUnion qu = new QuickUnion(new int[]{0, 1, 2});

        Assert.assertFalse(qu.isConnected(0, 2));
    }

    @Test
    public void shouldJustUnionTwoElements() {
        QuickUnion qu = new QuickUnion(new int[]{0, 1, 2});

        qu.union()

        Assert.assertTrue(qu.isConnected(0, 2));
        Assert.assertFalse(qu.isConnected(1, 2));
        Assert.assertFalse(qu.isConnected(0, 1));
    }
}
