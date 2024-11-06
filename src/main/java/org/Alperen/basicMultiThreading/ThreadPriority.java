package org.Alperen.basicMultiThreading;

public class ThreadPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority()); //default main 5
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY); //max 10
        System.out.println(Thread.currentThread().getPriority());
    }
}
