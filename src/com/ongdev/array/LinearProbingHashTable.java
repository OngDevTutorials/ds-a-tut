package com.ongdev.array;

import com.ongdev.hashtable.OpenAddressingHashTableBase;

public class LinearProbingHashTable<K, V> extends OpenAddressingHashTableBase<K,V> {
    private static final int LINEAR_CONSTANT = 13;

    public LinearProbingHashTable() {
        super();
    }

    public LinearProbingHashTable(int capacity) {
        super(capacity);
    }

    public LinearProbingHashTable(double loadFactor, int capacity) {
        super(loadFactor, capacity);
    }

    @Override
    protected void setupProbing(K key) {}

    @Override
    protected int probe(int x) {
        return x * LINEAR_CONSTANT;
    }

    @Override
    protected void adjustCapacity() {
        while (gcd(LINEAR_CONSTANT, capacity) != 1) {
            capacity++;
        }
    }
}
