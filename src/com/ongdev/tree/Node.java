package com.ongdev.tree;

class Node<T extends Comparable<T>> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public com.ongdev.tree.Node<T> getLeft() {
        return left;
    }

    public void setLeft(com.ongdev.tree.Node<T> left) {
        this.left = left;
    }

    public com.ongdev.tree.Node<T> getRight() {
        return right;
    }

    public void setRight(com.ongdev.tree.Node<T> right) {
        this.right = right;
    }

    private com.ongdev.tree.Node<T> left, right;

    public Node(T data, com.ongdev.tree.Node<T> left, com.ongdev.tree.Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

