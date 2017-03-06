package ua.oledok.algorithms.part1;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int head;
    private int tail;
    private int size;
    private Object[] elements;

    public RandomizedQueue() {
        elements = new Object[1];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item element) {
        if (element == null) {
            throw new NullPointerException();
        }

        elements[tail++] = element;

        size++;

        if (tail == elements.length) {
            resize(elements.length * 2);
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int next;
        Item result;

        do {
            next = StdRandom.uniform(head, tail);

            if (next == head) {
                result = (Item) elements[head];

                elements[head++] = null;
            } else if (next == tail) {
                result = (Item) elements[--tail];

                elements[tail] = null;
            } else {
                result = (Item) elements[next];

                elements[next] = null;
            }
        } while (result == null);

        size--;

        if (size() <= elements.length / 4) {
            resize(elements.length / 2);
        }

        return result;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int next;
        Item result;

        do {
            next = StdRandom.uniform(head, tail);
            result = (Item) elements[next];
        } while (result == null);

        return result;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int size) {
        Object[] current = elements;
        int length = this.size;

        elements = new Object[size];

        for (int i = head, j = 0; i < tail; i++) {
            Object value = current[i];

            if (value != null) {
                elements[j++] = value;
            }
        }

        tail = length;
        head = 0;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Object[] elements = new Object[size];
        private int position;

        private RandomizedQueueIterator() {
            for (int i = head, j = 0; i < tail; i++) {
                Object value = RandomizedQueue.this.elements[i];

                if (value != null) {
                    elements[j++] = value;
                }
            }

            StdRandom.shuffle(elements);
        }

        @Override
        public boolean hasNext() {
            return position < elements.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (Item) elements[position++];
        }
    }
}
