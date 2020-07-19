package com.ongdev.queue;

public class QueueTest {
    public static void main(String[] args) {
        int numberOfOperations = 10000000;

        // Array based stack
        QueueADT<Integer> circularArrayBasedQueue = new CircularArrayBasedQueue<>(numberOfOperations);

        long startTime = System.nanoTime();
        for (int i = 0; i< numberOfOperations; i++){
            circularArrayBasedQueue.enQueue(i);
        }
        for (int i = 0; i< numberOfOperations/2; i++){
            circularArrayBasedQueue.deQueue();
        }
        for (int i = 0; i< numberOfOperations/4; i++){
            circularArrayBasedQueue.enQueue(i);
        }
        for (int i = 0; i< 3*numberOfOperations/4; i++){
            circularArrayBasedQueue.deQueue();
        }
        long endTime = System.nanoTime();
        long arrayTime = endTime - startTime;
        System.out.println("Circular array based queue took: "+ arrayTime +"\n");


        // Linked list based stack
        QueueADT<Integer> linkedListBasedQueue = new LinkedListBasedQueue<>();

        startTime = System.nanoTime();
        for (int i = 0; i< numberOfOperations; i++){
            linkedListBasedQueue.enQueue(i);
        }
        for (int i = 0; i< numberOfOperations; i++){
            linkedListBasedQueue.deQueue();
        }
        endTime = System.nanoTime();
        long linkedListTime = endTime - startTime;
        System.out.println("Linked list based stack took: "+ linkedListTime +"\n");

        System.out.println("The difference: "+ (linkedListTime - arrayTime) +"\n");
    }
}
