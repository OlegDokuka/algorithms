package ua.oledok.algorithms.part1;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class QuickFindTest {

    @Test
    public void twoDetachedElementsShouldNotBeConnected() {
        QuickFind qf = new QuickFind(3);

        Assert.assertFalse(qf.isConnected(1, 2));
    }

    @Test
    public void shouldJustUnionTwoElements() throws NoSuchFieldException, IllegalAccessException {
        QuickFind qf = new QuickFind(3);
        Field idsField = QuickFind.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(qf);

        qf.union(0, 2);

        Assert.assertTrue(qf.isConnected(0, 2));
        Assert.assertFalse(qf.isConnected(1, 2));
        Assert.assertFalse(qf.isConnected(1, 0));
        Assert.assertArrayEquals(new int[]{2, 1, 2}, ids);
    }

    @Test
    public void shouldUnionAllSameElements() throws NoSuchFieldException, IllegalAccessException {
        QuickFind qf = new QuickFind(10);
        Field idsField = QuickFind.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(qf);

        qf.union(0, 2);
        qf.union(0, 5);
        qf.union(5, 2);
        qf.union(7, 5);

        Assert.assertArrayEquals(new int[]{5, 1, 5, 3, 4, 5, 6, 5, 8, 9}, ids);
    }

    @Test
    public void shouldContainSeveralUnionCollections() throws NoSuchFieldException, IllegalAccessException {
        QuickFind qf = new QuickFind(10);
        Field idsField = QuickFind.class.getDeclaredField("ids");

        idsField.setAccessible(true);

        int[] ids = (int[]) idsField.get(qf);

        qf.union(0, 2);
        qf.union(0, 5);
        qf.union(5, 2);
        qf.union(7, 5);

        qf.union(1, 9);
        qf.union(9, 3);
        qf.union(6, 3);

        qf.union(8, 4);

        Assert.assertArrayEquals(new int[]{5, 3, 5, 3, 4, 5, 3, 5, 4, 3}, ids);
    }

}
