package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Iterator;

public class DequeTest {
    @Test
    public void shouldHaveSizeOfOneAfterOneEnqueueing() {
        Deque<Integer> queue = new Deque<>();

        queue.addFirst(1);

        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void shouldHaveSizeOfZeroAcuterOneEnqueueingAndDequeueing() {
        Deque<Integer> queue = new Deque<>();

        queue.addFirst(1);

        Assert.assertEquals(Integer.valueOf(1), queue.removeFirst());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void innerArrayShouldHaveSizeOf8() throws NoSuchFieldException, IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(12, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, null, 5, 4, 3, 2, 1, null, null, null, null}, elements);
    }


    @Test
    public void innerArrayShouldHaveExpectedElements() throws NoSuchFieldException, IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.removeLast());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.removeLast());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(12, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, null, 5, 4, 3, null, null, null, null, null, null}, elements);
    }


    @Test
    public void innerArrayShouldResizedAfterThresholdReached() throws NoSuchFieldException, IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);


        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.removeLast());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.removeLast());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(Integer.valueOf(3), queue.removeLast());
        Assert.assertEquals(2, queue.size());

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(6, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, 5, 4, null, null}, elements);
    }

    @Test
    public void reversedInnerArrayShouldHaveSizeOf8() throws NoSuchFieldException, IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(12, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, null, null, 1, 2, 3, 4, 5, null, null, null}, elements);
    }


    @Test
    public void shouldSequentiallyAddAndRemoveItems() {
        Deque<Double> queue = new Deque<>();

        for (int i = 0; i < 100; i++) {
            queue.addFirst(StdRandom.uniform());
        }

        for (int i = 0; i < 100; i++) {
            queue.removeFirst();
        }

        for (int i = 0; i < 1000; i++) {
            queue.addFirst(StdRandom.uniform());
        }

        for (int i = 0; i < 1000; i++) {
            queue.removeFirst();
        }

        for (int i = 0; i < 50; i++) {
            queue.addFirst(StdRandom.uniform());
        }

        for (int i = 0; i < 50; i++) {
            queue.removeFirst();
        }

        for (int i = 0; i < 500; i++) {
            queue.addFirst(StdRandom.uniform());
        }

        for (int i = 0; i < 500; i++) {
            queue.removeFirst();
        }
    }

    @Test
    public void reversedInnerArrayShouldHaveExpectedElements() throws NoSuchFieldException, IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.removeFirst());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.removeFirst());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(12, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, null, null, null, null, 3, 4, 5, null, null, null}, elements);
    }


    @Test
    public void reversedInnerArrayShouldResizedAfterThresholdReached() throws NoSuchFieldException,
            IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);


        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.removeFirst());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.removeFirst());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(Integer.valueOf(3), queue.removeFirst());
        Assert.assertEquals(2, queue.size());

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(6, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, null, 4, 5, null}, elements);
    }

    @Test
    public void shouldReturnCorrectResultDuringShuffledInsertionToTheTopAndTheBottomOfTheDeque() throws
            NoSuchFieldException, IllegalAccessException {
        Deque<Integer> queue = new Deque<>();
        Field elementsField = Deque.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.addLast(1);
        queue.addLast(2);
        queue.addFirst(3);
        queue.addLast(4);
        queue.addFirst(5);
        queue.addFirst(6);
        queue.addFirst(7);
        queue.addLast(8);
        queue.addLast(9);
        queue.addFirst(10);
        queue.addLast(11);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(24, elements.length);
        Assert.assertEquals(11, queue.size());
        Assert.assertEquals(Integer.valueOf(10), queue.removeFirst());
        Assert.assertEquals(10, queue.size());
        Assert.assertEquals(Integer.valueOf(7), queue.removeFirst());
        Assert.assertEquals(9, queue.size());
        Assert.assertEquals(Integer.valueOf(6), queue.removeFirst());
        Assert.assertEquals(8, queue.size());
        Assert.assertEquals(Integer.valueOf(11), queue.removeLast());
        Assert.assertEquals(7, queue.size());
        Assert.assertEquals(Integer.valueOf(9), queue.removeLast());
        Assert.assertEquals(6, queue.size());

        elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(12, elements.length);

        queue.addFirst(12);
        queue.addLast(13);
        queue.addLast(14);
        queue.addLast(15);
        queue.addLast(16);
        queue.addLast(17);
        queue.addLast(18);
        queue.addFirst(19);

        elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(24, elements.length);

        Assert.assertEquals(14, queue.size());
        Assert.assertEquals(Integer.valueOf(19), queue.removeFirst());
        Assert.assertEquals(13, queue.size());
        Assert.assertEquals(Integer.valueOf(12), queue.removeFirst());
        Assert.assertEquals(12, queue.size());
        Assert.assertEquals(Integer.valueOf(18), queue.removeLast());
        Assert.assertEquals(11, queue.size());
        Assert.assertEquals(Integer.valueOf(5), queue.removeFirst());
        Assert.assertEquals(10, queue.size());
        Assert.assertEquals(Integer.valueOf(3), queue.removeFirst());
        Assert.assertEquals(9, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.removeFirst());
        Assert.assertEquals(8, queue.size());
        Assert.assertEquals(Integer.valueOf(17), queue.removeLast());
        Assert.assertEquals(7, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.removeFirst());
        Assert.assertEquals(6, queue.size());

        elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(12, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, null, 4, 8, 13, 14, 15, 16, null, null, null},
                elements);

    }

    @Test
    public void shouldIterateOverAllElements() {
        Deque<Integer> queue = new Deque<>();

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);

        Iterator iterator = queue.iterator();

        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(4, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(5, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
