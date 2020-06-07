package com.ongdev.stack;

import com.ongdev.array.DynamicArray;

import java.util.EmptyStackException;
import java.util.Iterator;

public class ArrayBasedStack<T> implements StackADT<T> {
    private final DynamicArray<T> array;
    private int index = -1;

    public ArrayBasedStack(int initSize){
        array = new DynamicArray<>(initSize);
    }

    @Override
    public void push(T element) {
        index++;
        array.add(element);
    }

    @Override
    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return array.removeAtWithoutMoving(index--);
    }

    @Override
    public T top() {
        return array.get(index);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
