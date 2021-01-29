package com.ongdev.hashtable;

import java.util.Random;

public class Test {
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
        testSeparateChaining();
    }

    private static void testSeparateChaining() {
        HashTableADT<Integer, Integer> hashtable = new SeparateChainingHashTable<>(MOD);
        long start = System.nanoTime();
        for(int i = 0; i< NUMBER_OF_KEYS; i++){
            hashtable.insert(keys[i], values[i]);
            int value = hashtable.get(keys[i]);
            if(value != values[i]) System.out.println("Code lai di ma");
        }
        long end = System.nanoTime();
        System.out.println("Separate chaining: " + (end - start) / 1e9);
    }
}
