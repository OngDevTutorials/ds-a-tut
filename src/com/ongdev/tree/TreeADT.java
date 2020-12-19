package com.ongdev.tree;

import java.util.Iterator;

public interface TreeADT<T extends Comparable<T>> {
    boolean isEmpty();
    int size();
    int height();
    boolean contains(T elem);
    boolean add(T elem);
    boolean remove(T elem);
    Iterator<T> traverse(TreeTraverseType type);
}
