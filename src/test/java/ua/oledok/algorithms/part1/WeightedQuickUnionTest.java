package ua.oledok.algorithms.part1;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class WeightedQuickUnionTest {

    @Test
    public void twoDetachedElementsShouldNotBeConnected() {
        WeightedQuickUnion wqu = new WeightedQuickUnion(3);

        Assert.assertFalse(wqu.isConnected(0, 2));
    }

    @Test
    public void shouldJustUnionTwoElements() {
        WeightedQuickUnion wqu = new WeightedQuickUnion(3);

        wqu.union(0, 2);

        Assert.assertTrue(wqu.isConnected(0, 2));
        Assert.assertFalse(wqu.isConnected(1, 2));
        Assert.assertFalse(wqu.isConnected(0, 1));
    }

    @Test
    public void alreadyConnectedElementsShouldNotChangeParentOnRedundantUnion() throws NoSuchFieldException,
            IllegalAccessException {
        WeightedQuickUnion wqu = new WeightedQuickUnion(8);
        Field idsField = WeightedQuickUnion.class.getDeclaredField("ids");

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
        WeightedQuickUnion wqu = new WeightedQuickUnion(8);

        wqu.union(0, 1);
        wqu.union(2, 3);
        wqu.union(3, 4);

        Assert.assertEquals(3, wqu.root(2));
        Assert.assertEquals(3, wqu.root(4));
        Assert.assertEquals(1, wqu.root(1));
        Assert.assertEquals(1, wqu.root(0));
    }

    @Test
    public void shouldCreateConnectedTrees() throws NoSuchFieldException, IllegalAccessException {
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        Field idsField = WeightedQuickUnion.class.getDeclaredField("ids");

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
        Assert.assertTrue(wqu.isConnected(2, 9));

        Assert.assertArrayEquals(new int[]{1, 5, 5, 4, 5, 5, 6, 7, 9, 5}, ids);
    }

    @Test
    public void maxDepthShouldBeLessThenOrEqualToLogNPlus1() throws NoSuchFieldException, IllegalAccessException {
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        Field idsField = WeightedQuickUnion.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(wqu);

        wqu.union(0, 1);
        wqu.union(2, 3);
        wqu.union(4, 5);
        wqu.union(6, 7);
        wqu.union(8, 9);
        wqu.union(1, 3);
        wqu.union(5, 7);
        wqu.union(9, 1);
        wqu.union(3, 7);

        int[] depthMap = new int[10];
        int maxDepth = (int) ((Math.log(10) / Math.log(2)) + 1);

        for (int i = 0; i < 10; i++) {
            int depth = 1;
            int j = i;

            while (j != ids[j]) {
                j = ids[j];
                depth++;
            }

            Assert.assertTrue("Depth of [" + i + "] greater then Log(10)", depth <= maxDepth);

            depthMap[i] = depth;
        }

        Assert.assertArrayEquals(new int[]{3, 2, 2, 1, 4, 3, 3, 2, 3, 2}, depthMap);
    }

    @Test
    public void rootOfDetachedElementShouldPointingOnItsSelf() {
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);

        Assert.assertEquals(2, wqu.root(2));
    }
}
