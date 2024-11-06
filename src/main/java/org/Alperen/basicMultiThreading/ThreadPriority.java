package org.Alperen.basicMultiThreading;

public class ThreadPriority {
    public static void main(String[] args) {
        //IT'S ONLY A "HINT" TO THREAD SCHEDULER NOT AN ABSOLUTE RULE.
        //Lowest priority threads can outrun max priority

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority()); //default main 5
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY); //max 10
        System.out.println(Thread.currentThread().getPriority());

        Thread one = new Thread(() -> {
            for(int i = 0; i < 20; i++){
                System.out.println("Thread 1 :" + i);
            }
            System.out.println("Thread 1 finish " + Thread.currentThread().getPriority());
        });

        Thread two = new Thread(() -> {
            for(int i = 0; i < 20; i++){
                System.out.println("Thread 2 :" + i);
            }
            System.out.println("Thread 2 finish " + Thread.currentThread().getPriority());


        });

        Thread three = new Thread(() -> {
            for(int i = 0; i < 20; i++){
                System.out.println("Thread 3 :" + i);
            }
            System.out.println("Thread 3 finish " + Thread.currentThread().getPriority());


        });

        one.setPriority(Thread.MIN_PRIORITY);
        two.setPriority(Thread.MAX_PRIORITY);
        three.setPriority(8);
        one.start();
        two.start();
        three.start();
    }
}
