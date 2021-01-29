package com.ongdev.linkedlist;

import java.util.Iterator;

public class DefaultDoublyLinkedList<T> implements DoublyLinkedList<T>{
    private int size;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public void clear() {
        Node<T> currentNode = head;
        while (currentNode != null){
            Node<T> nextNode = currentNode.getNext();
            currentNode.setNext(null);
            currentNode.setPrev(null);
            currentNode.setData(null);
            currentNode = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(T element) {
        addLast(element);
    }

    @Override
    public void addFirst(T element) {
        if(isEmpty()){
            head = tail = new Node<>(element, null, null);
        }else{
            head.setPrev(new Node<>(element, null, head));
            head = head.getPrev();
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        if(isEmpty()){
            head = tail = new Node<>(element, null, null);
        }else{
            tail.setNext(new Node<>(element, tail, null));
            tail = tail.getNext();
        }
        size++;
    }

    @Override
    public T peekFirst() {
        if(isEmpty()) throw new RuntimeException("Empty linked list!");
        return head.getData();
    }

    @Override
    public T peekLast() {
        if(isEmpty()) throw new RuntimeException("Empty linked list!");
        return tail.getData();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) throw new RuntimeException("Empty linked list!");
        T data = head.getData();
        head = head.getNext();
        size--;
        if(isEmpty()) tail = null;
        else head.setPrev(null);

        return data;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) throw new RuntimeException("Empty linked list!");
        T data = tail.getData();
        tail = tail.getPrev();
        size--;
        if(isEmpty()) head = null;
        else tail.setNext(null);

        return data;
    }

    @Override
    public T remove(Node<T> node) {
        if(node.getPrev()==null) removeFirst();
        if(node.getNext()==null) removeLast();

        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());

        T data = node.getData();
        size--;

        node.setData(null);
        node.setNext(null);
        node.setPrev(null);
        node = null;

        return data;
    }

    @Override
    public boolean remove(Object object) {
        Node<T> currentNode = head;

        if(object == null){
            while (currentNode != null){
                if(currentNode.getData() == null){
                    remove(currentNode);
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }else{
            while (currentNode != null){
                if(currentNode.getData() == object){
                    remove(currentNode);
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }

    @Override
    public T removeAt(int index) {
        if(index < 0 || index >= size) throw new IllegalArgumentException();

        int i;
        Node<T> currentNode;

        if(index < size / 2){
            i = 0;
            currentNode = head;
            while (i != index) {
                currentNode = currentNode.getNext();
                i++;
            }
        }else{
            i = size - 1;
            currentNode = tail;
            while (i != index) {
                currentNode = currentNode.getPrev();
                i--;
            }
        }

        return remove(currentNode);
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        Node<T> currentNode = head;

        if(object == null){
            while (currentNode != null){
                if(currentNode.getData()==null){
                    return index;
                }
                currentNode = currentNode.getNext();
                index++;
            }
        }else{
            while (currentNode != null){
                if(currentNode.getData()==object){
                    return index;
                }
                currentNode = currentNode.getNext();
                index++;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private boolean isEntering = true;
            private Node<T> currentNode = null;

            @Override
            public boolean hasNext() {
                if(isEntering) {
                    return head != null;
                }
                return currentNode.getNext() != null;
            }

            @Override
            public T next() {
                T data = null;
                if(isEntering) {
                    data = head.getData();
                    currentNode = head;
                    isEntering =false;
                }else {
                    currentNode = currentNode.getNext();
                    data = currentNode.getData();
                }
                return data;
            }
        };
    }

    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        else{
            StringBuilder sb = new StringBuilder(size);
            sb.append("[ ");

            Node<T> currentNode = head;

            while (currentNode!=null){
                sb.append(currentNode.getData());
                if(currentNode.getNext()!=null) sb.append(", ");
                currentNode = currentNode.getNext();
            }
            sb.append(" ]");
            return sb.toString();
        }
    }
}
