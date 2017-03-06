package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StdRandom.class)
public class RandomizedQueueTest {

    @Test
    public void shouldHaveSizeOfOneAfterOneEnqueueing() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(1);

        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void shouldHaveSizeOfZeroAcuterOneEnqueueingAndDequeueing() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(1);

        Assert.assertEquals(Integer.valueOf(1), queue.dequeue());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void innerArrayShouldHaveSizeOf8() throws NoSuchFieldException, IllegalAccessException {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Field elementsField = RandomizedQueue.class.getDeclaredField("elements");

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
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Field elementsField = RandomizedQueue.class.getDeclaredField("elements");

        PowerMockito.mockStatic(StdRandom.class);

        when(StdRandom.uniform(0, 5))
                .thenReturn(3)
                .thenReturn(3)
                .thenReturn(2)
                .thenReturn(1)
                .thenReturn(4)
                .thenReturn(0);

        elementsField.setAccessible(true);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(4), queue.dequeue());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(3), queue.dequeue());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(8, elements.length);
        Assert.assertArrayEquals(new Object[]{1, 2, null, null, 5, null, null, null}, elements);
    }


    @Test
    public void innerArrayShouldResizedAfterThresholdReached() throws NoSuchFieldException, IllegalAccessException {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Field elementsField = RandomizedQueue.class.getDeclaredField("elements");

        PowerMockito.mockStatic(StdRandom.class);

        when(StdRandom.uniform(0, 5))
                .thenReturn(3)
                .thenReturn(3)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(1)
                .thenReturn(4)
                .thenReturn(0);

        elementsField.setAccessible(true);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Integer.valueOf(4), queue.dequeue());
        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Integer.valueOf(3), queue.dequeue());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.dequeue());
        Assert.assertEquals(2, queue.size());

        Object[] elements = (Object[]) elementsField.get(queue);

        Assert.assertEquals(4, elements.length);
        Assert.assertArrayEquals(new Object[]{1, 5, null, null}, elements);
    }
}
