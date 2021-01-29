package com.ongdev.hashtable;

import com.ongdev.linkedlist.DefaultDoublyLinkedList;
import com.ongdev.linkedlist.DoublyLinkedList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SeparateChainingHashTable<K, V> implements HashTableADT<K, V>{
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    private final double loadFactor;
    private int size = 0, capacity, threshold;

    private DoublyLinkedList<Node<K, V>>[] table;

    public SeparateChainingHashTable() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public SeparateChainingHashTable(int capacity) {
        this(DEFAULT_LOAD_FACTOR, capacity);
    }

    public SeparateChainingHashTable(double loadFactor, int capacity) {
        if(capacity < 0) throw new IllegalArgumentException("Capacity bi gi ay ong ei");
        if(loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("load factor bi gi ay ong ei");
        }
        this.loadFactor = loadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.threshold = (int) (this.capacity * loadFactor);
        table = new DefaultDoublyLinkedList[capacity];
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
    public int hashCodeToIndex(int hashedKey) {
        return (int) ((hashedKey & 0xFFFFFFFFFL) % capacity);
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public boolean has(K key) {
        int index = hashCodeToIndex(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index];

        if(linkedList == null || linkedList.isEmpty()) return false;

        for(Node<K, V> node : linkedList) {
            if(node.getKey().equals(key)) return true;
        }
        return false;
    }

    @Override
    public V insert(K key, V value) {
        int index = hashCodeToIndex(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index];
        if(linkedList == null ) {
            table[index] = linkedList = new DefaultDoublyLinkedList<>();
            return addNodeToBucket(key, value, linkedList);
        } else if(linkedList.isEmpty()) {
            return addNodeToBucket(key, value, linkedList);
        }else {
            for(Node<K,V> node : linkedList) {
                if(node.getKey().equals(key)){
                    V oldValue = node.getValue();
                    node.setValue(value);
                    return oldValue;
                }
            }
            return addNodeToBucket(key, value, linkedList);
        }
    }

    private V addNodeToBucket(K key, V value, DoublyLinkedList<Node<K, V>> linkedList) {
        linkedList.add(new Node<>(key, value));
        if (++size > threshold) resizeTable();
        return null;
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (this.capacity * loadFactor);
        DoublyLinkedList<Node<K, V>>[] newTable = new DefaultDoublyLinkedList[capacity];
        for (int i = 0; i< table.length; i++) {
            if(table[i] == null) continue;

            for(Node<K, V> node : table[i]){
                int index = hashCodeToIndex(node.getHash());
                DoublyLinkedList<Node<K, V>> linkedList = newTable[index];
                if(linkedList == null)
                    newTable[index] = linkedList = new DefaultDoublyLinkedList<>();
                linkedList.add(node);
            }

            table[i].clear();
            table[i] = null;
        }

        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = hashCodeToIndex(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index];

        if(linkedList == null || linkedList.isEmpty()) return null;
        for(Node<K, V> node: linkedList) {
            if(node.getKey().equals(key)) return node.getValue();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashCodeToIndex(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index];

        if(linkedList == null || linkedList.isEmpty()) return null;
        for(Node<K,V> node: linkedList) {
            if(node.getKey().equals(key)) {
                linkedList.remove(node);
                --size;
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        final int elementCount = size();

        return new Iterator<K>() {
            int index = 0;
            Iterator<Node<K, V>> bucketIterator = table[0] == null ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                if(elementCount != size()) throw new ConcurrentModificationException("Table bi doi mat tieu luonnnnnn!");

                if(bucketIterator == null || !bucketIterator.hasNext()) {
                    while(++index < capacity) {
                        if(table[index] != null || !table[index].isEmpty() ) {
                            bucketIterator = table[index].iterator();
                            break;
                        }
                    }
                }
                return index < capacity;
            }

            @Override
            public K next() {
                return bucketIterator.next().getKey();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < table.length; i ++) {
            DoublyLinkedList<Node<K, V>> linkedList = table[i];

            if(linkedList == null || linkedList.isEmpty()) continue;

            Iterator<Node<K, V>> iterator = linkedList.iterator();

            do {
                Node<K, V> node = iterator.next();
                sb.append(node.toString()).append(", ");
            }while (iterator.hasNext());
        }
        sb.append("}");
        return sb.toString();
    }
}
