package com.ongdev.hashtable;

import com.ongdev.array.LinearProbingHashTable;
import com.ongdev.array.QuadraticProbingHashTable;

import java.util.Random;

public class TestOpenAddressing {
    static final int NUMBER_OF_KEYS = 100000000;
    static final int MOD = 100000;
    static int[] keys = new int[NUMBER_OF_KEYS];
    static int[] values = new int[NUMBER_OF_KEYS];

    public static void main(String[] args) {
        Random random = new Random();
        for(int i = 0; i< keys.length; i++) {
            keys[i] = random.nextInt() % MOD;
            values[i] = random.nextInt() % MOD;
        }
        testLinearProbing();
        testQuadraticProbing();
    }

    private static void testQuadraticProbing() {
        HashTableADT<Integer, Integer> hashtable = new LinearProbingHashTable<>();
        long start = System.nanoTime();
        for(int i = 0; i< NUMBER_OF_KEYS; i++){
            hashtable.insert(keys[i], values[i]);
            int value = hashtable.get(keys[i]);
            if(value != values[i]) System.out.println("Code lai di ma");
        }
        long end = System.nanoTime();
        System.out.println("Linear probing: " + (end - start) / 1e9);
    }

    private static void testLinearProbing() {
        HashTableADT<Integer, Integer> hashtable = new QuadraticProbingHashTable<>();
        long start = System.nanoTime();
        for(int i = 0; i< NUMBER_OF_KEYS; i++){
            hashtable.insert(keys[i], values[i]);
            int value = hashtable.get(keys[i]);
            if(value != values[i]) System.out.println("Code lai di ma");
        }
        long end = System.nanoTime();
        System.out.println("Quadratic probing: " + (end - start) / 1e9);
    }
}
