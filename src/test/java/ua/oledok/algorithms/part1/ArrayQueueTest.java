package ua.oledok.algorithms.part1;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class ArrayQueueTest {
    @Test
    public void shouldHaveSizeOfOneAfterOneEnqueueing() {
        Queue<Integer> queue = new ArrayQueue<>();

        queue.enqueue(1);

        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void shouldHaveSizeOfZeroAcuterOneEnqueueingAndDequeueing() {
        Queue<Integer> queue = new ArrayQueue<>();

        queue.enqueue(1);

        Assert.assertEquals(Integer.valueOf(1), queue.dequeue());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void innerArrayShouldHaveSizeOf8() throws NoSuchFieldException, IllegalAccessException {
        Queue<Integer> queue = new ArrayQueue<>();
        Field elementsField = ArrayQueue.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(8, elements.length);
        Assert.assertArrayEquals(new Object[]{1, 2, 3, 4, 5, null, null, null}, elements);
    }


    @Test
    public void innerArrayShouldHaveExpectedElements() throws NoSuchFieldException, IllegalAccessException {
        Queue<Integer> queue = new ArrayQueue<>();
        Field elementsField = ArrayQueue.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.dequeue());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.dequeue());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(8, elements.length);
        Assert.assertArrayEquals(new Object[]{null, null, 3, 4, 5, null, null, null}, elements);
    }


    @Test
    public void innerArrayShouldResizedAfterThresholdReached() throws NoSuchFieldException, IllegalAccessException {
        Queue<Integer> queue = new ArrayQueue<>();
        Field elementsField = ArrayQueue.class.getDeclaredField("elements");

        elementsField.setAccessible(true);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);


        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.dequeue());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.dequeue());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(Integer.valueOf(3), queue.dequeue());
        Assert.assertEquals(2, queue.size());

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(4, elements.length);
        Assert.assertArrayEquals(new Object[]{4, 5, null, null}, elements);
    }
}
