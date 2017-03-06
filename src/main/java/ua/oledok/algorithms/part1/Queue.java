package ua.oledok.algorithms.part1;


public interface Queue<T> {

    int size();

    void enqueue(T element);

    T dequeue();

    default boolean isEmpty() {
        return size() <= 0;
    }
}