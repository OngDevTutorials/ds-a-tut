package com.ongdev.queue;

import java.util.Iterator;

public class CircularArrayBasedQueue<T> implements QueueADT<T>{
    private final T[] array;
    private int front;
    private int end;
    private final int size;

    public CircularArrayBasedQueue(int maxSize) {
        front = end = 0;
        size = maxSize + 1;
        array =  (T[]) new Object[size];
    }

    @Override
    public void enQueue(T element) {
        array[end] = element;
        if(++end == size) end = 0;
        if(end == front) throw new RuntimeException("Queue is full!");
    }

    @Override
    public T deQueue() {
        if(isEmpty()) throw new RuntimeException("Queue empty");
        T deQueuedElem = array[front];
        if(++front == size) front = 0;
        return deQueuedElem;
    }

    @Override
    public T peek() {
        if(isEmpty()) throw new RuntimeException("Queue empty");
        return array[front];
    }

    @Override
    public int size() {
        if (front > end) return (end + size - front);
        return end - front;
    }

    @Override
    public boolean isEmpty() {
        return front == end;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
