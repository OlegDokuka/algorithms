package ua.oledok.algorithms.part1;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class WeightedQuickUnionTest {

    @Test
    public void twoDetachedElementsShouldNotBeConnected() {
        WeghtedQuickUnion wqu = new WeghtedQuickUnion(new int[]{0, 1, 2});

        Assert.assertFalse(wqu.isConnected(0, 2));
    }

    @Test
    public void shouldJustUnionTwoElements() {
        WeghtedQuickUnion wqu = new WeghtedQuickUnion(new int[]{0, 1, 2});

        wqu.union(0, 2);

        Assert.assertTrue(wqu.isConnected(0, 2));
        Assert.assertFalse(wqu.isConnected(1, 2));
        Assert.assertFalse(wqu.isConnected(0, 1));
    }

    @Test
    public void alreadyConnectedElementsShouldNotChangeParentOnRedundantUnion() throws NoSuchFieldException,
            IllegalAccessException {
        WeghtedQuickUnion wqu = new WeghtedQuickUnion(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        Field idsField = WeghtedQuickUnion.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(wqu);

        wqu.union(0, 1);
        wqu.union(1, 2);
        wqu.union(2, 3);
        wqu.union(3, 4);

        wqu.union(0, 4);
        wqu.union(1, 4);
        wqu.union(2, 4);


        Assert.assertArrayEquals(new int[]{1, 1, 1, 1, 1, 5, 6, 7}, ids);
    }

    @Test
    public void shouldFindARoot() {
        WeghtedQuickUnion wqu = new WeghtedQuickUnion(new int[]{0, 1, 2, 3, 4, 5, 6, 7});

        wqu.union(0, 1);
        wqu.union(2, 3);
        wqu.union(3, 4);

        Assert.assertEquals(4, wqu.root(2));
        Assert.assertEquals(1, wqu.root(1));
        Assert.assertEquals(1, wqu.root(0));
    }

    @Test
    public void shouldCreateConnectedTrees() throws NoSuchFieldException, IllegalAccessException {
        WeghtedQuickUnion wqu = new WeghtedQuickUnion(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        Field idsField = WeghtedQuickUnion.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(wqu);

        wqu.union(0, 1);
        wqu.union(2, 5);
        wqu.union(1, 5);

        wqu.union(3, 4);
        wqu.union(8, 9);
        wqu.union(5, 9);
        wqu.union(8, 3);

        Assert.assertTrue(wqu.isConnected(3, 9));
        Assert.assertTrue(wqu.isConnected(4, 8));
        Assert.assertTrue(wqu.isConnected(4, 9));
        Assert.assertTrue(wqu.isConnected(0, 5));
        Assert.assertTrue(wqu.isConnected(1, 2));
        Assert.assertTrue(wqu.isConnected(2, 0));
        Assert.assertFalse(wqu.isConnected(2, 9));

        Assert.assertArrayEquals(new int[]{1, 5, 5, 4, 5, 5, 6, 7, 9, 5}, ids);
    }

    @Test
    public void rootOfDetachedElementShouldPointingOnItsSelf() {
        WeghtedQuickUnion wqu = new WeghtedQuickUnion(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});

        Assert.assertEquals(2, wqu.root(2));
    }
}
