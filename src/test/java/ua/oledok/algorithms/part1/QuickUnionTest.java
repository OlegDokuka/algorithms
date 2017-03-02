package ua.oledok.algorithms.part1;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class QuickUnionTest {

    @Test
    public void twoDetachedElementsShouldNotBeConnected() {
        QuickUnion qu = new QuickUnion(3);

        Assert.assertFalse(qu.isConnected(0, 2));
    }

    @Test
    public void shouldJustUnionTwoElements() {
        QuickUnion qu = new QuickUnion(3);

        qu.union(0, 2);

        Assert.assertTrue(qu.isConnected(0, 2));
        Assert.assertFalse(qu.isConnected(1, 2));
        Assert.assertFalse(qu.isConnected(0, 1));
    }

    @Test
    public void alreadyConnectedElementsShouldNotChangeParentOnRedundantUnion() throws NoSuchFieldException,
            IllegalAccessException {
        QuickUnion qu = new QuickUnion(8);
        Field idsField = QuickUnion.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(qu);

        qu.union(0, 1);
        qu.union(1, 2);
        qu.union(2, 3);
        qu.union(3, 4);

        qu.union(0, 4);
        qu.union(1, 4);
        qu.union(2, 4);


        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 4, 5, 6, 7}, ids);
    }

    @Test
    public void shouldFindARoot() {
        QuickUnion qu = new QuickUnion(8);

        qu.union(0, 1);
        qu.union(2, 3);
        qu.union(3, 4);

        Assert.assertEquals(4, qu.root(2));
        Assert.assertEquals(1, qu.root(1));
        Assert.assertEquals(1, qu.root(0));
    }

    @Test
    public void shouldCreateConnectedTrees() throws NoSuchFieldException, IllegalAccessException {
        QuickUnion qu = new QuickUnion(10);
        Field idsField = QuickUnion.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(qu);

        qu.union(0, 1);
        qu.union(2, 5);
        qu.union(1, 5);

        qu.union(3, 4);
        qu.union(8, 9);
        qu.union(8, 3);

        Assert.assertTrue(qu.isConnected(3, 9));
        Assert.assertTrue(qu.isConnected(4, 8));
        Assert.assertTrue(qu.isConnected(4, 9));
        Assert.assertTrue(qu.isConnected(0, 5));
        Assert.assertTrue(qu.isConnected(1, 2));
        Assert.assertTrue(qu.isConnected(2, 0));
        Assert.assertFalse(qu.isConnected(2, 9));

        Assert.assertArrayEquals(new int[]{1, 5, 5, 4, 4, 5, 6, 7, 9, 4}, ids);
    }

    @Test
    public void rootOfDetachedElementShouldPointingOnItsSelf() {
        QuickUnion qu = new QuickUnion(10);

        Assert.assertEquals(2, qu.root(2));
    }
}
