package com.ongdev.queue;

import com.ongdev.linkedlist.DefaultDoublyLinkedList;
import com.ongdev.linkedlist.DoublyLinkedList;

import java.util.Iterator;

public class LinkedListBasedQueue<T> implements QueueADT<T>{
    private final DoublyLinkedList<T> linkedList = new DefaultDoublyLinkedList<>();

    public LinkedListBasedQueue() {
    }

    public LinkedListBasedQueue(T firstElem) {
        enQueue(firstElem);
    }

    @Override
    public void enQueue(T element) {
        linkedList.addLast(element);
    }

    @Override
    public T deQueue() {
        if(isEmpty()) throw new RuntimeException("Queue empty");
        return linkedList.removeFirst();
    }

    @Override
    public T peek() {
        if(isEmpty()) throw new RuntimeException("Queue empty");
        return linkedList.peekFirst();
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
