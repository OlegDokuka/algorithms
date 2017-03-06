package ua.oledok.algorithms.part1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int head;
    private int tail = 1;
    private Object[] elements;

    public Deque() {
        elements = new Object[3];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return tail - head - 1;
    }

    public void addFirst(Item element) {
        if (element == null) {
            throw new NullPointerException();
        }

        elements[head--] = element;

        if (head == -1) {
            resize(elements.length * 2);
        }
    }

    public void addLast(Item element) {
        if (element == null) {
            throw new NullPointerException();
        }

        elements[tail++] = element;

        if (tail == elements.length) {
            resize(elements.length * 2);
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        @SuppressWarnings("unchecked")
        Item result = (Item) elements[++head];

        elements[head] = null;

        if (!isEmpty() && size() <= elements.length / 4) {
            resize(elements.length / 2);
        }

        return result;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        @SuppressWarnings("unchecked")
        Item result = (Item) elements[--tail];

        elements[tail] = null;

        if (!isEmpty() && size() <= elements.length / 4) {
            resize(elements.length / 2);
        }

        return result;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void resize(int size) {
        Object[] current = elements;
        int length = size();
        int position = size / 2 - length / 2;

        elements = new Object[size];

        System.arraycopy(current, head + 1, elements, position, length);

        head = position - 1;
        tail = head + length + 1;
    }

    private class DequeIterator implements Iterator<Item> {
        private int position;

        @Override
        public boolean hasNext() {
            return head + position + 1 < tail;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (Item) elements[++position + head];
        }
    }
}

