package org.Alperen.concurrentCollection;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    static final int QUEUE_CAPACITY = 10;

    /**
     * A Queue that additionally supports operations that wait for the queue to become non-empty when retrieving an element,
     * and wait for space to become available in the queue when storing an element.
     */
    static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);


    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    taskQueue.put(i); //blocks the current thread indefinitely until the operation can succeed
                    System.out.println("Task produced: " + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread1 = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take(); //blocks the current thread indefinitely until the operation can succeed
                    processTask(task, "Consumer 1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread2 = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take(); //blocks the current thread indefinitely until the operation can succeed
                    processTask(task, "Consumer 2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }

    private static void processTask(int task, String consumerName) throws InterruptedException {
        System.out.println("Task being processed by " + consumerName + ": " + task);
        Thread.sleep(1000);
        System.out.println("Task consumed by " + consumerName + ": " + task);
    }
}
