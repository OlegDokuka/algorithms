package ua.oledok.algorithms.part1;

public class ArrayQueue<T> implements Queue<T> {

    private int head;
    private int tail;
    private Object[] elements;

    public ArrayQueue() {
        elements = new Object[1];
    }

    @Override
    public int size() {
        return tail - head;
    }

    @Override
    public void enqueue(T element) {
        elements[tail++] = element;

        if (tail == elements.length) {
            resize(elements.length * 2);
        }
    }

    @Override
    public T dequeue() {
        @SuppressWarnings("unchecked")
        T result = (T) elements[head];

        elements[head++] = null;

        if (size() <= elements.length / 4) {
            resize(elements.length / 2);
        }

        return result;
    }

    private void resize(int size) {
        Object[] current = elements;
        int length = tail - head;

        elements = new Object[size];
        tail = length;

        System.arraycopy(current, head, elements, 0, length);

        head = 0;
    }
}
